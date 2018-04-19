package com.tutorial.main;

import java.awt.*;

public class HUD {
    public static int HEALTH = 100;
    private int greenValue = 255;
    private int score = 0;
    private int level = 1;

    public void tick() {
        HEALTH = Game.clamp(HEALTH, 0, 100);
        this.greenValue = Game.clamp(greenValue, 0, 255);
        this.greenValue = HEALTH * 2;
        this.score++;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        g.setColor(new Color(75, this.greenValue, 0));
        g.fillRect(15, 15, HEALTH * 2, 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);

        g.drawString("Score: " + this.score, 230, 26);
        g.drawString("Level: " + this.level, 230, 46);
    }
}
