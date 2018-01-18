package com.vudn.contra.manager;

import com.vudn.contra.model.*;
import com.vudn.contra.ui.Gui;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameManager {
    private static final int INTENAL_TIME_FIRE_1000 = 1000;
    private static final int INTENAL_TIME_FIRE_500 = 500;
    private static final int INTENAL_TIME_FIRE_200 = 200;
    private Contra contra;
    private Map map;
    private Bullet bullet;
    private Burst burst;
    private PowerUpItem powerUpItem;

    private ISound[] soundBillBulletIntersectBox = new ISound[5];
    private ISound soundBillDie;
    private ISound[] soundBillFire  = new ISound[5];
    private ISound soundBossDie;
    private ISound soundEndGame;
    private ISound soundIntersectItem;
    private ISound[] soundSoldierFire  = new ISound[5];
    private ISound[] soundSoldierDie  = new ISound[5];
    private ISound[] soundCannonFire  = new ISound[5];
    private ISound[] soundCannonDie  = new ISound[5];

    private ArrayList<Bullet> bullets;
    private ArrayList<BoxItem> boxItems;
    private ArrayList<FlyingItem> flyingItems;
    private ArrayList<PowerUpItem> powerUpItems;
    private ArrayList<MapItem> mapItems;
    private ArrayList<Soldier> soldiers;
    private ArrayList<Burst> bursts;
    private ArrayList<Enemy> enemies;

    private Random random;
    private int countTimeContraFire = 0;
    private long score = 0;
    public int numberBillLife = 4;
    public boolean endGame = false;

    public GameManager() {
        contra = new Contra(300, -BaseItem.SIZE, BaseItem.RIGHT, BaseItem.RIGHT, 2);
        map = new Map(0, 0, contra.getDelay());
        bullets = new ArrayList<>();
        boxItems = new ArrayList<>();
        flyingItems = new ArrayList<>();
        powerUpItems = new ArrayList<>();
        mapItems = new ArrayList<>();
        soldiers = new ArrayList<>();
        bursts = new ArrayList<>();
        enemies = new ArrayList<>();
        random = new Random();

        creatMap();
        creatSound();
        creatSoldier();
        runAudio();
    }

    public void creatMap() {
        try {
            //Creat MapItem
            File file = new File(getClass().getResource("/res/assets/mapitem.txt").getPath());
            BufferedReader input = new BufferedReader(new FileReader(file));
            String line = input.readLine();
            while (line != null) {
                int type = Integer.parseInt(line.charAt(0) + "");
                int x = Integer.parseInt(line.substring(line.indexOf("_") + 1, line.lastIndexOf("_")));
                int y = Integer.parseInt(line.substring(line.lastIndexOf("_") + 1));
                if (type == 1) {
                    MapItem mapItem = new MapItem(x, y - 75, type, contra.getDelay());
                    mapItems.add(mapItem);
                } else if (type == 2) {
                    MapItem mapItem = new MapItem(x, y, type, contra.getDelay());
                    mapItems.add(mapItem);
                }
                line = input.readLine();
            }
            input.close();

            //Creat enemy
            file = new File(getClass().getResource("/res/assets/enemy.txt").getPath());
            input = new BufferedReader(new FileReader(file));
            line = input.readLine();
            while (line != null) {
                int type = Integer.parseInt(line.charAt(0) + "");
                int x = Integer.parseInt(line.substring(line.indexOf("_") + 1, line.lastIndexOf("_")));
                int y = Integer.parseInt(line.substring(line.lastIndexOf("_") + 1));
                Enemy enemy = new Enemy(x, y, contra.getDelay(), type);
                enemies.add(enemy);
                line = input.readLine();
            }
            input.close();
            //Creat BoxItem
            file = new File(getClass().getResource("/res/assets/item.txt").getPath());
            input = new BufferedReader(new FileReader(file));
            line = input.readLine();
            while (line != null) {
                int type = Integer.parseInt(line.charAt(0) + "");
                int x = Integer.parseInt(line.substring(line.indexOf("_") + 1, line.lastIndexOf("_")));
                int y = Integer.parseInt(line.substring(line.lastIndexOf("_") + 1));
                BoxItem boxItem = new BoxItem(x, y, contra.getDelay(), type);
                boxItems.add(boxItem);
                line = input.readLine();
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void creatSound() {
        for (int i = 0; i < soundBillFire.length; i++) {
            soundBillFire[i] = new SoundWav("sound_bill_fire");
            soundBillBulletIntersectBox[i] = new SoundWav("sound_bill_bullet_intersect_box");
            soundSoldierFire[i] = new SoundWav("sound_snipper_solider_fire");
            soundSoldierDie[i] = new SoundWav("sound_solider_snipper_die");
            soundCannonFire[i] = new SoundWav("sound_wall_turret_cannon_boss_fire");
            soundCannonDie[i] = new SoundWav("sound_wall_turret_cannon_die");
        }
        soundBillDie = new SoundWav("sound_bill_die");
        soundBossDie = new SoundWav("sound_boss_die");
        soundEndGame = new SoundWav("sound_end_game");
        soundIntersectItem = new SoundWav("sound_intersects_items");
    }

    public void creatSoldier() {
        if (soldiers == null) {
            return;
        }
        while (soldiers.size() < 5) {
            int x = 0;
            if (random.nextInt(100) > 50) {
                x = 960 * 2 + random.nextInt(960 * 2);
            } else {
                x = -960 - random.nextInt(960 * 2);
            }
            Soldier soldier = new Soldier(x, random.nextInt(150),
                    BaseItem.LEFT, BaseItem.LEFT, contra.getDelay());
            soldiers.add(soldier);
        }
    }

    public void drawMapItem(Graphics2D graphics2D) {
        map.draw(graphics2D);
        if (!enemies.isEmpty()) {
            for (int i = 0; i < enemies.size(); i++) {
                enemies.get(i).draw(graphics2D);
            }
        }
        if (!boxItems.isEmpty()) {
            for (int i = 0; i < boxItems.size(); i++) {
                boxItems.get(i).draw(graphics2D);
            }
        }
        if (!flyingItems.isEmpty()) {
            for (int i = 0; i < flyingItems.size(); i++) {
                flyingItems.get(i).draw(graphics2D);
            }
        }
        if (!powerUpItems.isEmpty()) {
            for (int i = 0; i < powerUpItems.size(); i++) {
                powerUpItems.get(i).draw(graphics2D);
            }
        }
        if (!(soldiers.isEmpty())) {
            for (int i = 0; i < soldiers.size(); i++) {
                soldiers.get(i).draw(graphics2D);
            }
        }
        if (!bursts.isEmpty()) {
            for (int i = 0; i < bursts.size(); i++) {
                bursts.get(i).draw(graphics2D);
            }
        }
    }

    public void drawBillLife(Graphics2D graphics2D) {
        for (int i = 1; i <= numberBillLife; i++) {
            graphics2D.drawImage(ImageStore.IMG_ICON_BILL_LIFE, i * 35, 0, 35, 50, null);
        }
    }

    public void drawContra(Graphics2D graphics2D) {
        contra.draw(graphics2D);
    }

    public void drawBullet(Graphics2D graphics2D) {
        if (!bullets.isEmpty()) {
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).draw(graphics2D);
            }
        }
    }

    public void moveContra(long numberOfSleep) {
        if (contra.getActivity() == Contra.ACTIVITY_ON_THE_GROUND) {
            contra.setAction(Contra.ACTION_RUN);
        }
        if (contra.getRectangle().getX() + BaseItem.SIZE > Gui.WIDTH_FRAME / 2 && map.getX() > -(9435)) {
            moveMapAndEnemy(numberOfSleep);
        } else if (
                contra.getRectangle().x > 0
                        && contra.getRectangle().x <= 575) {
            contra.move(numberOfSleep);
        }
    }

    public void moveContraWhenJump(long numberOfSleep) {
        contra.jump(numberOfSleep);
    }

    public void moveMapAndEnemy(long numberOfSleep) {
        map.move(numberOfSleep);

        for (int i = 0; i < mapItems.size(); i++) {
            mapItems.get(i).move(numberOfSleep);
            if (isObjCanRemove(mapItems.get(i))) {
                mapItems.remove(mapItems.get(i));
                i--;
            }
        }
        if (!enemies.isEmpty()) {
            for (int i = 0; i < enemies.size(); i++) {
                enemies.get(i).move(numberOfSleep);
                if (isObjCanRemove(enemies.get(i))) {
                    enemies.remove(enemies.get(i));
                    i--;
                }
            }
        }
        if (!boxItems.isEmpty()) {
            for (int i = 0; i < boxItems.size(); i++) {
                boxItems.get(i).move(numberOfSleep);
                if (isObjCanRemove(boxItems.get(i))) {
                    boxItems.remove(boxItems.get(i));
                    i--;
                }
            }
        }
        if (!flyingItems.isEmpty()) {
            for (int i = 0; i < flyingItems.size(); i++) {
                flyingItems.get(i).move(numberOfSleep);
                if(isObjCanRemove(flyingItems.get(i))){
                    flyingItems.remove(flyingItems.get(i));
                    i--;
                }
            }
        }
        if (!powerUpItems.isEmpty()){
            for (int i = 0; i< powerUpItems.size(); i++){
                powerUpItems.get(i).move(numberOfSleep);
                if(isObjCanRemove(powerUpItems.get(i))){
                    powerUpItems.remove(powerUpItems.get(i));
                    i--;
                }
            }
        }
        if (!bursts.isEmpty()) {
            for (int i = 0; i < bursts.size(); i++) {
                bursts.get(i).move(numberOfSleep);
                if (isObjCanRemove(bursts.get(i))) {
                    bursts.remove(bursts.get(i));
                    i--;
                }
            }
        }
        if (!soldiers.isEmpty()) {
            for (int i = 0; i < soldiers.size(); i++) {
                soldiers.get(i).moveWithMap(numberOfSleep);
            }
        }
        if (!bullets.isEmpty()) {
            for (int i = 0; i < bullets.size(); i++) {
                bullets.get(i).moveWithMap(numberOfSleep);
            }
        }
    }

    public void moveSoldier(long numberOfSleep) {
        for (int i = 0; i < soldiers.size(); i++) {
            soldiers.get(i).move(numberOfSleep);
            soldiers.get(i).setAction(Contra.ACTION_RUN);
            canCollisionWithContra(soldiers.get(i));
            if (soldiers.get(i).getRectangle().getX() < -BaseItem.SIZE) {
                soldiers.get(i).setContraOrientation(BaseItem.RIGHT);
            }
            if (soldiers.get(i).getRectangle().getX() > Gui.WIDTH_FRAME + BaseItem.SIZE) {
                soldiers.get(i).setContraOrientation(BaseItem.LEFT);
            }
            if (soldiers.get(i).getRectangle().getY() > Gui.HEIGHT_FRAME) {
                if (random.nextInt(100) > 50) {
                    soldiers.get(i).setX(Gui.WIDTH_FRAME * 2 + random.nextInt(Gui.WIDTH_FRAME * 2));
                    soldiers.get(i).setY(0);
                } else {
                    soldiers.get(i).setX(-Gui.WIDTH_FRAME - random.nextInt(Gui.WIDTH_FRAME * 2));
                    soldiers.get(i).setY(0);
                }
            }
            if (map.getX() < -9000){
                if (soldiers.get(i).getX() < -BaseItem.SIZE || soldiers.get(i).getX() > Gui.WIDTH_FRAME){
                    soldiers.remove(soldiers.get(i));
                }
            }
        }
    }

    public void movePowerUpItem(long numberOfSleep){
        if (!flyingItems.isEmpty()){
            for (int i = 0; i < flyingItems.size();i++){
                flyingItems.get(i).autoMove(numberOfSleep);
                flyingItems.get(i).setHight(numberOfSleep);
                if(flyingItems.get(i).getX() > Gui.WIDTH_FRAME){
                    flyingItems.remove(flyingItems.get(i));
                    i--;
                }
            }
        }
        if (!powerUpItems.isEmpty()){
            for (int i = 0; i < powerUpItems.size();i++){
                powerUpItems.get(i).autoMove(numberOfSleep);
            }
        }
    }

    public void moveBullet(long numberOfSleep) {
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).move(numberOfSleep);
            if (isBulletCanRemove(bullets.get(i))) {
                bullets.remove(bullets.get(i));
                i--;
            }
        }
    }

    public void setContraAction(int contraAction) {
        contra.setAction(contraAction);
    }

    public void fireFromContra() {
        if (countTimeContraFire > 0) {
            return;
        }
        canPlay(soundBillFire).play();
        Bullet bullet = new Bullet(contra.getX(),
                contra.getY(), contra.getGunOrientation(), 2, contra.getTypeBullet());
        bullets.add(bullet);
        switch (contra.getTypeBullet()){
            case Contra.TYPE_BILL_BULLET_1:
                countTimeContraFire = INTENAL_TIME_FIRE_1000;
                break;

            case Contra.TYPE_BILL_BULLET_2:
                countTimeContraFire = INTENAL_TIME_FIRE_500;
                break;

            case Contra.TYPE_BILL_BULLET_3:
                countTimeContraFire = INTENAL_TIME_FIRE_200;
                break;

                default:
                    break;
        }
    }

    public void canEnemyFire(Enemy enemy) {
        if (isEnemyCanFire(enemy)) {
            switch (enemy.getType()) {
                case BaseItem.TYPE_SNIPER:
                    bullet = new Bullet(enemy.getX(), enemy.getY(), enemy.getOrientation(), contra.getDelay(),
                            BaseItem.TYPE_SOLDIER_SNIPER_BULLET);
                    bullets.add(bullet);
                    canPlay(soundSoldierFire).play();
                    enemy.setTimeReloadBullet(INTENAL_TIME_FIRE_1000);
                    break;

                case BaseItem.TYPE_CANNON:
                case BaseItem.TYPE_TURRET:
                    bullet = new Bullet(enemy.getX(), enemy.getY(), enemy.getOrientation(), contra.getDelay(),
                            BaseItem.TYPE_TURRET_CANNON_BULLET);
                    bullets.add(bullet);
                    canPlay(soundCannonFire).play();
                    enemy.setTimeReloadBullet(INTENAL_TIME_FIRE_1000);
                    break;

                case BaseItem.TYPE_BOSS_GUN:
                    bullet = new Bullet(enemy.getX(), enemy.getY(), enemy.getOrientation(), contra.getDelay(),
                            BaseItem.TYPE_BOSS_BULLET);
                    bullets.add(bullet);
                    canPlay(soundCannonFire).play();
                    enemy.setTimeReloadBullet(INTENAL_TIME_FIRE_500);
                    break;

                default:
                    break;
            }
        }
    }

    public boolean isEnemyCanFire(Enemy enemy) {
        return enemy.getTimeReloadBullet() == 0;
    }

    public void jumpContra() {
        contra.setAction(Contra.ACTION_JUMP);
    }

    public void setContraMoveOrientation(int contraMoveOrientation) {
        contra.setContraOrientation(contraMoveOrientation);
    }

    public void setContraFireOrientation(int contraFireOrientation) {
        contra.setGunOrientation(contraFireOrientation);
    }

    public void tryObjCanStand(long numberOfSleep) {
        for (int i = 0; i < soldiers.size(); i++) {
            if (canObjStand(soldiers.get(i))) {
                soldiers.get(i).setActivity(Contra.ACTIVITY_ON_THE_GROUND);
            } else if (canObjSwim(soldiers.get(i))) {
                soldiers.get(i).setActivity(Contra.ACTIVITY_UNDER_WATER);
                if (numberOfSleep % 100 == 0) {
                    if (random.nextInt(100) > 50) {
                        soldiers.get(i).setX(Gui.WIDTH_FRAME + random.nextInt(Gui.WIDTH_FRAME));
                        soldiers.get(i).setY(150);
                    } else {
                        soldiers.get(i).setX(-Gui.WIDTH_FRAME - random.nextInt(Gui.WIDTH_FRAME));
                        soldiers.get(i).setY(150);
                    }
                }
            } else {
                soldiers.get(i).fall(numberOfSleep);
                soldiers.get(i).setActivity(Contra.ACTIVITY_OVER_HEAD);

            }
        }
        if (!powerUpItems.isEmpty()){
            for(int i = 0; i < powerUpItems.size(); i++){
                if (!canObjStand(powerUpItems.get(i))){
                    powerUpItems.get(i).fall(numberOfSleep);
                }
            }
        }
        if (canObjStand(contra)) {
            contra.setActivity(Contra.ACTIVITY_ON_THE_GROUND);
            contra.setAction(Contra.ACTION_STAND);
        } else if (canObjSwim(contra)) {
            contra.setActivity(Contra.ACTIVITY_UNDER_WATER);
            contra.setAction(Contra.ACTION_STAND);
        } else {
            contra.fall(numberOfSleep);
            contra.setActivity(Contra.ACTIVITY_OVER_HEAD);
        }

    }

    public boolean canObjStand(BaseItem obj) {
        for (int i = 0; i < mapItems.size(); i++) {
            if (isCollisionWithMap(obj, mapItems.get(i)) && mapItems.get(i).getType() == 1) {
                return true;
            }
        }
        return false;
    }

    public boolean canObjSwim(BaseItem obj) {
        for (int i = 0; i < mapItems.size(); i++) {
            if (isCollisionWithMap(obj, mapItems.get(i)) && mapItems.get(i).getType() == 2) {
                return true;
            }
        }
        return false;
    }

    public boolean isCollisionWithMap(BaseItem obj1, BaseItem obj2) {
        Rectangle r1 = obj1.getRectangle();
        Rectangle r2 = obj2.getRectangle();
        Rectangle r3 = r1.intersection(r2);
        return (r3.width > 0 && r3.height == BaseItem.SIZE);
    }

    public boolean isCollisionWithBullet(BaseItem obj1, BaseItem obj2) {
        Rectangle r1 = obj1.getRectangle();
        Rectangle r2 = obj2.getRectangle();
        Rectangle rectangle = r1.intersection(r2);
        return (rectangle.width == 15 && rectangle.height == 15);
    }

    public boolean isCollisionWithOtherObj(BaseItem obj1, BaseItem obj2) {
        Rectangle r1 = obj1.getRectangle();
        Rectangle r2 = obj2.getRectangle();
        Rectangle rectangle = r1.intersection(r2);
        return (rectangle.width > 10 && rectangle.height > 50);
    }

    public boolean isObjCanRemove(BaseItem obj) {
        return obj.getX() <= -BaseItem.SIZE;
    }

    public boolean isBulletCanRemove(Bullet bullet) {
        return (bullet.getY() <= 0 || bullet.getX() <= 0
                || bullet.getX() > Gui.WIDTH_FRAME
                || bullet.getY() > Gui.HEIGHT_FRAME);
    }

    public boolean canContraDied(Bullet bullet) {
        if (isCollisionWithBullet(contra, bullet) && contra.getTimeContraImmotal() == 0) {
            burst = new Burst(contra.getX(), contra.getY(),
                    contra.getDelay(), Burst.TYPE_BURST_1);
            bursts.add(burst);
            soundBillDie.play();
            soundBillDie.loop(1);
            numberBillLife --;
            contra.died();
            return true;
        }
        return false;
    }

    public void tryEnemyAction() {
        if (!enemies.isEmpty()) {
            for (int i = 0; i < enemies.size(); i++) {
                countTimeEnemyReloadBullet(enemies.get(i));
                if (enemies.get(i).getX() < Gui.WIDTH_FRAME){
                    enemies.get(i).computeOrientation(contra.getX(), contra.getY());
                    canEnemyFire(enemies.get(i));
                    canCollisionWithContra(enemies.get(i));
                }
            }
        }
    }

    public void countTimeEnemyReloadBullet(Enemy enemy) {
        if (enemy.getTimeReloadBullet() > 0) {
            int i = enemy.getTimeReloadBullet() - 100;
            enemy.setTimeReloadBullet(i);
        }
    }

    public boolean canEnemyDestroyed(Bullet bullet) {
        if (!enemies.isEmpty()) {
            for (int i = 0; i < enemies.size(); i++) {
                if (isCollisionWithBullet(enemies.get(i), bullet)) {
                    switch (enemies.get(i).getType()) {
                        case Enemy.TYPE_SNIPER:
                            if (score < Long.MAX_VALUE) {
                                score += 50;
                            }
                            burst = new Burst(enemies.get(i).getX(), enemies.get(i).getY(),
                                    contra.getDelay(), Burst.TYPE_BURST_1);
                            bursts.add(burst);
                            canPlay(soundSoldierDie).play();
                            enemies.remove(enemies.get(i));
                            break;

                        case Enemy.TYPE_CANNON:
                        case Enemy.TYPE_TURRET:
                            enemies.get(i).wounded();
                            if (enemies.get(i).getHealth() == 0) {
                                if (score < Long.MAX_VALUE) {
                                    score += 50;
                                }
                                burst = new Burst(enemies.get(i).getX(), enemies.get(i).getY(),
                                        contra.getDelay(), Burst.TYPE_BURST_2);
                                bursts.add(burst);
                                canPlay(soundCannonDie).play();
                                enemies.remove(enemies.get(i));
                            } else {
                                canPlay(soundBillBulletIntersectBox).play();
                            }
                            break;
                        case Enemy.TYPE_BOSS_GUN:
                            enemies.get(i).wounded();
                            if (enemies.get(i).getHealth() == 0) {
                                if (score < Long.MAX_VALUE) {
                                    score += 100;
                                }
                                burst = new Burst(enemies.get(i).getX() - 25, enemies.get(i).getY() - 25,
                                        contra.getDelay(), Burst.TYPE_BURST_2);
                                bursts.add(burst);
                                canPlay(soundCannonDie).play();
                                enemies.remove(enemies.get(i));
                            } else {
                                canPlay(soundBillBulletIntersectBox).play();
                            }
                            break;
                        case Enemy.TYPE_BOSS:
                            if (enemies.size() == 1){
                                enemies.get(i).wounded();
                            }
                            if (enemies.get(i).getHealth() == 0) {
                                if (score < Long.MAX_VALUE) {
                                    score += 100;
                                }
                                burst = new Burst(enemies.get(i).getX(), enemies.get(i).getY(),
                                        contra.getDelay(), Burst.TYPE_BURST_2);
                                bursts.add(burst);
                                    for (int j = 1 ; j <= 3; j++){
                                        burst = new Burst(enemies.get(i).getX() + j*BaseItem.SIZE, enemies.get(i).getY(),
                                                contra.getDelay(), Burst.TYPE_BURST_2);
                                        bursts.add(burst);
                                    }
                                soundBossDie.play();
                                soundBossDie.loop(1);
                                endGame = true;
                                enemies.remove(enemies.get(i));
                            } else {
                                canPlay(soundBillBulletIntersectBox).play();
                            }
                            break;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canSoldierDestroyed(Bullet bullet) {
        if (!soldiers.isEmpty()) {
            for (int i = 0; i < soldiers.size(); i++) {
                if (isCollisionWithBullet(soldiers.get(i), bullet)) {
                    if (score < Long.MAX_VALUE) {
                        score += 25;
                    }
                    burst = new Burst(soldiers.get(i).getX(), soldiers.get(i).getY(),
                            contra.getDelay(), Burst.TYPE_BURST_1);
                    bursts.add(burst);
                    if (random.nextInt(100) > 50) {
                        soldiers.get(i).setX(Gui.WIDTH_FRAME * 2 + random.nextInt(Gui.WIDTH_FRAME * 2));
                        soldiers.get(i).setY(150);
                    } else {
                        soldiers.get(i).setX(-Gui.WIDTH_FRAME - random.nextInt(Gui.WIDTH_FRAME * 2));
                        soldiers.get(i).setY(150);
                    }
                    canPlay(soundSoldierDie).play();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canBoxItemDestroyed(Bullet bullet) {
        if (!boxItems.isEmpty()) {
            for (int i = 0; i < boxItems.size(); i++) {
                if (isCollisionWithBullet(boxItems.get(i), bullet)) {
                    if (boxItems.get(i).getStatus() % 3 == 2) {
                        burst = new Burst(boxItems.get(i).getX(), boxItems.get(i).getY(),
                                contra.getDelay(), Burst.TYPE_BURST_1);
                        bursts.add(burst);
                        powerUpItem = new PowerUpItem(boxItems.get(i).getX(), boxItems.get(i).getY(),
                                contra.getDelay(),boxItems.get(i).getType());
                        powerUpItems.add(powerUpItem);
                        boxItems.remove(boxItems.get(i));
                        canPlay(soundSoldierDie).play();
                        canPlay(soundSoldierDie).loop(1);
                    } else {
                        canPlay(soundBillBulletIntersectBox).play();
                        canPlay(soundBillBulletIntersectBox).loop(1);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canFlyingItemDestroyed(Bullet bullet){
        if(!flyingItems.isEmpty()){
            for (int i = 0; i < flyingItems.size(); i++){
                if(isCollisionWithBullet(flyingItems.get(i),bullet)){
                    burst = new Burst(flyingItems.get(i).getX(), flyingItems.get(i).getY(),
                            contra.getDelay(), Burst.TYPE_BURST_1);
                    bursts.add(burst);
                    powerUpItem = new PowerUpItem(flyingItems.get(i).getX(), flyingItems.get(i).getY(),
                            contra.getDelay(),flyingItems.get(i).getType());
                    powerUpItems.add(powerUpItem);
                    flyingItems.remove(flyingItems.get(i));
                    canPlay(soundSoldierDie).play();
                    canPlay(soundSoldierDie).loop(1);
                    return true;
                }
            }
        }
        return false;
    }

    public void tryObjDestroyed() {
        if (!bullets.isEmpty()) {
            for (int i = 0; i < bullets.size(); i++) {
                if (bullets.get(i).getType() == BaseItem.TYPE_BILL_BULLET_1
                        || bullets.get(i).getType() == BaseItem.TYPE_BILL_BULLET_2
                        || bullets.get(i).getType() == BaseItem.TYPE_BILL_BULLET_3) {
                    if (canSoldierDestroyed(bullets.get(i))) {
                        bullets.remove(bullets.get(i));
                        i--;
                        return;
                    }
                    if (canEnemyDestroyed(bullets.get(i))) {
                        bullets.remove(bullets.get(i));
                        i--;
                        return;
                    }
                    if (canBoxItemDestroyed(bullets.get(i))) {
                        bullets.remove(bullets.get(i));
                        i--;
                        return;
                    }
                    if(canFlyingItemDestroyed(bullets.get(i))){
                        bullets.remove(bullets.get(i));
                        i--;
                        return;
                    }
                } else {
                    if (canContraDied(bullets.get(i))) {
                        bullets.remove(bullets.get(i));
                        i--;
                        return;
                    }
                }
            }
        }
    }

    public void tryContraInserctItem(){
        if (!powerUpItems.isEmpty()){
            for (int i = 0; i < powerUpItems.size(); i++){
                if (isCollisionWithOtherObj(contra,powerUpItems.get(i))){
                    contra.setTypeBullet(powerUpItems.get(i).getType());
                    powerUpItems.remove(powerUpItems.get(i));
                    i--;
                    soundIntersectItem.play();
                    soundIntersectItem.loop(1);
                }
            }
        }
    }

    public void canCollisionWithContra(BaseItem obj) {
        if (isCollisionWithOtherObj(contra, obj) && contra.getTimeContraImmotal() == 0) {
            burst = new Burst(contra.getX(), contra.getY(),
                    contra.getDelay(), Burst.TYPE_BURST_1);
            bursts.add(burst);
            soundBillDie.play();
            numberBillLife --;
            contra.died();
        }
    }

    public void countTimeReloadBullet() {
        if (countTimeContraFire > 0) {
            countTimeContraFire -= 250;
        }
    }

    public void countTime(long numberOfSleep) {
        contra.setTime(numberOfSleep);
        for (int i = 0; i < bursts.size(); i++) {
            bursts.get(i).setTime(numberOfSleep);
        }
        for (int i = 0; i < soldiers.size(); i++) {
            soldiers.get(i).setTime(numberOfSleep);
        }
        for (int i = 0; i < boxItems.size(); i ++){
            boxItems.get(i).setStatus(numberOfSleep);
        }
    }

    public void runAudio() {
        if (endGame){
            soundEndGame.play();
        }
    }

    public ISound canPlay(ISound[] iSound) {
        int i = 0;
        while (iSound[i].isRunning() && i < 4) {
            i++;
        }
        iSound[i].loop(1);
        return iSound[i];
    }

    public long countScore() {
        return score;
    }

    public void creatFlyingItem(){
        if (map.getX() == -500){
            FlyingItem flyingItem = new FlyingItem(-50,200,contra.getDelay(),1);
            flyingItems.add(flyingItem);
        }
        if (map.getX() == -3500){
            FlyingItem flyingItem = new FlyingItem(-50,200,contra.getDelay(),1);
            flyingItems.add(flyingItem);
        }
        if (map.getX() == - 7200){
            FlyingItem flyingItem = new FlyingItem(-50,100,contra.getDelay(),1);
            flyingItems.add(flyingItem);
            FlyingItem flyingItem2 = new FlyingItem(-50,200,contra.getDelay(),2);
            flyingItems.add(flyingItem2);
        }
    }
}
