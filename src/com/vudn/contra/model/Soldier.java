package com.vudn.contra.model;

import com.vudn.contra.manager.ImageStore;
import com.vudn.contra.ui.Gui;

import java.awt.*;

public class Soldier extends Contra {


    public Soldier(int x, int y, int moveOrientation, int fireOrientation, int delay) {
        super(x, y, moveOrientation, fireOrientation, delay);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        super.draw(graphics2D);
    }

    @Override
    public void drawActivityOnTheGround(Graphics2D graphics2D) {
        switch (contraOrientation) {
            case LEFT:
                if (timeContraRunFire == 0) {
                    graphics2D.drawImage(ImageStore.IMG_SOLDIER_RUN_LEFT_1,
                            x, y, SIZE - 25, SIZE, null);
                }
                if (timeContraRunFire == 1) {
                    graphics2D.drawImage(ImageStore.IMG_SOLDIER_RUN_LEFT_2,
                            x, y, SIZE - 25, SIZE, null);
                }
                if (timeContraRunFire == 2) {
                    graphics2D.drawImage(ImageStore.IMG_SOLDIER_RUN_LEFT_3,
                            x, y, SIZE - 25, SIZE, null);
                }
                if (timeContraRunFire == 3) {
                    graphics2D.drawImage(ImageStore.IMG_SOLDIER_RUN_LEFT_4,
                            x, y, SIZE - 25, SIZE, null);
                }
                break;

            case RIGHT:
                if (timeContraRunFire == 0) {
                    graphics2D.drawImage(ImageStore.IMG_SOLDIER_RUN_RIGHT_1,
                            x, y, SIZE - 25, SIZE, null);
                }
                if (timeContraRunFire == 1) {
                    graphics2D.drawImage(ImageStore.IMG_SOLDIER_RUN_RIGHT_2,
                            x, y, SIZE - 25, SIZE, null);
                }
                if (timeContraRunFire == 2) {
                    graphics2D.drawImage(ImageStore.IMG_SOLDIER_RUN_RIGHT_3,
                            x, y, SIZE - 25, SIZE, null);
                }
                if (timeContraRunFire == 3) {
                    graphics2D.drawImage(ImageStore.IMG_SOLDIER_RUN_RIGHT_4,
                            x, y, SIZE - 25, SIZE, null);
                }
                break;
        }
    }

    @Override
    public void drawActivityOverHead(Graphics2D graphics2D) {
        if (contraOrientation == LEFT) {
            graphics2D.drawImage(ImageStore.IMG_SOLDIER_RUN_LEFT_3, x, y,
                    SIZE - 25, SIZE, null);
        }
        if (contraOrientation == RIGHT) {
            graphics2D.drawImage(ImageStore.IMG_SOLDIER_RUN_RIGHT_3, x, y,
                    SIZE - 25, SIZE, null);
        }
    }

    @Override
    public void drawActivityUnderWater(Graphics2D graphics2D) {
        graphics2D.drawImage(ImageStore.IMG_BILL_SWIM, x, y + 25, SIZE / 2, SIZE / 4, null);
    }

    @Override
    public void setTime(long numberOfSleep) {
        if (numberOfSleep % 100 != 0) {
            return;
        }

        if (timeContraRunFire < 3) {
            timeContraRunFire++;
        } else {
            timeContraRunFire = 0;
        }
    }

    @Override
    public void fall(long numberOfSleep) {
        if (numberOfSleep % (2) != 0) {
            return;
        }
        y++;
        switch (contraOrientation) {
            case LEFT:
                rectangle.setLocation(x - 1, y);
                break;

            case RIGHT:
                rectangle.setLocation(x + 1, y);
                break;

            default:
                break;
        }
    }

    public void moveWithMap(long numberOfSleep) {
        if (numberOfSleep % delay != 0) {
            return;
        }
        x--;
        rectangle.setLocation(x, y);

    }
}
