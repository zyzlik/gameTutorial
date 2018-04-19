package com.tutorial.main;

import java.awt.*;
import java.util.LinkedList;

public class Handler {
    LinkedList<GameObject> objects = new LinkedList<>();

    public void tick() {
        for(int i = 0; i < this.objects.size(); i++) {
            GameObject tempObject = this.objects.get(i);
            tempObject.tick();
        }
    }

    public void clearEnemies() {
        for(int i = 0; i < this.objects.size(); i++) {
            GameObject tempObject = this.objects.get(i);
            if (tempObject.getId() == ID.Player) {
                this.objects.clear();
                this.objects.add(tempObject);
                break;
            }
        }
    }

    public void render(Graphics g) {
        for(GameObject tempObject: this.objects) {
            tempObject.render(g);
        }
    }

    public void addObject(GameObject object) {
        this.objects.add(object);
    }

    public void removeObject(GameObject object) {
        this.objects.remove(object);
    }
}
