package com.vudn.contra.ui;

import com.vudn.contra.manager.ImageStore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpPanel extends BasePanel implements ActionListener{
    private JButton btnOK;
    private ImageIcon iconOK;

    public HelpPanel() {
        super();
    }

    @Override
    public void initializeContainer() {
        setLayout(null);
        setBackground(Color.BLACK);
    }

    @Override
    public void initializeComponents() {
        iconOK = new ImageIcon(ImageStore.IMG_BUTTON_OKE_1);
        btnOK = new JButton(iconOK);
        btnOK.setBounds(300, 665, iconOK.getIconWidth(),iconOK.getIconHeight());
        add(btnOK);
    }

    @Override
    public void registerListener() {
        btnOK.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        onScreenSwitchListener.switchScreen(Gui.SCREEN_BACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.drawImage(ImageStore.IMG_MENU_BACKGROUND,0,0,Gui.WIDTH_FRAME, Gui.HEIGHT_FRAME,null);
        graphics2D.drawImage(ImageStore.IMG_HELP,50, 450, 200,250,null);
    }
}
