package com.tutorial.main;

import java.awt.*;

public class Trail extends GameObject {
    private float alpha = 1;
    private float life;
    private Handler handler;
    private Color color;
    private int width;
    private int height;

    public Trail(int x, int y, ID id, Handler handler, Color color, int width, int height, float life) {
        super(x, y, id);
        this.handler = handler;
        this.color = color;
        this.height = height;
        this.width = width;
        this.life = life;
    }

    public Rectangle getBounds() {
        return null;
    }

    public void tick() {
        if (this.alpha > this.life) {
            this.alpha -= this.life - 0.01f;
        } else {
            this.handler.removeObject(this);
        }
    }

    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(this.makeTransparent(this.alpha));
        g.setColor(color);
        g.fillRect(x, y, this.width, this.height);
        g2d.setComposite(this.makeTransparent(1));
    }
}
