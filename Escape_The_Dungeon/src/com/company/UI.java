package com.company;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.swing.*;

public class UI {

    GamePanel gp;
    static Graphics2D g2;

    static JPanel p = new JPanel();
    JButton b1 = new JButton("Quit");

    public UI(GamePanel gp){
        this.gp =gp;
    }

    public void draw(Graphics2D g2){
        this.g2=g2;

        Font font = new Font("Serif", Font.BOLD, 60);
        g2.setFont(font);
        g2.setColor(Color.black);
        //g2.setBackground(Color.darkGray);

        if(GamePanel.gameOn==0){
            //just let player move
        }else if(GamePanel.gameOn==1){
            endingMenu();
        }else if(GamePanel.gameOn==2){
            pauseMenu();
        }else if(GamePanel.gameOn==3){
            menu();
        }
    }

   // Player player = new Player(GamePanel.keyControls);
  // JPanel p = new JPanel();  //adding JPanel to JFrame
   static JPanel pO = new JPanel();
    public static int i=0; //due to this + if = only 1 implementation
    public static void menu(){
        for( ; i<1; i++) {  //not a good way to do it but -_-  -------------- Creating JPanel outside the menu() will fix it probably
            JFrame f = new JFrame("Menu");
            f.setResizable(false);
            f.setLocationRelativeTo(null);
            f.setSize(GamePanel.windowHeight, GamePanel.widowLength);



            p.setPreferredSize(new Dimension(GamePanel.windowHeight, GamePanel.widowLength));
            p.setBackground(Color.darkGray);

            JButton b1 = new JButton("Options");
            JButton b2 = new JButton("Level");
            JButton b3 = new JButton("Exit Game");

          //  JButton bO1 = new JButton("");
          //  JButton bO2 = new JButton("");
            JButton bO3 = new JButton("Back");

            JCheckBox ch =new JCheckBox("MUSIC:");

            ch.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    startMusic(musicFilePath);
                }
            });

//            bO2.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//
//                }
//            });

            bO3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    pO.setVisible(false);
                    p.setVisible(true);
                }
            });

            b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    p.setVisible(false);
                    pO.setPreferredSize(new Dimension(GamePanel.windowHeight, GamePanel.widowLength));
                    pO.setBackground(Color.darkGray);
                    pO.setVisible(true);

                   // pO.add(bO2);
                    pO.add(bO3);
                    pO.add(ch);
                    f.add(pO);
                }
            });

            JPanel lvlP = new JPanel();

            lvlP.setPreferredSize(new Dimension(GamePanel.windowHeight, GamePanel.widowLength));
            lvlP.setBackground(Color.green);

            JButton lvl1 = new JButton("Level 1");
            JButton lvl2 = new JButton("Level 2");
            JButton lvl3 = new JButton("Level 3");

            b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    p.setVisible(false);

                    lvlP.add(lvl1);
                    lvlP.add(lvl2);
                    lvlP.add(lvl3);
                    lvlP.setVisible(true);

                    f.add(lvlP);

                }
            }) ;

//                      ---LEVELS MENU BUTTONS---

            lvl1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    f.setVisible(false);
                    Player.playerPositionX=100;
                    Player.playerPositionY=100;

                    GamePanel.gameOn=0;

                }
            });

            lvl2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    f.setVisible(false);
                    Player.playerPositionX=100;
                    Player.playerPositionY=100;

                    GamePanel.gameOn=8;

                }
            });

            lvl3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    f.setVisible(false);
                    Player.playerPositionX=100;
                    Player.playerPositionY=100;

                    GamePanel.gameOn=9;

                }
            });
//                      ---------------------------

            b3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });


            p.add(b1);
            p.add(b2);
            p.add(b3);

            f.add(p);

            p.setVisible(true);
            f.setVisible(true);
        }

    }

    public void pauseMenu(){
        String text1 = "Game Paused";
        String text2 = "Continue";
        String text3 = "Exit";
        String text4 = "Main Menu";

        int x=300;
        int y=300;

        g2.drawString(text1, x, y);

        g2.setColor(Color.darkGray);
        g2.fillRoundRect(300, 300, 350, 300, 35, 35);

        g2.setColor(Color.white);
        g2.drawString(text2, 320, 365 );
        g2.drawString(text3, 320, 465 );
        g2.drawString(text4, 320, 565 );


//        p.setBounds(0, 0, 350, 300 );
//        p.setVisible(true);
//        p.setEnabled(true);
//        p.setBackground(Color.white);
//
//        b1.setBounds(320, 320, 20, 20);
//        b1.setVisible(true);
//        b1.setEnabled(true);
//
//        p.add(b1);


    }

    public static void endingMenu(){

        String text = "YOU FOUND THE EXIT";
        String text2 = "Exit";
        String text3 = "Main Menu";

        int x=150;
        int y=300;

        g2.drawString(text, x, y);

        g2.setColor(Color.darkGray);
        g2.fillRoundRect(300, 300, 350, 300, 35, 35);

        g2.setColor(Color.white);
        g2.drawString(text2, 320, 550 );
        g2.drawString(text3, 320, 400 );
    }

//          ---MUSIC---

    static String musicFilePath="src/sounds/8_Bit_Adventure.wav";
    public static void startMusic(String musicFilePatch){
        try{
            File music = new File(musicFilePatch);
            AudioInputStream audio = AudioSystem.getAudioInputStream(music);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

}
