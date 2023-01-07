package com.company;

import javax.swing.*;

/**
 * Class with main method
 *
 * Game called "Escape The Dungeon" - easy labyrinth game with educational
 * and pedagogical aspects
 *
 * @author Igor Zeitz
 */
public class Main {

    /**
     * Main method which starts game
     *
     * It creates main game window and
     * uses essential classes and methods to
     * properly start the game
     *
     * @param args
     */
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
