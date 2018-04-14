package com.tutorial.main;

import java.awt.*;

public class BasicEnemy extends GameObject {
    private Handler handler;

    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.velX = 5;
        this.velY = 5;
        this.handler = handler;
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
        this.handler.addObject(new Trail(this.x, this.y, ID.Trail, this.handler, Color.red, 16, 16, 0.01f));
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(this.x, this.y, 16, 16);
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }
}
