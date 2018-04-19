package com.tutorial.main;

import java.awt.*;

public class FastEnemy extends BasicEnemy {
    public FastEnemy(int x, int y, ID id, Handler handler, Color color) {
        super(x, y, id, handler, color);
        this.velX = 8;
        this.velY = 8;
    }
}
