package com.tutorial.main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = WIDTH / 12 * 9;
    private static final long serialVersionUID = 1550691097823471818L;
    private Thread thread;
    private boolean running = false;
    private Handler handler;

    public Game() {
        this.handler = new Handler();
        this.addKeyListener(new KeyInput(this.handler));
        new Window(WIDTH, HEIGHT, "Let's build the game", this);
        Player player = new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player);
        this.handler.addObject(player);
        this.handler.addObject(new BasicEnemy(WIDTH / 2, 0, ID.BasicEnemy));
    }

    public static void main(String args[]) {
        new Game();
    }

    public synchronized void start() {
        this.thread = new Thread(this);
        this.thread.start();
        this.running = true;
    }

    public synchronized void stop() {
        try {
            this.thread.join();
            this.running = false;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        int frames = 0;
        double unprocessedSeconds = 0;
        long previousTime = System.nanoTime();
        double secondsForEachTick = 1 / 60.0;
        int tickCount = 0;
        boolean ticked = false;

        while(running) {
            long currentTime = System.nanoTime();
            long passedTime = currentTime - previousTime;
            previousTime = currentTime;
            unprocessedSeconds = unprocessedSeconds + passedTime / 1000000000.0
            int count = 0;

            while(unprocessedSeconds >= secondsForEachTick) {
                this.tick();
                count++;
                unprocessedSeconds -= secondsForEachTick;
                ticked = true;
                tickCount++;

                if(tickCount % 60 == 0){
                    previousTime += 1000;
                    frames = 0;
                }
            }
            if (ticked) {
                this.render();
                frames++;
                ticked = false;
            }
        }
        this.stop();
    }

    private void tick() {
        handler.tick();
    }
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        g.dispose();
        bs.show();
    }
}
