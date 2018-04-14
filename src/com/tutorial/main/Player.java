package com.tutorial.main;

import java.awt.*;

public class Player extends GameObject {
    Handler handler;
    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }
    public void tick() {
        this.x += this.velX;
        this.y += this.velY;

        this.x = Game.clamp(this.x, 1, Game.WIDTH-36);
        this.y = Game.clamp(this.y, 1, Game.HEIGHT-60);

        this.collision();
    }
    public void collision() {
        for (GameObject tempObject: this.handler.objects) {
            if (tempObject.id == ID.BasicEnemy) {
                if (this.getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH -= 2;
                }
            }
        }
    }
    public void render(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(this.x, this.y, 32, 32);
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
}
