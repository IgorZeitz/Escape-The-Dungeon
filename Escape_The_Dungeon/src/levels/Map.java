package levels;

import com.company.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class Map {

    public Textures[] tile;

    public Map(){

        tile = new Textures[4];


        loadTextures();
        mapLoading();
    }
//---------------Loading textures to array-------------------------------------
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
    public void drawTextures(Graphics2D g2) {

        int worldColumns = GamePanel.screenColumn;
        int worldRows = GamePanel.screenRow; // to set

        int x = 0;
        int y = 0;//position of tales

        int colX;
        int colY;

    try {
        for (colX = 0, colY = 0; colX < worldColumns && colY < worldRows; colX++) {

            int numberToWrite = map[colY][colX];        //numberToWrite - saving appropriate number of "texture"

            g2.drawImage(tile[numberToWrite].image, x, y, GamePanel.screenSize, GamePanel.screenSize, null);
            x = x + GamePanel.screenSize;
            if (colX == worldColumns -1) {
                colX = -1;               //TU CHYBA -1? ALE -1=invalid array index - pytanie czy przez to źle rysuje - bo po wyjściu z pętli => wejścu w pętle rysowania od razu colX=0 więc poprawny index tabeli
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

    public int[][] map = new int[GamePanel.screenRow][GamePanel.screenColumn];
static String mapPathFile;
    public void mapLoading() {
        try {
            //----Reading the file-----
            // !!!!!!!!!!!!!!!!!!!!!! if(GamePanel.gameOn){few options}
            if(GamePanel.gameOn==0) {
                mapPathFile="src/maps/1.txt";
            }else if(GamePanel.gameOn==8){
                mapPathFile="src/maps/2.txt";
            }else if(GamePanel.gameOn==9){
                mapPathFile="src/maps/3.txt";
            }
                File txtFile = new File(mapPathFile); //XDDD   //Nie fajna ścierza - ale działą


           // FileInputStream fstream = new FileInputStream("/maps/lvl1.txt");    // Err. System nie może odnaleźć określonej ścieżki
            BufferedReader brLvlTxt = new BufferedReader(new FileReader(txtFile));

            String sLine;

            int worldColumns = GamePanel.screenColumn;
            int worldRows = GamePanel.screenRow;

            while ((sLine = brLvlTxt.readLine()) != null) {

            for(int colX = 0, colY=0; colX < worldColumns && colY < worldRows; colX++){                // odwoływanie się do 31 elementu tablicy przez <= ???

                    String[] mapNumbers =  sLine.split(" ");
                    int number = Integer.parseInt(mapNumbers[colX]);

                    map[colY][colX]=number;
                  //  colX++; przeskakiwanie co 2?


                if(colX==worldColumns-1) {        // TU PRZERZUCILEM

                    colX=-1 ;                //TU CHYBA -!
                    sLine= brLvlTxt.readLine(); //Tu Brakowało !!!!
                    colY++;

                }
            }
            }

            brLvlTxt.close();           //22,55? w złym miejscu przerwanie czytania, stąd zczytuję w pętli tylko pierwszą linie

        }catch (Exception e){
            e.printStackTrace();
        }

        //----Importing string into a int array map

//        List<String> listMap = Collections.emptyList();
//
////        try {
////            listMap = Files.readAllLines(Paths.get("/maps/lvl1.txt"));
////        } catch (IOException e){
////            e.printStackTrace();
////        }
////
////        // NOT WORKING -paths.get- idk why^ System.out.println(listMap);

     }

}