package com.vudn.contra.model;

import com.vudn.contra.manager.ImageStore;
import com.vudn.contra.ui.Gui;

import java.awt.*;

public class Contra extends BaseItem {
    public static final int ACTIVITY_ON_THE_GROUND = 10;
    public static final int ACTIVITY_OVER_HEAD = 11;
    public static final int ACTIVITY_UNDER_WATER = 12;

    public static final int ACTION_STAND = 21;
    public static final int ACTION_LIE = 22;
    public static final int ACTION_RUN = 23;
    public static final int ACTION_JUMP = 24;
    public static final int ACTION_FIRE = 25;
    public static final int ACTION_RUN_AND_FIRE = 26;

    protected int activity;
    protected int action;
    protected int timeContraRunFire;
    protected int timeContraJump;
    protected int timeContraSwim;
    protected int gunOrientation;
    protected int contraOrientation;
    protected int typeBullet;
    protected int hightJump;
    private int timeContraImmotal;


    public Contra(int x, int y, int contraOrientation, int gunOrientation, int delay) {
        super(x, y, delay);
        this.contraOrientation = contraOrientation;
        this.gunOrientation = gunOrientation;
        rectangle = new Rectangle(x, y, 50, SIZE);
        typeBullet = TYPE_BILL_BULLET_1;
        timeContraRunFire = 0;
        timeContraJump = 0;
        timeContraSwim = 0;
        hightJump = 150;
        timeContraImmotal = 3;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getTypeBullet() {
        return typeBullet;
    }

    public void setTypeBullet(int typeBullet) {
        this.typeBullet = typeBullet;
    }

    public int getTimeContraRunFire() {
        return timeContraRunFire;
    }

    public void setTimeContraRunFire(int timeContraRunFire) {
        this.timeContraRunFire = timeContraRunFire;
    }

    public int getTimeContraJump() {
        return timeContraJump;
    }

    public void setTimeContraJump(int timeContraJump) {
        this.timeContraJump = timeContraJump;
    }

    public int getGunOrientation() {
        return gunOrientation;
    }

    public void setGunOrientation(int gunOrientation) {
        this.gunOrientation = gunOrientation;
    }

    public int getContraOrientation() {
        return contraOrientation;
    }

    public void setContraOrientation(int contraOrientation) {
        if (this.contraOrientation == contraOrientation) {
            return;
        }
        this.contraOrientation = contraOrientation;
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

    public int getTimeContraImmotal() {
        return timeContraImmotal;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
//        graphics2D.drawRect((int) rectangle.getX(), (int) rectangle.getY(),
//                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        switch (activity) {
            case ACTIVITY_OVER_HEAD:
                drawActivityOverHead(graphics2D);
                break;

            case ACTIVITY_ON_THE_GROUND:
                drawActivityOnTheGround(graphics2D);
                break;

            case ACTIVITY_UNDER_WATER:
                drawActivityUnderWater(graphics2D);
                break;

            default:
                break;
        }
    }

    public void drawActivityOnTheGround(Graphics2D graphics2D) {
        switch (gunOrientation) {
            case LEFT:
                switch (action) {
                    case ACTION_STAND:
                        graphics2D.drawImage(ImageStore.IMG_BILL_RUN_FIRE_LEFT_1,
                                x - 25, y, SIZE - 25, SIZE, null);
                        break;

                    case ACTION_FIRE:
                        if (timeContraRunFire % 2 == 0) {
                            graphics2D.drawImage(ImageStore.IMG_BILL_RUN_FIRE_LEFT_1,
                                    x - 25, y + 5, SIZE - 25, SIZE - 5, null);
                        } else {
                            graphics2D.drawImage(ImageStore.IMG_BILL_RUN_FIRE_LEFT_1,
                                    x - 25, y, SIZE - 25, SIZE, null);
                        }
                        break;

                    case ACTION_RUN:
                        switch (timeContraRunFire % 5) {
                            case 0:
                                graphics2D.drawImage(ImageStore.IMG_BILL_RUN_LEFT_1,
                                        x, y, SIZE - 35, SIZE, null);
                                break;
                            case 1:
                                graphics2D.drawImage(ImageStore.IMG_BILL_RUN_LEFT_2,
                                        x, y, SIZE - 35, SIZE, null);
                                break;
                            case 2:
                                graphics2D.drawImage(ImageStore.IMG_BILL_RUN_LEFT_3,
                                        x, y, SIZE - 35, SIZE, null);
                                break;

                            case 3:
                                graphics2D.drawImage(ImageStore.IMG_BILL_RUN_LEFT_4,
                                        x, y, SIZE - 35, SIZE, null);
                                break;

                            case 4:
                                graphics2D.drawImage(ImageStore.IMG_BILL_RUN_LEFT_5,
                                        x, y, SIZE - 35, SIZE, null);
                                break;

                            default:
                                break;
                        }
                        break;

                    case ACTION_RUN_AND_FIRE:
                        switch (timeContraRunFire % 3) {
                            case 0:
                                graphics2D.drawImage(ImageStore.IMG_BILL_RUN_FIRE_LEFT_1,
                                        x - 25, y, SIZE - 15, SIZE, null);
                                break;
                            case 1:
                                graphics2D.drawImage(ImageStore.IMG_BILL_RUN_FIRE_LEFT_2,
                                        x - 25, y, SIZE - 15, SIZE, null);
                                break;
                            case 2:
                                graphics2D.drawImage(ImageStore.IMG_BILL_RUN_FIRE_LEFT_3,
                                        x - 25, y, SIZE - 15, SIZE, null);
                                break;

                            default:
                                break;
                        }
                        break;
                }
                break;

            case UP_LEFT:
                switch (timeContraRunFire %3) {
                    case 0:
                        graphics2D.drawImage(ImageStore.IMG_BILL_RUN_FIRE_DIAGONAL_UP_LEFT_1,
                                x, y - 5, SIZE - 25, SIZE + 5, null);
                        break;
                    case 1:
                        graphics2D.drawImage(ImageStore.IMG_BILL_RUN_FIRE_DIAGONAL_UP_LEFT_2,
                                x + 10, y - 5, SIZE - 25, SIZE + 5, null);
                        break;
                    case 2:
                        graphics2D.drawImage(ImageStore.IMG_BILL_RUN_FIRE_DIAGONAL_UP_LEFT_3,
                                x + 10, y - 5, SIZE - 25, SIZE + 5, null);
                        break;

                    default:
                        break;
                }
                break;

            case UP:
                if (contraOrientation == LEFT) {
                    graphics2D.drawImage(ImageStore.IMG_BILL_FIRE_UP_LEFT,
                            x, y - 30, SIZE / 2, SIZE + 35, null);
                }
                if (contraOrientation == RIGHT) {
                    graphics2D.drawImage(ImageStore.IMG_BILL_FIRE_UP_RIGHT,
                            x, y - 30, SIZE / 2, SIZE + 35, null);
                }
                break;

            case UP_RIGHT:
                switch (timeContraRunFire % 3) {
                    case 0:
                        graphics2D.drawImage(ImageStore.IMG_BILL_RUN_FIRE_DIAGONAL_UP_RIGHT_1,
                                x, y - 5, SIZE - 25, SIZE + 5, null);
                        break;
                    case 1:
                        graphics2D.drawImage(ImageStore.IMG_BILL_RUN_FIRE_DIAGONAL_UP_RIGHT_2,
                                x - 10, y - 5, SIZE - 25, SIZE + 5, null);
                        break;
                    case 2:
                        graphics2D.drawImage(ImageStore.IMG_BILL_RUN_FIRE_DIAGONAL_UP_RIGHT_3,
                                x - 10, y - 5, SIZE - 25, SIZE + 5, null);
                        break;

                    default:
                        break;
                }
                break;

            case RIGHT:
                switch (action) {
                    case ACTION_STAND:
                        graphics2D.drawImage(ImageStore.IMG_BILL_RUN_FIRE_RIGHT_1,
                                x, y, SIZE - 25, SIZE, null);
                        break;

                    case ACTION_FIRE:
                        if (timeContraRunFire % 2 == 0) {
                            graphics2D.drawImage(ImageStore.IMG_BILL_RUN_FIRE_RIGHT_1,
                                    x, y + 5, SIZE - 25, SIZE - 5, null);
                        } else {
                            graphics2D.drawImage(ImageStore.IMG_BILL_RUN_FIRE_RIGHT_1,
                                    x, y, SIZE - 25, SIZE, null);
                        }
                        break;
                    case ACTION_RUN:
                        switch (timeContraRunFire % 5 ) {
                            case 0:
                                graphics2D.drawImage(ImageStore.IMG_BILL_RUN_RIGHT_1,
                                        x, y, SIZE - 35, SIZE, null);
                                break;
                            case 1:
                                graphics2D.drawImage(ImageStore.IMG_BILL_RUN_RIGHT_2,
                                        x, y, SIZE - 35, SIZE, null);
                                break;
                            case 2:
                                graphics2D.drawImage(ImageStore.IMG_BILL_RUN_RIGHT_3,
                                        x, y, SIZE - 35, SIZE, null);
                                break;

                            case 3:
                                graphics2D.drawImage(ImageStore.IMG_BILL_RUN_RIGHT_4,
                                        x, y, SIZE - 35, SIZE, null);
                                break;

                            case 4:
                                graphics2D.drawImage(ImageStore.IMG_BILL_RUN_RIGHT_5,
                                        x, y, SIZE - 35, SIZE, null);
                                break;

                            default:
                                break;
                        }
                        break;

                    case ACTION_RUN_AND_FIRE:
                        switch (timeContraRunFire % 3) {
                            case 0:
                                graphics2D.drawImage(ImageStore.IMG_BILL_RUN_FIRE_RIGHT_1,
                                        x, y, SIZE - 25, SIZE, null);
                                break;
                            case 1:
                                graphics2D.drawImage(ImageStore.IMG_BILL_RUN_FIRE_RIGHT_2,
                                        x, y, SIZE - 25, SIZE, null);
                                break;
                            case 2:
                                graphics2D.drawImage(ImageStore.IMG_BILL_RUN_FIRE_RIGHT_3,
                                        x, y, SIZE - 25, SIZE, null);
                                break;

                            default:
                                break;
                        }
                        break;
                }

                break;

            case DOWN_RIGHT:
                switch (timeContraRunFire % 3) {
                    case 0:
                        graphics2D.drawImage(ImageStore.IMG_BILL_RUN_FIRE_DIAGONAL_DOWN_RIGHT_1,
                                x, y, SIZE - 15, SIZE, null);
                        break;

                    case 1:
                        graphics2D.drawImage(ImageStore.IMG_BILL_RUN_FIRE_DIAGONAL_DOWN_RIGHT_2,
                                x, y, SIZE - 15, SIZE, null);
                        break;

                    case 2:
                        graphics2D.drawImage(ImageStore.IMG_BILL_RUN_FIRE_DIAGONAL_DOWN_RIGHT_3,
                                x, y, SIZE - 15, SIZE, null);
                        break;

                    default:
                        break;
                }
                break;

            case DOWN:
                if (contraOrientation == LEFT) {
                    graphics2D.drawImage(ImageStore.IMG_BILL_LIE_FIRE_LEFT,
                            x, y + 50, SIZE, SIZE / 2, null);
                } else if (contraOrientation == RIGHT) {
                    graphics2D.drawImage(ImageStore.IMG_BILL_LIE_FIRE_RIGHT,
                            x - 25, y + 50, SIZE, SIZE / 2, null);
                }
                break;

            case DOWN_LEFT:
                switch (timeContraRunFire % 3) {
                    case 0:
                        graphics2D.drawImage(ImageStore.IMG_BILL_RUN_FIRE_DIAGONAL_DOWN_LEFT_1,
                                x - 15, y, SIZE - 15, SIZE, null);
                        break;

                    case 1:
                        graphics2D.drawImage(ImageStore.IMG_BILL_RUN_FIRE_DIAGONAL_DOWN_LEFT_2,
                                x - 15, y, SIZE - 15, SIZE, null);
                        break;

                    case 2:
                        graphics2D.drawImage(ImageStore.IMG_BILL_RUN_FIRE_DIAGONAL_DOWN_LEFT_3,
                                x - 15, y, SIZE - 15, SIZE, null);
                        break;

                    default:
                        break;
                }
                break;

            default:
                break;
        }
    }

    public void drawActivityOverHead(Graphics2D graphics2D) {
        if (contraOrientation == LEFT) {
            switch (timeContraJump % 4) {
                case 0:
                    graphics2D.drawImage(ImageStore.IMG_BILL_JUMP_LEFT_1,
                            x, y, SIZE / 2, SIZE / 2, null);
                    break;

                case 1:
                    graphics2D.drawImage(ImageStore.IMG_BILL_JUMP_LEFT_2,
                            x, y, SIZE / 2, SIZE / 2, null);
                    break;

                case 2:
                    graphics2D.drawImage(ImageStore.IMG_BILL_JUMP_LEFT_3,
                            x, y, SIZE / 2, SIZE / 2, null);
                    break;

                case 3:
                    graphics2D.drawImage(ImageStore.IMG_BILL_JUMP_LEFT_4,
                            x, y, SIZE / 2, SIZE / 2, null);
                    break;

                default:
                    break;
            }
        }
        if (contraOrientation == RIGHT) {
            switch (timeContraJump % 4) {
                case 0:
                    graphics2D.drawImage(ImageStore.IMG_BILL_JUMP_RIGHT_1,
                            x, y, SIZE / 2, SIZE / 2, null);
                    break;

                case 1:
                    graphics2D.drawImage(ImageStore.IMG_BILL_JUMP_RIGHT_2,
                            x, y, SIZE / 2, SIZE / 2, null);
                    break;

                case 2:
                    graphics2D.drawImage(ImageStore.IMG_BILL_JUMP_RIGHT_3,
                            x, y, SIZE / 2, SIZE / 2, null);
                    break;

                case 3:
                    graphics2D.drawImage(ImageStore.IMG_BILL_JUMP_RIGHT_4,
                            x, y, SIZE / 2, SIZE / 2, null);
                    break;

                default:
                    break;
            }
        }
    }

    public void drawActivityUnderWater(Graphics2D graphics2D) {
        if (timeContraSwim == 0) {
            graphics2D.drawImage(ImageStore.IMG_BILL_SWIM, x, y + 25, 50, 50, null);
        } else {
            switch (gunOrientation) {
                case LEFT:
                    graphics2D.drawImage(ImageStore.IMG_BILL_SWIM_FIRE_RIGHT,
                            x, y, SIZE - 25, SIZE - 25, null);
                    break;
                case UP:
                    graphics2D.drawImage(ImageStore.IMG_BILL_SWIM_FIRE_UP,
                            x, y - 15, SIZE - 25, SIZE - 25, null);
                    break;
                case UP_RIGHT:
                    graphics2D.drawImage(ImageStore.IMG_BILL_SWIM_FIRE_UP_RIGHT,
                            x, y - 5, SIZE - 25, SIZE - 25, null);
                    break;
                case RIGHT:
                    graphics2D.drawImage(ImageStore.IMG_BILL_SWIM_FIRE_RIGHT,
                            x, y, SIZE - 25, SIZE - 25, null);
                    break;
                case DOWN:
                    if (timeContraSwim == 1) {
                        graphics2D.drawImage(ImageStore.IMG_BILL_SWIM_DOWN,
                                x, y + 35, SIZE / 2, SIZE / 4, null);
                    }
                    if (timeContraSwim == 2) {
                        graphics2D.drawImage(ImageStore.IMG_BILL_SWIM_DOWN,
                                x, y + 40, SIZE / 2, SIZE / 4, null);
                    }
                    break;

                default:
                    break;

            }
        }

    }

    public void move(long numberOfSleep) {
        if (numberOfSleep % delay != 0) {
            return;
        }
        switch (contraOrientation) {
            case LEFT:
                x--;
                rectangle.setLocation(x - 1, y);
                break;

            case RIGHT:
                x++;
                rectangle.setLocation(x + 1, y);
                break;

            default:
                break;
        }
    }

    public void fall(long numberOfSleep) {
        if (hightJump <= 150) {
            return;
        }
        int gravitasion = 0;
        if (timeContraJump > 5) {
            gravitasion = 1;
        }
        if (numberOfSleep % (delay - gravitasion) != 0) {
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

        if (y > Gui.HEIGHT_FRAME) {
            timeContraJump = 0;
            y = -SIZE;
            rectangle.setLocation(x, y);
        }
    }

    public void jump(long numberOfSleep) {
        if (numberOfSleep % (delay) != 0) {
            return;
        }
        if (hightJump == 0 && gunOrientation == DOWN) {
            y++;
            hightJump = 150;
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
            return;
        }
        if (hightJump <= 150) {
            y--;
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
            hightJump++;
        }

    }

    public void died(){
        x = 100;
        y = -SIZE;
        typeBullet = TYPE_BILL_BULLET_1;
        timeContraJump = 0;
        timeContraImmotal = 3;
    }

    public void setTime(long numberOfSleep) {
        switch (activity) {
            case ACTIVITY_OVER_HEAD:
                if (numberOfSleep % 75 != 0) {
                    return;
                }

                if (timeContraJump < Integer.MAX_VALUE) {
                    timeContraJump++;
                }
                break;

            case ACTIVITY_ON_THE_GROUND:
                switch (action) {
                    case ACTION_STAND:
                        timeContraRunFire = 0;
                        timeContraSwim = 0;
                        setGunOrientation(contraOrientation);
                        rectangle.setLocation(x, y);
                        break;

                    case ACTION_RUN:
                    case ACTION_FIRE:
                    case ACTION_RUN_AND_FIRE:
                        if (numberOfSleep % 100 != 0) {
                            return;
                        }
                        if (timeContraRunFire < Integer.MAX_VALUE) {
                            timeContraRunFire++;
                        } else {
                            timeContraRunFire = 0;
                        }
                        break;
                    case ACTION_LIE:
                        rectangle.setLocation(x - 50, y + 50);
                        break;

                    case ACTION_JUMP:
                        timeContraJump = 0;
                        hightJump = 0;
                        break;

                    default:
                        break;
                }
                break;

            case ACTIVITY_UNDER_WATER:
                if (numberOfSleep % 300 != 0) {
                    return;
                }
                if (timeContraSwim < 2) {
                    timeContraSwim++;
                } else {
                    timeContraSwim = 1;
                }
                break;

            default:
                break;
        }
        if (numberOfSleep % 500 != 0){
            return;
        }else if (timeContraImmotal > 0){
            timeContraImmotal --;
        }
    }
}
