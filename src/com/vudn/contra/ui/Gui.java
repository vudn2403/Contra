package com.vudn.contra.ui;

import com.vudn.contra.manager.ImageStore;
import com.vudn.contra.model.ISound;
import com.vudn.contra.model.SoundWav;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Gui extends JFrame implements Setup, OnScreenSwitchListener {
    public static final int WIDTH_FRAME = 960;
    public static final int HEIGHT_FRAME = 720;
    public static final String SCREEN_MENU = "screen_menu";
    public static final String SCREEN_BACK = "screen_back_menu";
    public static final String SCREEN_GAME_PLAY = "screen_game";
    public static final String SCREEN_HELP = "screen_help";
    public static final String SCREEN_EXIT = "screen_exit";

    private ISound soundBeginGame;
    private ISound soundMenuAndHelp;
    private ISound soundGame;

    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private HelpPanel helpPanel;

    public Gui() {
        initializeContainer();
        initializeComponents();
        registerListener();
        creatSound();
        soundGame.play();
    }

    public void creatSound() {
        soundBeginGame = new SoundWav("sound_begin_game");
        soundMenuAndHelp = new SoundWav("sound_menu_and_help_panel");
        soundGame = new SoundWav("sound_game");
    }

    @Override
    public void initializeContainer() {
        setTitle("Contra Game");
        setLayout(new CardLayout());
        getContentPane().setPreferredSize(new Dimension(WIDTH_FRAME, HEIGHT_FRAME));
        pack();
        setIconImage(ImageStore.IMG_ICON);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    @Override
    public void initializeComponents() {
        menuPanel = new MenuPanel();
        menuPanel.setOnScreenSwitchListener(this);
        add(menuPanel);
    }

    @Override
    public void registerListener() {
        WindowAdapter windowAdapter = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(null,
                        "Bạn muốn thoát game",
                        "Thông báo",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        };
        addWindowListener(windowAdapter);
    }

    @Override
    public void switchScreen(String name) {
        switch (name) {
            case SCREEN_MENU:
                soundGame.stop();
                remove(gamePanel);
                menuPanel = new MenuPanel();
                menuPanel.setOnScreenSwitchListener(this);
                add(menuPanel);
                revalidate();
                soundGame.play();
                break;

            case SCREEN_GAME_PLAY:
                remove(menuPanel);
                soundGame.stop();
                gamePanel = new GamePanel();
                gamePanel.setOnScreenSwitchListener(this);
                add(gamePanel);
                revalidate();
                gamePanel.focus();
                soundBeginGame.play();
                if (!soundBeginGame.isRunning()){
                    soundGame.play();
                    soundGame.loop(Clip.LOOP_CONTINUOUSLY);
                }
                break;

            case SCREEN_HELP:
                soundGame.stop();
                remove(menuPanel);
                helpPanel = new HelpPanel();
                helpPanel.setOnScreenSwitchListener(this);
                add(helpPanel);
                revalidate();
                soundMenuAndHelp.play();
                soundMenuAndHelp.loop(Clip.LOOP_CONTINUOUSLY);
                break;

            case SCREEN_BACK:
                soundMenuAndHelp.stop();
                remove(helpPanel);
                menuPanel.setOnScreenSwitchListener(this);
                add(menuPanel);
                revalidate();
                soundGame.play();
                break;

            case SCREEN_EXIT:
                System.exit(0);
                break;

            default:
                break;
        }
    }
}
