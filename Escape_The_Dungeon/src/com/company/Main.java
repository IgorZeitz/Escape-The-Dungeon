package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	//***Game window
        JFrame gameWindow = new JFrame();
        gameWindow.setTitle("Escape The Dungeon");
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setLocationRelativeTo(null);

        GamePanel gamePanel = new GamePanel();
        gameWindow.add(gamePanel);


        gameWindow.pack();

            gameWindow.setVisible(true);

        gamePanel.startGameThread();

    }
}
