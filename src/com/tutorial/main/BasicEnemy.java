package com.tutorial.main;

import java.awt.*;

public class BasicEnemy extends GameObject {
    public BasicEnemy(int x, int y, ID id) {
        super(x, y, id);
        this.velX = 5;
        this.velY = 5;
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
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(this.x, this.y, 16, 16);
    }
}
