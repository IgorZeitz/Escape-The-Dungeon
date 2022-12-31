package com.company;

import levels.Map;

public class ObjectCollision {

    public ObjectCollision(){

    }

    Controls controls = new Controls();
    Player player = new Player(controls);
    static Map map = new Map();

    public void checkCollision(Player player){

        int upCollision = player.playerPositionY + 2 + GamePanel.screenSize-16; //+0 = + collision area position
        int downCollision = player.playerPositionY + 32; //+0 +0 = -||- + player collision width
        int leftCollision = player.playerPositionX + 12;
        int rightCollision = player.playerPositionX + 2 + GamePanel.screenSize-16;

        int leftColumn = leftCollision/GamePanel.screenSize;
        int rightColumn = rightCollision/GamePanel.screenSize;          //TU GDZIES WARTOSCI POMIESZANE - JUZ GIT
        int upRow = upCollision/GamePanel.screenSize;
        int downRow = downCollision/GamePanel.screenSize;

        int firstBlock, secondBlock;

        switch(player.playerUpdate()){

            case 1:
                upRow = (upCollision - player.playerSpeed)/GamePanel.screenSize;
                firstBlock = map.map[upRow][leftColumn];
                secondBlock = map.map[upRow][rightColumn];
                if(map.tile[firstBlock].collision==true || map.tile[secondBlock].collision==true){
                    player.playerCollision=true;
                }break;
            case 2:
                downRow = (downCollision + player.playerSpeed)/GamePanel.screenSize;
                firstBlock = map.map[downRow][leftColumn];
                secondBlock = map.map[downRow][rightColumn];
                if(map.tile[firstBlock].collision==true || map.tile[secondBlock].collision==true){
                    player.playerCollision=true;
                }break;
            case 3:
                leftColumn = (leftCollision - player.playerSpeed)/GamePanel.screenSize;
                firstBlock = map.map[upRow][leftColumn];
                secondBlock = map.map[downRow][leftColumn];
                if(map.tile[firstBlock].collision==true || map.tile[secondBlock].collision==true){
                    player.playerCollision=true;
                }break;
            case 4:
                rightColumn = (rightCollision + player.playerSpeed)/GamePanel.screenSize;
                firstBlock = map.map[upRow][rightColumn];
                secondBlock = map.map[downRow][rightColumn];
                if(map.tile[firstBlock].collision==true || map.tile[secondBlock].collision==true){
                    player.playerCollision=true;
                }break;
        }


    }

    public void checkEndingTile(Player player){

        int verticalCollision = player.playerPositionY+8;
        int horizontalCollision = player.playerPositionX+8; // +8 must have - for appropriate tile checking

        int Column = horizontalCollision/GamePanel.screenSize;
        int Row= verticalCollision/GamePanel.screenSize;

        int endingBlock;

                endingBlock = map.map[Row][Column];

     //   System.out.println(player.playerPositionX + "____" + player.playerPositionY + "    " + endingBlock);// TO SIĘ WYKONUJE !!!

        if(map.tile[endingBlock].endingTile==true){
                    player.playerEndingCollision=true;
                }
    }
}
