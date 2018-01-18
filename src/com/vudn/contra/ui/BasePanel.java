package com.vudn.contra.ui;

import javax.swing.*;

public abstract class BasePanel extends JPanel implements Setup {
    protected OnScreenSwitchListener onScreenSwitchListener;
    public BasePanel() {
        initializeContainer();
        initializeComponents();
        registerListener();
    }

    public void setOnScreenSwitchListener(OnScreenSwitchListener onScreenSwitchListener) {
        this.onScreenSwitchListener = onScreenSwitchListener;
    }
}
