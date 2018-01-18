package com.vudn.contra.model;

import com.vudn.contra.manager.ImageStore;

import java.awt.*;

public class Map extends  BaseItem {
    public Map(int x, int y, int delay) {
        super(x, y, delay);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(ImageStore.IMG_MAP_1,
                x, y, 10400, 750, null);
    }

    public void move(long numberOfSleep) {
        if (numberOfSleep % delay != 0) {
            return;
        }
        x--;
    }
}
