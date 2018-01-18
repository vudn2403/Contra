package com.vudn.contra.model;

import java.awt.*;

public abstract class BaseItem {
    public static final int SIZE = 100;

    public static final int LEFT = 0;
    public static final int UP = 1;
    public static final int RIGHT = 2;
    public static final int DOWN = 3;
    public static final int DOWN_LEFT = 4;
    public static final int UP_LEFT = 5;
    public static final int UP_RIGHT = 6;
    public static final int DOWN_RIGHT = 7;

    public static final int TYPE_SNIPER = 1;
    public static final int TYPE_CANNON = 2;
    public static final int TYPE_TURRET = 3;
    public static final int TYPE_BOSS_GUN = 4;
    public static final int TYPE_BOSS = 5;

    public static final int TYPE_BILL_BULLET_1 = 0;
    public static final int TYPE_BILL_BULLET_2 = 1;
    public static final int TYPE_BILL_BULLET_3 = 2;
    public static final int TYPE_SOLDIER_SNIPER_BULLET = 11;
    public static final int TYPE_TURRET_CANNON_BULLET = 12;
    public static final int TYPE_BOSS_BULLET = 13;


    protected int x;
    protected int y;
    protected int delay;
    protected Rectangle rectangle;

    public BaseItem(int x, int y, int delay){
        this.x = x;
        this.y = y;
        this.delay = delay;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public abstract void draw(Graphics2D graphics2D);

}
