package com.tutorial.main;

import java.awt.*;
import java.util.Random;

public class BossEnemyBullet extends GameObject {
    private Handler handler;
    private Random r = new Random();
    private Color color;

    public BossEnemyBullet(int x, int y, ID id, Handler handler, Color color) {
        super(x, y, id);
        this.handler = handler;
        this.velX = r.nextInt(10) - 5;
        this.velY = 5;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }

    public void tick() {
        this.x += velX;
        this.y += velY;

        if (this.y >= Game.HEIGHT) {
            this.handler.removeObject(this);
        }
        this.handler.addObject(new Trail(this.x, this.y, ID.Trail, this.handler, this.color, 16, 16, 0.05f));
    }

    public void render(Graphics g) {
        g.setColor(this.color);
        g.fillRect(this.x, this.y, 16, 16);
    }
}
