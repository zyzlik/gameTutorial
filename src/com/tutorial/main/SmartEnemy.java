package com.tutorial.main;

import java.awt.*;

public class SmartEnemy extends BasicEnemy {
    private Handler handler;
    private Color color;
    private GameObject player;
    public SmartEnemy(int x, int y, ID id, Handler handler, Color color) {
        super(x, y, id, handler, color);
        this.handler = handler;
        this.color = color;
        for (int i = 0; i < this.handler.objects.size(); i++) {
            if (this.handler.objects.get(i).getId() == ID.Player) {
                this.player = this.handler.objects.get(i);
            }
        }
    }

    @Override
    public void tick() {
        this.x += this.velX;
        this.y += this.velY;

        float diffX = this.x - this.player.getX() - 8;
        float diffY = this.y - this.player.getY() - 8;
        float distance = (float)Math.sqrt(
            ((this.x - this.player.getX()) * (this.x - this.player.getX())) +
            ((this.y - this.player.getY()) * (this.y - this.player.getY()))
        );
        this.velX = (int)Math.round(((-1.0 / distance) * diffX) * 2);
        this.velY = (int)Math.round(((-1.0 / distance) * diffY) * 2);

        if (this.y <= 0 || this.y >= Game.HEIGHT - 32) {
            this.velY = -this.velY;
        }
        if (this.x <= 0 || this.x >= Game.WIDTH - 16) {
            this.velX = -this.velX;
        }
        this.handler.addObject(new Trail(this.x, this.y, ID.Trail, this.handler, this.color, 16, 16, 0.03f));
    }
}
