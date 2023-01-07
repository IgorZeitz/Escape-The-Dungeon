package levels;

import com.company.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

/**
 * Class responsible for level maps
 *
 * It loads, stores and draws maps for
 * concrete levels
 *
 */
public class Map {

    /** Table that stores map textures  */
    public Textures[] tile;

    /**
     * Map constructor - loading textures and map
     */
    public Map(){

        tile = new Textures[4];

        loadTextures();
        mapLoading();
    }
//---------------Loading textures to the array-------------------------------------

    /**
     * Loading textures to specific table elements
     */
    public void loadTextures(){
        try {
            tile[0] = new Textures();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/textures/floor.png"));

            tile[1] = new Textures();
            tile[1].image= ImageIO.read(getClass().getResourceAsStream("/textures/brickWall.png"));
            tile[1].collision=true;

            tile[2] = new Textures();
            tile[2].image= ImageIO.read(getClass().getResourceAsStream("/textures/door.png"));
            tile[2].endingTile = true;

           // tile[3] = new Textures();
           // tile[3].image= ImageIO.read(getClass().getResourceAsStream("/textures/domek.png"));

        }catch (IOException e){
            e.printStackTrace();
        }

    }
//---------------------Drawing textures-----------------------

    /**
     * Method that fills the game window with textures stored in tile table
     *
     * @param g2
     */
    public void drawTextures(Graphics2D g2) {

        int worldColumns = GamePanel.screenColumn;
        int worldRows = GamePanel.screenRow; // to set

        int x = 0;
        int y = 0; //position of tales

        int colX;
        int colY;

    try {
        for (colX = 0, colY = 0; colX < worldColumns && colY < worldRows; colX++) {

            int numberToWrite = map[colY][colX];        //numberToWrite - saving appropriate number of "texture"

            g2.drawImage(tile[numberToWrite].image, x, y, GamePanel.screenSize, GamePanel.screenSize, null);
            x = x + GamePanel.screenSize;
            if (colX == worldColumns -1) {
                colX = -1;
                x = 0;
                y = y + GamePanel.screenSize;
                colY++;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
//---------------------Loading whole map form txt-------------
    /** two-dimensional map table to store textures */
    public int[][] map = new int[GamePanel.screenRow][GamePanel.screenColumn];
    /** Necessary string with map file path */
    static String mapPathFile;

    /**
     * Method that loads textures to the two-dimensional map table
     *
     * This method takes a txt file, separates its
     * elements and enter them to a two-dimensional map
     * table from which textures are assigned
     * to appropriate positions
     */
    public void mapLoading() {
        try {
            //----Reading the file-----
            if(GamePanel.gameOn==0) {
                mapPathFile="src/maps/1.txt";
            }else if(GamePanel.gameOn==8){
                mapPathFile="src/maps/2.txt";
            }else if(GamePanel.gameOn==9){
                mapPathFile="src/maps/3.txt";
            }
                File txtFile = new File(mapPathFile);

            BufferedReader brLvlTxt = new BufferedReader(new FileReader(txtFile));

            String sLine;

            int worldColumns = GamePanel.screenColumn;
            int worldRows = GamePanel.screenRow;

            while ((sLine = brLvlTxt.readLine()) != null) {

            for(int colX = 0, colY=0; colX < worldColumns && colY < worldRows; colX++){ // if <= 31 it is trying to "find" 31 element

                    String[] mapNumbers =  sLine.split(" ");
                    int number = Integer.parseInt(mapNumbers[colX]);

                    map[colY][colX]=number;


                if(colX==worldColumns-1) {

                    colX=-1 ;
                    sLine= brLvlTxt.readLine();
                    colY++;

                }
            }
            }

            brLvlTxt.close();

        }catch (Exception e){
            e.printStackTrace();
        }

     }

}