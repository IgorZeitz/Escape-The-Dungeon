package com.company;

import levels.Map;

/**
 * Class that is responsible for all in game collisions
 *
 * @author Igor Zeitz
 */
public class ObjectCollision {
    /**
     * Empty ObjectCollision constructor
     */
    public ObjectCollision(){

    }

    Controls controls = new Controls();
    Player player = new Player(controls);
    static Map map = new Map();

    /**
     * It checks if player collides with "walls"
     *
     * This method checks the positions before player
     * if the texture collision, opposite to the player, is true
     * it stops the player.
     * Texture is checked by player position which is then
     * assigned to the Map class table filled with
     * appropriate texture number. If the collision
     * of the given texture number = true then
     * the player won't move
     *
     *
     * @param player Player class object
     */
    public void checkCollision(Player player){
        /** position of the tile before the player while going up */
        int upCollision = player.playerPositionY + 2 + GamePanel.screenSize-16; //+0 = + collision area position
        /** position of the tile before the player while going down */
        int downCollision = player.playerPositionY + 32; //+0 +0 = -||- + player collision width
        /** position of the tile before the player while going left */
        int leftCollision = player.playerPositionX + 12;
        /** position of the tile before the player while going right */
        int rightCollision = player.playerPositionX + 2 + GamePanel.screenSize-16;

        /** number of the (tile) left column before the player in which is stored the texture*/
        int leftColumn = leftCollision/GamePanel.screenSize;
        /** number of the (tile) right column before the player in which is stored the texture*/
        int rightColumn = rightCollision/GamePanel.screenSize;
        /** number of the (tile) up row before the player in which is stored the texture*/
        int upRow = upCollision/GamePanel.screenSize;
        /** number of the (tile) down row before the player in which is stored the texture*/
        int downRow = downCollision/GamePanel.screenSize;

        /** variables to check two tiles collision for appropriate collision checking*/
        int firstBlock, secondBlock;

        // 4 cases for collision while going: up, down, left, right

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

    /**
     * It checks if player collides with "door" - colliding with door means ending a level
     *
     * This method checks if the player position is
     * equal to the ending tile (door) position.
     *
     * @param player
     */
    public void checkEndingTile(Player player){

        /** x position of the player (+8 to prevent collision on the edges of the tile) */
        int verticalCollision = player.playerPositionY+8;
        /** y position of the player (+8 to prevent collision on the edges of the tile) */
        int horizontalCollision = player.playerPositionX+8; // +8 must have - for appropriate tile checking

        int Column = horizontalCollision/GamePanel.screenSize;
        int Row= verticalCollision/GamePanel.screenSize;

        int endingBlock;

                endingBlock = map.map[Row][Column];

        if(map.tile[endingBlock].endingTile==true){
                    player.playerEndingCollision=true;
                }
    }
}
