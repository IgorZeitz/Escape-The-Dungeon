package com.company;
import levels.Map;
import javax.swing.*;
import java.awt.*;

/**
 * Main graphical game area
 *
 * Class responsible for drawing/displaying most graphic components
 * and also repainting changes. Class extends JPanel and implements Runnable
 *
 *
 * @author Igor Zeitz
 */
public class GamePanel extends JPanel implements Runnable{
    //Making a game screen with sections - 16x16pixels or 8x8 - to do
    /** Not changeable size of one tile - size of one texture */
    public static final int oneSection = 8; //8x8pix game
    /** Not changeable value created to scale size of textures */
    public static final int scale = 4;
    /** Not changeable real size of one tile - oneSection*scale to make textures bigger */
    public static final int screenSize= scale*oneSection;

    /** Not changeable size of rows in game window */
    public static final int screenRow=22;
    /** Not changeable size of columns in game window */
    public static final int screenColumn=30;
    /** Not changeable game window height */
    static final int windowHeight = screenColumn*screenSize; //(8*4)*30=1024
    /** Not changeable game window length */
    static final int widowLength = screenRow*screenSize; //(8*4)*24=768
    //*** resolution 1024x768


    Controls keyControls = new Controls();

    MouseControls mouseControls = new MouseControls();

    UI ui = new UI(this);

    static ObjectCollision collision = new ObjectCollision();

    static ObjectCollision endingCollision = new ObjectCollision();

    Map map = new Map();  // TU MAPA

    /**
     * Class constructor
     *
     * Setting basic parameters and adding action listeners in game area
     *
     */
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
    /** Responsible for game thread */
    Thread frameRate;

    /**
     * Starting game thread
     */
    public void startGameThread() {
        frameRate = new Thread(this);
        frameRate.start();
    }

//------------ loop ------------------------------------------------------------
    /** delay necessary to refresh game not faster than 60 times per on second */
    double delay=1000000000/60; //60 frames (in 1 sec - 1000ms- 1000 000 000ns)
    /** responsible for counting time */
    long time = System.nanoTime();
    /** responsible for correct game time refreshment  */
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

    /** A parameter to distinguish game state (If the game: is paused, returned to menu, etc.) */
    public static int gameOn=0;

    /**
     * Method updating player character position and player view distance
     */
    public void updateGame() {

            player1.playerUpdate(); //updating player1 position
        distanceView.playerUpdate();

    }

    /**
     * Method responsible for painting appropriate components
     *
     * Painting depends on gameOn which tells if the painted component
     * should be player, menu, etc.
     *
     * @param g
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;



        if(gameOn == 0) {
            //Lvl 1
            player1.collision();
            distanceView.collision();

            map.drawTextures(g2);
        } else if(gameOn==8){
            //Lvl 2
            map.mapLoading();
            map.drawTextures(g2);

            collision.checkCollision(player1);

            ObjectCollision.map.mapLoading();
            player1.collision();
            distanceView.collision();

             }else if(gameOn==9){
            //Lvl 3
            map.mapLoading();
            map.drawTextures(g2);

            ObjectCollision.map.mapLoading();
            collision.checkCollision(player1);

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


