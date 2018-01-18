package com.vudn.contra.model;

import com.vudn.contra.manager.ImageStore;

import java.awt.*;

public class BoxItem extends BaseItem{
    protected int type;
    private int status;
    public BoxItem(int x, int y, int delay, int type) {
        super(x, y, delay);
        this.type = type;
        rectangle = new Rectangle(x,y,SIZE,SIZE);
        status = 0;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(long numberOfSleep) {
        if ( numberOfSleep % 500 != 0){
            return;
        }
        if (status < Integer.MAX_VALUE){
            status ++;
        }else {
            status = 0;
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        switch (status % 3){
            case 0:
                graphics2D.drawImage(ImageStore.IMG_BOX_ITEM_1,x,y,SIZE,SIZE,null);
                break;

            case 1:
                graphics2D.drawImage(ImageStore.IMG_BOX_ITEM_2,x,y,SIZE,SIZE,null);
                break;

            case 2:
                graphics2D.drawImage(ImageStore.IMG_BOX_ITEM_3,x,y,SIZE,SIZE,null);
                break;
        }
    }

    public void move(long numberOfSleep) {
        if (numberOfSleep % delay != 0) {
            return;
        }
        x--;
        rectangle.setLocation(x,y);
    }
}
