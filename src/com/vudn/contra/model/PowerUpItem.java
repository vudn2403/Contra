package com.vudn.contra.model;

import com.vudn.contra.manager.ImageStore;

import java.awt.*;

public class PowerUpItem extends BoxItem {
    private int hight;
    public PowerUpItem(int x, int y, int delay, int type) {
        super(x, y, delay,type);
        rectangle = new Rectangle(x,y,50,100);
        hight = 0;
    }


    @Override
    public void draw(Graphics2D graphics2D) {
        switch (type){
            case 1:
                graphics2D.drawImage(ImageStore.IMG_ITEM_M,x,y+65,75,35,null);
                break;

            case 2:
                graphics2D.drawImage(ImageStore.IMG_ITEM_R,x,y+65,75,35,null);
                break;
        }
    }

    public void autoMove(long numberOfSleep){
        if (numberOfSleep % delay != 0){
            return;
        }
        if (hight < 150){
            hight ++;
            x++;
            y-=2;
            rectangle.setLocation(x,y);
        }
    }

    public void fall(long numberOfSleep){
        if (numberOfSleep % (delay) != 0) {
            return;
        }
        if (hight == 150){
            x++;
            y++;
            rectangle.setLocation(x,y);
        }
    }
}
