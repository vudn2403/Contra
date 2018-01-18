package com.vudn.contra.model;

import com.vudn.contra.manager.ImageStore;

import java.awt.*;

public class Enemy extends BaseItem {
    private int orientation;
    private int type;
    private int timePaint;
    private int health;
    private int timeReloadBullet;

    public Enemy(int x, int y, int delay, int type) {
        super(x, y, delay);
        this.type = type;
        timePaint = 0;
        orientation = LEFT;
        rectangle = new Rectangle(x, y, SIZE, SIZE);
        setHealth();
        setRectangle(rectangle);
    }

    @Override
    public void setRectangle(Rectangle rectangle) {
        switch (type) {
            case TYPE_SNIPER:
                rectangle.setSize(SIZE/2,SIZE);
                break;

            case TYPE_BOSS_GUN:
                rectangle.setSize(SIZE/2, SIZE/5);
            case TYPE_BOSS:
                rectangle.setLocation(x+25,y+25);
                rectangle.setSize(SIZE/2,SIZE/2);

                break;

            default:
                break;
        }
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTimePaint() {
        return timePaint;
    }

    public void setTimePaint(long numberOfSleep) {
        if (numberOfSleep % 300 != 0) {
            return;
        }
        if (timePaint < Integer.MAX_VALUE) {
            timePaint++;
        } else {
            timePaint = 0;
        }
    }

    public int getHealth() {
        return health;
    }

    public void setHealth() {
        switch (type) {
            case TYPE_SNIPER:
                health = 2;
                break;

            case TYPE_CANNON:
            case TYPE_TURRET:
            case TYPE_BOSS_GUN:
            case TYPE_BOSS:
                health = 5;
                break;

            default:
                break;

        }
    }

    public int getTimeReloadBullet() {
        return timeReloadBullet;
    }

    public void setTimeReloadBullet(int timeReloadBullet) {
        this.timeReloadBullet = timeReloadBullet;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        switch (type) {
            case TYPE_SNIPER:
                drawSniper(graphics2D);
                break;

            case TYPE_CANNON:
                drawCannon(graphics2D);
                break;

            case TYPE_TURRET:
                drawTurret(graphics2D);
                break;

            case TYPE_BOSS_GUN:
                drawBossGun(graphics2D);
                break;

            case TYPE_BOSS:
                drawBoss(graphics2D);
                break;

            default:
                break;

        }
    }

    public void drawSniper(Graphics2D graphics2D) {
        switch (orientation) {
            case LEFT:
                graphics2D.drawImage(ImageStore.IMG_SNIPER_FIRE_LEFT,
                        x, y, SIZE - 25, SIZE, null);
                break;

            case UP_LEFT:
                graphics2D.drawImage(ImageStore.IMG_SNIPER_FIRE_UP_LEFT,
                        x, y - 15, SIZE - 25, SIZE + 15, null);
                break;

            case UP:

                break;

            case UP_RIGHT:
                graphics2D.drawImage(ImageStore.IMG_SNIPER_FIRE_UP_RIGHT,
                        x, y - 15, SIZE - 25, SIZE + 15, null);
                break;

            case RIGHT:
                graphics2D.drawImage(ImageStore.IMG_SNIPER_FIRE_RIGHT,
                        x, y, SIZE - 25, SIZE, null);
                break;

            case DOWN_RIGHT:
                graphics2D.drawImage(ImageStore.IMG_SNIPER_FIRE_DOWN_RIGHT,
                        x, y, SIZE - 25, SIZE, null);
                break;

            case DOWN:

                break;

            case DOWN_LEFT:
                graphics2D.drawImage(ImageStore.IMG_SNIPER_FIRE_DOWN_LEFT,
                        x, y, SIZE - 25, SIZE, null);
                break;

            default:
                break;
        }
    }

    public void drawCannon(Graphics2D graphics2D) {
        switch (orientation) {
            case LEFT:
                graphics2D.drawImage(ImageStore.IMG_CANNON_FIRE_LEFT,
                        x, y, SIZE, SIZE, null);
                break;

            case UP_LEFT:
                graphics2D.drawImage(ImageStore.IMG_CANNON_FIRE_UP_LEFT,
                        x, y, SIZE, SIZE, null);
                break;

            case UP:
                graphics2D.drawImage(ImageStore.IMG_CANNON_FIRE_UP,
                        x, y, SIZE, SIZE, null);
                break;

            case UP_RIGHT:
                graphics2D.drawImage(ImageStore.IMG_CANNON_FIRE_UP_RIGHT,
                        x, y, SIZE, SIZE, null);
                break;

            case RIGHT:
                graphics2D.drawImage(ImageStore.IMG_CANNON_FIRE_RIGHT,
                        x, y, SIZE, SIZE, null);
                break;

            case DOWN_RIGHT:
                graphics2D.drawImage(ImageStore.IMG_CANNON_FIRE_DOWN_RIGHT,
                        x, y, SIZE, SIZE, null);
                break;

            case DOWN:
                graphics2D.drawImage(ImageStore.IMG_CANNON_FIRE_DOWN,
                        x, y, SIZE, SIZE, null);
                break;

            case DOWN_LEFT:
                graphics2D.drawImage(ImageStore.IMG_CANNON_FIRE_DOWN_LEFT,
                        x, y, SIZE, SIZE, null);
                break;

            default:
                break;
        }
    }

    public void drawTurret(Graphics2D graphics2D) {
        switch (orientation) {
            case LEFT:
                graphics2D.drawImage(ImageStore.IMG_TURRET_FIRE_LEFT,
                        x, y, SIZE, SIZE, null);
                break;

            case UP_LEFT:
                graphics2D.drawImage(ImageStore.IMG_TURRET_FIRE_UP_LEFT,
                        x, y, SIZE, SIZE, null);
                break;

            case UP:
                graphics2D.drawImage(ImageStore.IMG_TURRET_FIRE_UP,
                        x, y, SIZE, SIZE, null);
                break;

            case UP_RIGHT:
                graphics2D.drawImage(ImageStore.IMG_TURRET_FIRE_UP_RIGHT,
                        x, y, SIZE, SIZE, null);
                break;

            case RIGHT:
                graphics2D.drawImage(ImageStore.IMG_TURRET_FIRE_RIGHT,
                        x, y, SIZE, SIZE, null);
                break;

            case DOWN_RIGHT:
                graphics2D.drawImage(ImageStore.IMG_TURRET_FIRE_DOWN_RIGHT,
                        x, y, SIZE, SIZE, null);
                break;

            case DOWN:
                graphics2D.drawImage(ImageStore.IMG_TURRET_FIRE_DOWN,
                        x, y, SIZE, SIZE, null);
                break;

            case DOWN_LEFT:
                graphics2D.drawImage(ImageStore.IMG_TURRET_FIRE_DOWN_LEFT,
                        x, y, SIZE, SIZE, null);
                break;

            default:
                break;
        }
    }

    public void drawBossGun(Graphics2D graphics2D){
        if (timeReloadBullet % 500 < 250){
            graphics2D.drawImage(ImageStore.IMG_BOSS_GUN_1,x,y,50,20,null);
        }else {
            graphics2D.drawImage(ImageStore.IMG_BOSS_GUN_1, x + 5, y, 45, 20, null);
        }
    }
    public void drawBoss(Graphics2D graphics2D){
        graphics2D.drawImage(ImageStore.IMG_END_BOSS,
                x-25, y-375,350,620,null);
        graphics2D.drawImage(ImageStore.IMG_BOSS,x,y,SIZE-25,SIZE,null);

    }

    public void move(long numberOfSleep) {
        if (numberOfSleep % delay != 0) {
            return;
        }
        x--;
        rectangle.setLocation(x, y);
    }

    public void computeOrientation(int xContra, int yContra){
        switch (type){
            case TYPE_SNIPER:
                if (xContra < (x + BaseItem.SIZE/2)){
                    if (yContra < y){
                        setOrientation(UP_LEFT);
                    }
                    if (y <= yContra && yContra <= y +BaseItem.SIZE/2){
                        setOrientation(LEFT);
                    }
                    if (yContra > y + BaseItem.SIZE){
                        setOrientation(DOWN_LEFT);
                    }
                }

                if (xContra > (x + BaseItem.SIZE/2)){
                    if (yContra < y){
                        setOrientation(UP_RIGHT);
                    }
                    if (y <= yContra && yContra <= y +BaseItem.SIZE/2){
                        setOrientation(RIGHT);
                    }
                    if (yContra > y + BaseItem.SIZE/2){
                        setOrientation(DOWN_RIGHT);
                    }
                }
                break;

            case TYPE_CANNON:
            case TYPE_TURRET:
                if (xContra < (x - BaseItem.SIZE)){
                    if (yContra < y){
                        setOrientation(UP_LEFT);
                    }
                    if (y <= yContra && yContra <= y +BaseItem.SIZE/2){
                        setOrientation(LEFT);
                    }
                    if (yContra > y + BaseItem.SIZE/2){
                        setOrientation(DOWN_LEFT);
                    }
                }
                if ((x - BaseItem.SIZE) < xContra && xContra <= (x + BaseItem.SIZE/2)){
                    if (yContra < y){
                        setOrientation(UP);
                    }
                    if (y <= yContra && yContra <= y +BaseItem.SIZE/2){
                        setOrientation(LEFT);
                    }
                    if (yContra > y + BaseItem.SIZE/2){
                        setOrientation(DOWN);
                    }
                }
                if (xContra > (x + BaseItem.SIZE/2)){
                    if (yContra < y){
                        setOrientation(UP_RIGHT);
                    }
                    if (y <= yContra && yContra <= y +BaseItem.SIZE/2){
                        setOrientation(RIGHT);
                    }
                    if (yContra > y + BaseItem.SIZE/2){
                        setOrientation(DOWN_RIGHT);
                    }
                }
                break;
        }
    }

    public void wounded(){
        if (health > 0){
            health --;
        }
    }
}
