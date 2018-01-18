package com.vudn.contra.model;

import com.vudn.contra.manager.ImageStore;

import java.awt.*;

public class MapItem extends BaseItem {
    private int type;
    public MapItem(int x, int y, int type,int delay) {
        super(x, y, delay);
        this.type = type;
        rectangle = new Rectangle(x,y,SIZE,SIZE);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
    }

    public void move(long numberOfSleep) {
        if (numberOfSleep % delay != 0) {
            return;
        }
        x--;
        rectangle.setLocation(x,y);
    }
}
