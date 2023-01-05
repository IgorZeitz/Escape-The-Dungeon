package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.EOFException;

public class Player {

    public static int playerPositionX=100;   //player standard position
    public static int playerPositionY=100;

    public int playerSpeed=1;

     Controls playerControls;
    public Player(Controls controls){
        this.playerControls = controls;

        loadPlayerTextures();

    }

    public int animation=1;
    int counter;

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

    BufferedImage front1, front2, back1, back2, left1, left2, right1, right2, front, view;

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

    public void drawDistance(Graphics2D g2){
        g2.drawImage(view,playerPositionX-1524,playerPositionY-872 ,3*GamePanel.windowHeight,3*GamePanel.widowLength,null);
    }

    boolean playerCollision = false;

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

    boolean playerEndingCollision;

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
