package com.vudn.contra.model;

import com.vudn.contra.manager.ImageStore;

import java.awt.*;
import java.net.Proxy;
import java.util.Random;

public class Bullet extends BaseItem {
    private int orientation;
    private int type;
    private int speed;
    private int ranger;

    public Bullet(int x, int y, int orientation, int delay, int type) {
        super(x, y, delay);
        this.orientation = orientation;
        this.type = type;
        rectangle = new Rectangle(x, y, 15, 15);
        speed = 1;
        computeSpeed();
        computeLocation();
        ranger = 0;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void computeLocation() {
        switch (type) {
            case TYPE_BILL_BULLET_1:
            case TYPE_BILL_BULLET_2:
            case TYPE_BILL_BULLET_3:
                switch (orientation) {
                    case LEFT:
                        x -= 10;
                        y += 35;
                        break;

                    case UP:
                        x += 30;
                        y -= 10;

                        break;
                    case RIGHT:
                        x += 75;
                        y += 35;
                        break;
                    case DOWN:
                        x += 40;
                        y += 75;
                        break;
                    case DOWN_LEFT:
                        x -= 10;
                        y += 90;
                        break;
                    case UP_LEFT:
                        x -= 10;
                        y -= 10;
                        break;
                    case UP_RIGHT:
                        x += 90;
                        y -= 10;
                        break;
                    case DOWN_RIGHT:
                        x += 90;
                        y += 90;
                        break;

                    default:
                        break;
                }
                break;

            case TYPE_SOLDIER_SNIPER_BULLET:
                switch (orientation) {
                    case LEFT:
                        y += 10;
                        break;

                    case UP:
                        break;
                    case RIGHT:
                        x += 75;
                        y += 10;
                        break;
                    case DOWN:
                        break;
                    case DOWN_LEFT:
                        x += 15;
                        y += 50;
                        break;
                    case UP_LEFT:
                        x -= 10;
                        y -= 15;
                        break;
                    case UP_RIGHT:
                        x += 75;
                        y -= 20;
                        break;
                    case DOWN_RIGHT:
                        x += 75;
                        y += 75;
                        break;

                    default:
                        break;
                }
                break;

            case TYPE_TURRET_CANNON_BULLET:
                switch (orientation) {
                    case LEFT:
                        x += 10;
                        y += 45;
                        break;

                    case UP:
                        x += 45;
                        y += 10;

                        break;
                    case RIGHT:
                        x += 90;
                        y += 45;
                        break;
                    case DOWN:
                        x += 45;
                        y += 90;
                        break;
                    case DOWN_LEFT:
                        x += 10;
                        y += 90;
                        break;
                    case UP_LEFT:
                        x += 10;
                        break;
                    case UP_RIGHT:
                        x += 90;
                        y += 10;
                        break;
                    case DOWN_RIGHT:
                        x += 90;
                        y += 90;
                        break;

                    default:
                        break;
                }
                break;

            case TYPE_BOSS_BULLET:
                x = x - 15;
                break;

            default:
                break;
        }
        rectangle.setLocation(x, y);

    }

    public void computeSpeed() {
        switch (type) {
            case TYPE_BILL_BULLET_1:
            case TYPE_BILL_BULLET_2:
            case TYPE_BILL_BULLET_3:
                speed = 1;
                break;

            case TYPE_SOLDIER_SNIPER_BULLET:
            case TYPE_TURRET_CANNON_BULLET:
                speed = 2;
                break;

            case TYPE_BOSS_BULLET:
                speed = 3;

            default:
                break;
        }
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        switch (type) {
            case TYPE_BILL_BULLET_1:
                graphics2D.drawImage(ImageStore.IMG_BULLET_0,
                        x, y, 10, 10, null);
                break;
            case TYPE_BILL_BULLET_2:
                graphics2D.drawImage(ImageStore.IMG_BULLET_2,
                        x, y, 15, 15, null);
                break;
            case TYPE_BILL_BULLET_3:
                graphics2D.drawImage(ImageStore.IMG_BULLET_2,
                        x, y, 15, 15, null);
                break;
            case TYPE_TURRET_CANNON_BULLET:
                graphics2D.drawImage(ImageStore.IMG_BULLET_0,
                        x, y, 10, 10, null);
                break;
            case TYPE_SOLDIER_SNIPER_BULLET:
                graphics2D.drawImage(ImageStore.IMG_BULLET_0,
                        x, y, 10, 10, null);
                break;

            case TYPE_BOSS_BULLET:
                graphics2D.drawImage(ImageStore.IMG_BULLET_2,
                        x, y, 20, 20, null);
                break;

            default:
                break;
        }
    }

    public void move(long numberOfSleep) {
        if (numberOfSleep % speed != 0) {
            return;
        }
        if (type == TYPE_BOSS_BULLET) {
            ranger ++;
            x--;
            if (ranger % 2 == 0){
                y += 1 + ranger/25;
            }
            rectangle.setLocation(x, y);
        } else {
            switch (orientation) {
                case LEFT:
                    x--;
                    rectangle.setLocation(x, y);
                    break;
                case UP:
                    y--;
                    rectangle.setLocation(x, y);
                    break;
                case RIGHT:
                    x++;
                    rectangle.setLocation(x, y);
                    break;
                case DOWN:
                    y++;
                    rectangle.setLocation(x, y);
                    break;
                case DOWN_LEFT:
                    x--;
                    y++;
                    rectangle.setLocation(x, y);
                    break;
                case UP_LEFT:
                    x--;
                    y--;
                    rectangle.setLocation(x, y);
                    break;
                case UP_RIGHT:
                    x++;
                    y--;
                    rectangle.setLocation(x, y);
                    break;
                case DOWN_RIGHT:
                    x++;
                    y++;
                    rectangle.setLocation(x, y);
                    break;
                default:
                    break;
            }
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
