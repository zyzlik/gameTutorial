package com.tutorial.main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 800;
    public static final int HEIGHT = WIDTH / 12 * 9;
    private static final long serialVersionUID = 1550691097823471818L;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Random r = new Random();

    public Game() {
        this.handler = new Handler();
        this.hud = new HUD();
        this.spawner = new Spawn(this.handler, this.hud);
        this.addKeyListener(new KeyInput(this.handler));
        new Window(WIDTH, HEIGHT, "Let's build the game", this);
        Player player = new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, this.handler);
        this.handler.addObject(player);
        this.handler.addObject(new BasicEnemy(
            this.r.nextInt(Game.WIDTH - 20),
            this.r.nextInt(Game.HEIGHT - 20),
            ID.BasicEnemy, this.handler, Color.red)
        );
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
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                this.tick();
                delta--;
            }
            if (running) {
                this.render();
            }
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(frames);
                frames = 0;
            }
        }
        this.stop();
    }

    private void tick() {
        this.handler.tick();
        this.hud.tick();
        this.spawner.tick();
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

        this.handler.render(g);
        this.hud.render(g);

        g.dispose();
        bs.show();
    }
    public static int clamp(int var, int min, int max) {
        if (var >= max) {
            return max;
        }
        else if (var <= min) {
            return min;
        }
        else {
            return var;
        }
    }
}
