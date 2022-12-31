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
    static final int windowHeight = screenColumn*screenSize; //(8*4)*30=1024
    static final int widowLength = screenRow*screenSize; //(8*4)*24=768
    //*** resolution 1024x768

    Controls keyControls = new Controls();

    MouseControls mouseControls = new MouseControls();

    UI ui = new UI(this);

    static ObjectCollision collision = new ObjectCollision();

    static ObjectCollision endingCollision = new ObjectCollision();

    Map map = new Map();            // TU MAPA!

    public GamePanel() {
        this.setPreferredSize(new Dimension(windowHeight, widowLength));
        this.setBackground(Color.black);
        this.setVisible(true);
        //implemented key "input"
        this.addKeyListener(keyControls);

        this.addMouseListener(mouseControls);

        this.setFocusable(true);
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

//------------------------------------------------------

    Player player1 = new Player(keyControls);
    Player distanceView = new Player(keyControls);

    public static int gameOn=0;
    public void updateGame() {

            player1.playerUpdate(); //updating player1 position
        distanceView.playerUpdate();

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;      //Extenciton of graphis specified for 2d



        if(gameOn == 0) {
            //Lvl 1
            player1.collision();
            distanceView.collision();

            map.drawTextures(g2);
        } else if(gameOn==8){
            //Lvl 2
            map.mapLoading();
            map.drawTextures(g2);

            player1.collision();
            distanceView.collision();

             }else if(gameOn==9){
            //Lvl 3
            map.mapLoading();
            map.drawTextures(g2);

            player1.collision();
            distanceView.collision();
        }

        player1.endingCollision();
        distanceView.endingCollision();
        if(gameOn == 0) {
            player1.drawPlayer(g2);
            distanceView.drawDistance(g2);
            //gameOn - used 2x for drawing and moving -> to correct
        } else if(gameOn==8){
            //Lvl 2
            player1.drawPlayer(g2);
            distanceView.drawDistance(g2);

        }else if(gameOn==9) {
            //Lvl 3
            player1.drawPlayer(g2);
            distanceView.drawDistance(g2);
        }

        ui.draw(g2);
    }



}


