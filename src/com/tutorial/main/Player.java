package com.tutorial.main;

import java.awt.*;

public class Player extends GameObject {
    public Player(int x, int y, ID id) {
        super(x, y, id);
    }
    public void tick() {
        this.x += this.velX;
        this.y += this.velY;
    }
    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(this.x, this.y, 32, 32);
    }
}
