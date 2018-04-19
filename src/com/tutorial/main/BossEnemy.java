package com.tutorial.main;

import java.awt.*;
import java.util.Random;

public class BossEnemy extends BasicEnemy {
    private Color color;
    private Handler handler;
    private int timer;
    private int bulletTimer;
    private Random r;
    public BossEnemy(int x, int y, ID id, Handler handler, Color color) {
        super(x, y, id, handler, color);
        this.color = color;
        this.handler = handler;
        this.velX = 0;
        this.timer = 30;
        this.bulletTimer = 80;
        this.r = new Random();
    }

    public void tick() {
        this.x += this.velX;
        this.y += this.velY;

        if (this.timer <= 0) {
            this.velY = 0;
        } else {
            this.timer--;
        }
        if (this.timer <= 0) {
            this.bulletTimer--;
        }

        if (this.bulletTimer <= 0) {
            if (this.velX == 0) {
                this.velX = 2;
            }
            int spawn = this.r.nextInt(10);
            if (spawn == 0) {
                this.handler.addObject(new BossEnemyBullet(this.x + 48, this.y, ID.BasicEnemy, this.handler, Color.magenta));
            }
        }
        if (this.x <= 0 || this.x >= Game.WIDTH - 100) {
            this.velX = -this.velX;
        }
    }

    public void render(Graphics g) {
        g.setColor(this.color);
        g.fillRect(this.x, this.y, 96, 96);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 96, 96);
    }
}
