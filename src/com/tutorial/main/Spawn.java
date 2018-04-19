package com.tutorial.main;

import java.awt.*;
import java.util.Random;

public class Spawn {
    private Handler handler;
    private HUD hud;
    private int scoreKeep = 0;
    private Random r;

    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
        this.r = new Random();
    }

    public void tick() {
        this.scoreKeep++;
        if (this.scoreKeep >= 500) {
            this.scoreKeep = 0;
            this.hud.setLevel(this.hud.getLevel() + 1);
            if (this.hud.getLevel() == 2 || this.hud.getLevel() == 3) {
                BasicEnemy enemy = new BasicEnemy(
                    r.nextInt(Game.WIDTH - 20),
                    r.nextInt(Game.HEIGHT - 20),
                    ID.BasicEnemy, this.handler, Color.red
                );
                this.handler.addObject(enemy);
            } else if (this.hud.getLevel() == 4) {
                FastEnemy enemy = new FastEnemy(
                    r.nextInt(Game.WIDTH - 20),
                    r.nextInt(Game.HEIGHT - 20),
                    ID.FastEnemy, this.handler, Color.cyan
                );
                this.handler.addObject(enemy);
            } else if (this.hud.getLevel() == 5) {
                SmartEnemy enemy = new SmartEnemy(
                    r.nextInt(Game.WIDTH-20),
                    r.nextInt(Game.HEIGHT-20),
                    ID.SmartEnemy, this.handler,
                    Color.green
                );
                this.handler.addObject(enemy);
            } else if (this.hud.getLevel() == 6) {
                this.handler.clearEnemies();
                BossEnemy enemy = new BossEnemy((Game.WIDTH / 2) - 48, -120, ID.BossEnemy, this.handler, Color.magenta);
                this.handler.addObject(enemy);
            }
        }
    }
}
