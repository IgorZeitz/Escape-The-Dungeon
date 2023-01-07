package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.EOFException;

/**
 * Class specified for playable character
 *
 * It implements required player settings
 *
 *
 * @author Igor Zeitz
 */
public class Player {

    /** Starting player x position */
    public static int playerPositionX=100;   //player standard position
    /** Starting player y position */
    public static int playerPositionY=100;

    /** Player basic movement speed */
    public int playerSpeed=1;

     Controls playerControls;

    /**
     * Player class constructor
     *
     * Needed for appropriate textures while
     * pressing movement keys
     *
     * @param controls used for displaying different textures that represent different movement directions
     */
    public Player(Controls controls){
        this.playerControls = controls;

        loadPlayerTextures();

    }

    /** Starting animation number */
    public int animation=1;
    /** Animation counter*/
    int counter;

    /**
     * Allows hero to animate while moving
     */
    public void animation(){

        counter++;         //animation counter
        if(counter>10){
            if(animation==1){
                animation=2;
            } else if(animation==2){
                animation=1;
            }
            counter=0;
        }
    }

    /**
     * It allows updating player position
     *
     * @return returns int values form 0 to 4: 1 = player is going up, 2 = player is going down,
     * 3 = player is going left, 4 = player is going right, 0 = player is standing
     */
    public int playerUpdate(){

            if (playerControls.up == true) {
              //  playerPositionY -= playerSpeed; //moving up by 1pix => playerSpeed==1
                return 1;
            }
            if (playerControls.down == true) {
               // playerPositionY += playerSpeed;
                return 2;
            }
            if (playerControls.left == true) {
               // playerPositionX -= playerSpeed;
                return 3;
            }
            if (playerControls.right == true) {
               // playerPositionX += playerSpeed;
                return 4;
            } else {
                return 0; //can't happen
            }

    }

    /** BufferedImages for all player textures */
    BufferedImage front1, front2, back1, back2, left1, left2, right1, right2, front, view;

    /**
     * Method that assigns textures to appropriate directions
     */
    public void loadPlayerTextures(){
        try{
            front1 = ImageIO.read(getClass().getResourceAsStream("/textures/playerFront1.png"));
            front2 = ImageIO.read(getClass().getResourceAsStream("/textures/playerFront2.png"));
            back1 = ImageIO.read(getClass().getResourceAsStream("/textures/playerBack1.png"));
            back2 = ImageIO.read(getClass().getResourceAsStream("/textures/playerBack2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/textures/playerLeft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/textures/playerLeft2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/textures/playerRight1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/textures/playerRight2.png"));
            front = ImageIO.read(getClass().getResourceAsStream("/textures/playerFront.png"));

            view = ImageIO.read(getClass().getResourceAsStream("/textures/viewDistance1.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Drawing player textures on specific x and y position
     *
     * It allows to see the player movement
     *
     * @param g2
     */
    public void drawPlayer(Graphics2D g2){

        BufferedImage playerTexture = front;

        animation();

        if(playerUpdate() == 1) {
            //going up == seeing back texture
            if(animation==1){
                playerTexture=back2;
            }else{
                playerTexture=back1;
           }
        } else if(playerUpdate()==2){
            if(animation==1){
                playerTexture=front2;
            }else{
                playerTexture=front1;
            }  // down => the other way
        }else if(playerUpdate()==3){
            if(animation==1){
                playerTexture=left2;
            }else{
                playerTexture=left1;
            }
        }else if(playerUpdate()==4){
            if(animation==1){
                playerTexture=right2;
            }else{
                playerTexture=right1;
            }
        }

        g2.drawImage(playerTexture,playerPositionX,playerPositionY,GamePanel.screenSize,GamePanel.screenSize,null);

    }

    /**
     * It draws player view distance
     *
     * @param g2
     */
    public void drawDistance(Graphics2D g2){
        g2.drawImage(view,playerPositionX-1524,playerPositionY-872 ,3*GamePanel.windowHeight,3*GamePanel.widowLength,null);
    }

    /** Assigning starting collision to false */
    boolean playerCollision = false;

    /**
     * Collision checking method
     *
     * It allows player to move if the colliding tile
     * collision is false. If texture collision is true
     * player can't move in that texture position
     */
    public void collision(){

        playerCollision =false;     // MUST HAVE - bo inaczej zatrzymyje gracza na zawsze

        GamePanel.collision.checkCollision(this);

        if(playerCollision==false){
            switch(playerUpdate()){
                case 1:
                    playerPositionY -= playerSpeed;
                    break;
                case 2:
                    playerPositionY += playerSpeed;
                    break;
                case 3:
                    playerPositionX -= playerSpeed;
                    break;
                case 4:
                    playerPositionX += playerSpeed;
                    break;
            }
        }

    }

    /** Variable for ending level collision */
    boolean playerEndingCollision;

    /**
     * Checking if the level should end
     *
     * If a player position is equal to the
     * door position it will display an ending menu
     * and change the gameOn variable to ending value
     */
    public void endingCollision(){

        GamePanel.collision.checkEndingTile(this);

        if(playerEndingCollision==true) {

            JPanel endingPanel = new JPanel();
            endingPanel.setVisible(true);
            endingPanel.setBackground(Color.darkGray);
            endingPanel.setBounds(300, 300, 50, 50);

            GamePanel.gameOn=1;

           // System.out.println("Wygywasz !!!");

        }
    }

}
