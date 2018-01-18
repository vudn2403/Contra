package com.vudn.contra.ui;


import com.vudn.contra.manager.GameManager;
import com.vudn.contra.manager.ImageStore;
import com.vudn.contra.model.ISound;
import javafx.scene.layout.Border;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuPanel extends BasePanel implements ActionListener{
    private static final String ACTION_BUTTON_PLAY = "action_button_play";
    private static final String ACTION_BUTTON_HELP = "action_button_help";
    private static final String ACTION_BUTTON_EXIT= "action_button_exit";

    private JButton btnPlay;
    private JButton btnHelp;
    private JButton btnExit;
    private ImageIcon iconPlay1;
    private ImageIcon iconPlay2;
    private ImageIcon iconHelp1;
    private ImageIcon iconHelp2;
    private ImageIcon iconExit1;
    private ImageIcon iconExit2;

    public MenuPanel() {
        super();
    }


    @Override
    public void initializeContainer() {
        setBackground(Color.BLACK);
        setLayout(null);
    }

    @Override
    public void initializeComponents() {
        iconPlay1 = new ImageIcon(ImageStore.IMG_BUTTON_PLAY_1);
        iconPlay2 = new ImageIcon(ImageStore.IMG_BUTTON_PLAY_2);
        iconHelp1 = new ImageIcon(ImageStore.IMG_BUTTON_HELP_1);
        iconHelp2 = new ImageIcon(ImageStore.IMG_BUTTON_HELP_2);
        iconExit1 = new ImageIcon(ImageStore.IMG_BUTTON_EXIT_1);
        iconExit2 = new ImageIcon(ImageStore.IMG_BUTTON_EXIT_2);
        btnPlay = new JButton(iconPlay1);
        btnPlay.setBorder(null);
        btnPlay.setActionCommand(ACTION_BUTTON_PLAY);
        btnPlay.setBounds(250, 450, iconPlay2.getIconWidth(), iconPlay2.getIconHeight());
        add(btnPlay);
        btnHelp = new JButton(iconHelp1);
        btnHelp.setActionCommand(ACTION_BUTTON_HELP);
        btnHelp.setBorder(null);
        btnHelp.setBounds(250, 525, iconHelp1.getIconWidth(),iconHelp1.getIconHeight() );
        add(btnHelp);
        btnExit = new JButton(iconExit1);
        btnExit.setActionCommand(ACTION_BUTTON_EXIT);
        btnExit.setBorder(null);
        btnExit.setBounds(250, 600, iconExit2.getIconWidth(),iconExit2.getIconHeight());
        add(btnExit);
    }

    @Override
    public void registerListener() {
        btnPlay.addActionListener(this);
        btnHelp.addActionListener(this);
        btnExit.addActionListener(this);
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnPlay.setIcon(iconPlay1);
                btnHelp.setIcon(iconHelp1);
                btnExit.setIcon(iconExit1);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (btnPlay.getX() <= e.getX() && btnPlay.getY() <= e.getY()
                        && btnPlay.getX() + btnPlay.getWidth() >= e.getX()
                        &&btnPlay.getY() + btnPlay.getHeight() >= e.getY()){
                    btnPlay.setIcon(iconPlay2);
                    btnPlay.setBorder(null);
                }

                if (btnHelp.getX() < e.getX() && btnHelp.getY() < e.getY()
                        && btnHelp.getX() + btnHelp.getWidth() >= e.getX()
                        &&btnHelp.getY() + btnHelp.getHeight() >= e.getY()){
                    btnHelp.setIcon(iconHelp2);
                }

                if (btnExit.getX() < e.getX() && btnExit.getY() < e.getY()
                        && btnExit.getX() + btnExit.getWidth() >= e.getX()
                        &&btnExit.getY() + btnExit.getHeight() >= e.getY()){
                    btnExit.setIcon(iconExit2);
                }
            }
        };
        addMouseListener(mouseAdapter);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.drawImage(ImageStore.IMG_MENU_BACKGROUND,0,0,Gui.WIDTH_FRAME,Gui.HEIGHT_FRAME,null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case ACTION_BUTTON_PLAY:
                onScreenSwitchListener.switchScreen(Gui.SCREEN_GAME_PLAY);
                break;

            case ACTION_BUTTON_HELP:
                onScreenSwitchListener.switchScreen(Gui.SCREEN_HELP);
                break;

            case ACTION_BUTTON_EXIT:
                onScreenSwitchListener.switchScreen(Gui.SCREEN_EXIT);
                break;

            default:
                break;
        }
    }
}
