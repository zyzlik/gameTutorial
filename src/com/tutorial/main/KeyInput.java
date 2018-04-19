package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private Handler handler;
    private boolean[] keyDown = new boolean[4];

    public KeyInput(Handler handler) {
        this.handler = handler;
        this.keyDown[0] = false;
        this.keyDown[1] = false;
        this.keyDown[2] = false;
        this.keyDown[3] = false;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        for (int i = 0; i < this.handler.objects.size(); i++) {
            GameObject tempObject = this.handler.objects.get(i);
            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W) {
                    tempObject.setVelY(-5);
                    this.keyDown[0] = true;
                }
                if (key == KeyEvent.VK_S) {
                    tempObject.setVelY(5);
                    this.keyDown[1] = true;
                }
                if (key == KeyEvent.VK_A) {
                    tempObject.setVelX(-5);
                    this.keyDown[2] = true;
                }
                if (key == KeyEvent.VK_D) {
                    tempObject.setVelX(5);
                    this.keyDown[3] = true;
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < this.handler.objects.size(); i++) {
            GameObject tempObject = this.handler.objects.get(i);
            if (tempObject.getId() == ID.Player) {
                if (key == KeyEvent.VK_W) {
                    this.keyDown[0] = false;
                }
                if (key == KeyEvent.VK_S) {
                    this.keyDown[1] = false;
                }
                if (key == KeyEvent.VK_A) {
                    this.keyDown[2] = false;
                }
                if (key == KeyEvent.VK_D) {
                    this.keyDown[3] = false;
                }
                if (!this.keyDown[0] && !this.keyDown[1]) {
                    tempObject.setVelY(0);
                }
                if (!this.keyDown[2] && !this.keyDown[3]) {
                    tempObject.setVelX(0);
                }
            }
        }
    }
}
