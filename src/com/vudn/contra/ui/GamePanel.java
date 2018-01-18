package com.vudn.contra.ui;

import com.vudn.contra.manager.GameManager;
import com.vudn.contra.model.BaseItem;
import com.vudn.contra.model.Contra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.BitSet;

public class GamePanel extends BasePanel implements Runnable {
    private GameManager gameManager;
    private BitSet bitSet;
    private JLabel lbScore;
    private long score;
    private boolean gameRun;

    public GamePanel() {
        super();
        startGame();
    }

    @Override
    public void initializeContainer() {
        setLayout(null);
        setBackground(Color.WHITE);
        setFocusable(true);
    }

    public void focus() {
        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    public void initializeComponents() {
        gameManager = new GameManager();
        bitSet = new BitSet();
        lbScore = new JLabel();
        Font font = new Font("Arial", Font.BOLD, 32);
        lbScore.setFont(font);
        lbScore.setForeground(Color.WHITE);
        lbScore.setText("Score: 0");
        lbScore.setBounds(600, 5,
                getFontMetrics(font).stringWidth("Score: 99999999"),
                getFontMetrics(font).getHeight());
        add(lbScore);
    }

    @Override
    public void registerListener() {
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                bitSet.set(e.getKeyCode(), true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                bitSet.set(e.getKeyCode(), false);
                if (!(bitSet.get(KeyEvent.VK_RIGHT) && bitSet.get(KeyEvent.VK_LEFT)
                        && bitSet.get(KeyEvent.VK_UP) && bitSet.get(KeyEvent.VK_DOWN))) {
                    gameManager.setContraAction(Contra.ACTION_STAND);
                }

            }
        };
        addKeyListener(keyAdapter);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        gameManager.drawMapItem(graphics2D);
        gameManager.drawContra(graphics2D);
        gameManager.drawBullet(graphics2D);
        gameManager.drawBillLife(graphics2D);
    }

    public void startGame() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        long numberOfSleep = 0;
        while (true) {
            if (gameManager.endGame == true) {
                JOptionPane.showMessageDialog(this, "Win! Your Score: " + gameManager.countScore(), "Message", JOptionPane.INFORMATION_MESSAGE);
                onScreenSwitchListener.switchScreen(Gui.SCREEN_MENU);
                gameRun = false;
                return;
            }
            if (gameManager.numberBillLife < 0) {
                JOptionPane.showMessageDialog(this, "GameOver! ", "Message", JOptionPane.INFORMATION_MESSAGE);
                onScreenSwitchListener.switchScreen(Gui.SCREEN_MENU);
                gameRun = false;
                return;
            }
            if (gameRun = true) {
                numberOfSleep++;
                if (numberOfSleep == Long.MAX_VALUE) {
                    numberOfSleep = 0;
                }
                if (score < gameManager.countScore()) {
                    if (numberOfSleep % 25 == 0) {
                        if (score < Long.MAX_VALUE) {
                            score++;
                        }
                    }
                    lbScore.setText("Score: " + String.valueOf(score));
                }
                if (numberOfSleep % 10 == 0) {
                    gameManager.tryObjDestroyed();
                    gameManager.tryContraInserctItem();
                }
                if (numberOfSleep % 100 == 0) {
                    gameManager.tryEnemyAction();
                    gameManager.countTimeReloadBullet();
                }
                gameManager.countTime(numberOfSleep);
                gameManager.moveBullet(numberOfSleep);
                gameManager.tryObjCanStand(numberOfSleep);
                gameManager.moveContraWhenJump(numberOfSleep);
                gameManager.moveSoldier(numberOfSleep);
                gameManager.movePowerUpItem(numberOfSleep);
                gameManager.creatFlyingItem();

                if (bitSet.get(KeyEvent.VK_S) && bitSet.get(KeyEvent.VK_A)) {
                    gameManager.setContraMoveOrientation(BaseItem.LEFT);
                    gameManager.setContraFireOrientation(BaseItem.DOWN_LEFT);
                    gameManager.moveContra(numberOfSleep);
                } else if (bitSet.get(KeyEvent.VK_S) && bitSet.get(KeyEvent.VK_D)) {
                    gameManager.setContraFireOrientation(BaseItem.DOWN_RIGHT);
                    gameManager.setContraMoveOrientation(BaseItem.RIGHT);
                    gameManager.moveContra(numberOfSleep);
                } else if (bitSet.get(KeyEvent.VK_W) && bitSet.get(KeyEvent.VK_A)) {
                    gameManager.setContraFireOrientation(BaseItem.UP_LEFT);
                    gameManager.setContraMoveOrientation(BaseItem.LEFT);
                    gameManager.moveContra(numberOfSleep);
                } else if (bitSet.get(KeyEvent.VK_W) && bitSet.get(KeyEvent.VK_D)) {
                    gameManager.setContraFireOrientation(BaseItem.UP_RIGHT);
                    gameManager.setContraMoveOrientation(BaseItem.RIGHT);
                    gameManager.moveContra(numberOfSleep);
                } else if (bitSet.get(KeyEvent.VK_A)) {
                    gameManager.setContraFireOrientation(BaseItem.LEFT);
                    gameManager.setContraMoveOrientation(BaseItem.LEFT);
                    gameManager.moveContra(numberOfSleep);
                } else if (bitSet.get(KeyEvent.VK_W)) {
                    gameManager.setContraFireOrientation(BaseItem.UP);
                } else if (bitSet.get(KeyEvent.VK_D)) {
                    gameManager.setContraFireOrientation(BaseItem.RIGHT);
                    gameManager.setContraMoveOrientation(BaseItem.RIGHT);
                    gameManager.moveContra(numberOfSleep);
                } else if (bitSet.get(KeyEvent.VK_S)) {
                    gameManager.setContraFireOrientation(BaseItem.DOWN);
                }
                if (bitSet.get(KeyEvent.VK_J)) {
                    gameManager.fireFromContra();
                    gameManager.setContraAction(Contra.ACTION_FIRE);
                    if (bitSet.get(KeyEvent.VK_A) == true || bitSet.get(KeyEvent.VK_D) == true) {
                        gameManager.setContraAction(Contra.ACTION_RUN_AND_FIRE);
                    }
                }
                if (bitSet.get(KeyEvent.VK_K)) {
                    gameManager.jumpContra();
                }
                repaint();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}