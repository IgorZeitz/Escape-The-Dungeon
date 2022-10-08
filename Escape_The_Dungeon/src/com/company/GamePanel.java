package com.company;

import javax.swing.*;

import java.awt.*;

public class GamePanel extends JPanel{
    //Making a game screen with sections - 16x16pixels
    final int windowHeight = 300;
    final int widowLength = 200;
    //***
    public GamePanel() {
        this.setPreferredSize(new Dimension(windowHeight, widowLength));
        this.setBackground(Color.black);
        this.setVisible(true);
    }
}
