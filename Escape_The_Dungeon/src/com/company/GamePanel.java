package com.company;

import levels.Map;
import levels.Textures;

import javax.swing.*;

import java.awt.*;

import java.io.*;

public class GamePanel extends JPanel implements Runnable{
    //Making a game screen with sections - 16x16pixels or 8x8 - to do
   public static final int oneSection = 8; //8x8pix game
   public static final int scale = 4;
   public static final int screenSize= scale*oneSection;

   public static final int screenRow=22;
    public static final int screenColumn=30;
    final int windowHeight = screenColumn*screenSize; //(8*4)*30=1024
    final int widowLength = screenRow*screenSize; //(8*4)*24=768
    //*** resolution 1024x768

    Controls keyControls = new Controls();

    Map map = new Map();            // TU MAPA!

    public GamePanel() {
        this.setPreferredSize(new Dimension(windowHeight, widowLength));
        this.setBackground(Color.black);
        this.setVisible(true);
        //implemented key "input"
        this.addKeyListener(keyControls);
        this.setFocusable(true);
    }

    //after pressing start game or the number of level turn to true
    boolean gameRuns;
    //methot to implement 60fps
    void frameRate(double fps){
        while(gameRuns=true){

        }
    }


//fps operation in the background **********************************
    Thread frameRate;

    public void startGameThread() {
        frameRate = new Thread(this);
        frameRate.start();
    }

//------------ loop ------------------------------------------------------------
    double delay=1000000000/60; //60 frames (in 1 sec - 1000ms- 1000 000 000ns)
    long time = System.nanoTime();

    double delta=0;


    @Override
    public void run() {
        while (frameRate!=null){

            updateGame();

            repaint();

            time+=delay;
                try {
                    long time2=System.nanoTime();
                    delta=time-time2;
                    delta=delta/1000000;
                    if(delta>=0) {
                        Thread.sleep((long) delta);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        }
    }

//Player positioning - moving (by changing x&y)
    int playerPositionX = 100;
    int playerPositionY = 100;
//------------------------------------------------------

    Player player1 = new Player(keyControls);

    public void updateGame() {

        player1.playerUpdate(); //updating player1 position


    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;      //Extenciton of graphis specified for 2d

        map.drawTextures(g2);

        player1.drawPlayer(g2);

       // g2.setColor(Color.red);
        //g2.fillRect(playerPositionX, playerPositionY, screenSize, screenSize);      //main character position + size of main character


       // map.mapLoading();
    }
    //***********************************************

    //Textures mapTextures = new Textures();

    //mapTextures.draw(g2);

}


