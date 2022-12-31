package com.company;

import levels.Map;//////////////////////////////////// TU usunąć potem!!!

import javax.swing.*;

public class Main {

   // static boolean closeWindow=false;

    public static void main(String[] args) {
	//***Game window
        JFrame gameWindow = new JFrame();
        gameWindow.setTitle("Escape The Dungeon");
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        gameWindow.setLocationRelativeTo(null);

        GamePanel gamePanel = new GamePanel();  //adding JPanel to JFrame
        gameWindow.add(gamePanel);

      //  gameWindow.add(UI.p);

        gameWindow.pack();

      //  While(closeWindow==true){
      //      gameWindow.setVisible(false);
      //  } else {
            gameWindow.setVisible(true);
      //  }
    //**************
        gamePanel.startGameThread();


        //gameWindow.add(endingPanel);
    //

     //   Map map = new Map();    //////////////////////////////// TU usunąć potem!!!
     //   map.mapLoading();
    }
}
