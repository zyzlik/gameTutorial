package com.tutorial.main;

import java.awt.*;

public class BasicEnemy extends GameObject {
    private Handler handler;
    private Color color;

    public BasicEnemy(int x, int y, ID id, Handler handler, Color color) {
        super(x, y, id);
        this.velX = 5;
        this.velY = 5;
        this.handler = handler;
        this.color = color;
    }

    public void tick() {
        this.x += this.velX;
        this.y += this.velY;

        if (this.y <= 0 || this.y >= Game.HEIGHT - 32) {
            this.velY = -this.velY;
        }
        if (this.x <= 0 || this.x >= Game.WIDTH - 16) {
            this.velX = -this.velX;
        }
        this.handler.addObject(new Trail(this.x, this.y, ID.Trail, this.handler, this.color, 16, 16, 0.05f));
    }

    public void render(Graphics g) {
        g.setColor(this.color);
        g.fillRect(this.x, this.y, 16, 16);
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }
}
