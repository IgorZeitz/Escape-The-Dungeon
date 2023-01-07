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

/**
 * Class responsible for an GUI
 *
 * It displays all types of menu graphics
 * depending on gameOn variable
 *
 * @author Igor Zeitz
 */
public class UI {

    GamePanel gp;
    static Graphics2D g2;

    static JPanel p = new JPanel();
    JButton b1 = new JButton("Quit");

    /**
     * UI class constructor
     *
     * @param gp required GamePanel object
     */
    public UI(GamePanel gp){
        this.gp =gp;
    }

    /**
     * Draws appropriate menu depending on a game state (variable gameOn)
     *
     * @param g2
     */
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

  static JFrame f = new JFrame("Menu");

    static JPanel pO = new JPanel();
    public static int i=0; // due to this + if => only 1 implementation

    /**
     * It implements all main menu elements
     */
    public static void menu(){
        for( ; i<1; i++) {  // + i=0 while changing level => I quess it's not a good way to do it -_-
            f.setResizable(false);
            f.setLocationRelativeTo(null);
            f.setSize(GamePanel.windowHeight, GamePanel.widowLength);
            //f.setLayout(new GridBagLayout());



            p.setPreferredSize(new Dimension(GamePanel.windowHeight, GamePanel.widowLength));
            p.setBackground(Color.darkGray);
            p.setLayout(new GridBagLayout());

            JButton b1 = new JButton("Options");
            JButton b2 = new JButton("Level");
            JButton b3 = new JButton("Exit Game");

          //  JButton bO1 = new JButton("");
          //  JButton bO2 = new JButton("");
            JButton bO3 = new JButton("Back");

            JCheckBox ch =new JCheckBox("MUSIC:");

            ch.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if(e.getStateChange()==1){
                        startMusic(musicFilePath);
                    }else{
                        clip.stop();
                    }
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
            pO.setLayout(new GridBagLayout());


            JPanel lvlP = new JPanel();

            lvlP.setPreferredSize(new Dimension(GamePanel.windowHeight, GamePanel.widowLength));
            lvlP.setBackground(Color.gray);
            lvlP.setLayout(new GridBagLayout());

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

                    i=0;
                }
            });

            lvl3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    f.setVisible(false);
                    Player.playerPositionX=100;
                    Player.playerPositionY=100;

                    GamePanel.gameOn=9;

                    i=0;
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

//          --- PAUSE MENU

    /**
     * It implements all pause menu elements
     */
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

//          ---ENDING MENU---

    /**
     * It implements all ending menu elements
     */
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

    /** Necessary string that stores music file path  */
    static String musicFilePath="src/sounds/8_Bit_Adventure.wav";
    /** Necessary clip to start music in startMusic method */
    public static Clip clip;

    /**
     * It is responsible for all music elements
     *
     * @param musicFilePatch required music file patch to know which sound it should play
     */
    public static void startMusic(String musicFilePatch){
        try{
            File music = new File(musicFilePatch);
            AudioInputStream audio = AudioSystem.getAudioInputStream(music);
            clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();

            clip.loop(Clip.LOOP_CONTINUOUSLY); // music --> infinity
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }

}
