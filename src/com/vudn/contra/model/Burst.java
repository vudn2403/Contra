package com.vudn.contra.model;

import com.vudn.contra.manager.ImageStore;

import java.awt.*;

public class Burst extends BoxItem {
    private int time;

    public static final int TYPE_BURST_1 = 1;
    public static final int TYPE_BURST_2 = 2;

    public Burst(int x, int y, int delay, int type) {
        super(x, y, delay, type);
    }

    public int getTime() {
        return time;
    }

    public void setTime(long numberOfSleep) {
        if (numberOfSleep % 250 != 0){
            return;
        }
        if (time < 3){
            time++;
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        switch (type) {
            case TYPE_BURST_1:
                if (time == 0){
                    graphics2D.drawImage(ImageStore.IMG_BURST_TYPE_1_1,
                            x,y,SIZE,SIZE,null);
                }
                if (time == 1){
                    graphics2D.drawImage(ImageStore.IMG_BURST_TYPE_1_2,
                            x,y,SIZE,SIZE,null);
                }

                break;
            case TYPE_BURST_2:
                if (time == 0){
                    graphics2D.drawImage(ImageStore.IMG_BURST_TYPE_2_1,
                            x,y,SIZE,SIZE,null);
                }
                if (time ==1){
                    graphics2D.drawImage(ImageStore.IMG_BURST_TYPE_2_2,
                            x,y,SIZE,SIZE,null);
                }
                if (time == 2){
                    graphics2D.drawImage(ImageStore.IMG_BURST_TYPE_2_3,
                            x,y,SIZE,SIZE,null);
                }
                break;

            default:
                break;
        }
    }
}
