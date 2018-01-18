package com.vudn.contra.model;

import com.vudn.contra.manager.ImageStore;

import java.awt.*;

public class FlyingItem extends BoxItem {
    private int hight;

    public FlyingItem(int x, int y, int delay, int type) {
        super(x, y, delay, type);
        rectangle = new Rectangle(x, y, 75, 50);
        hight = 0;
    }

    public int getHight() {
        return hight;
    }

    public void setHight(long numberOfSleep) {
        if (numberOfSleep % 250 != 0) {
            return;
        }
        if (hight < Integer.MAX_VALUE) {
            hight++;
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(ImageStore.IMG_ITEM_FLYING,
                x, y, 75, 35, null);
    }

    public void autoMove(long numberOfSleep) {
        if (numberOfSleep % (delay) != 0) {
            return;
        }
        if (hight % 2 == 0) {
            x++;
            y--;
        } else {
            x++;
            y++;
        }
        rectangle.setLocation(x,y);
    }
}
