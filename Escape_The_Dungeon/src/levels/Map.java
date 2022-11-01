package levels;

import com.company.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Map {

    Textures[] tile;

    public Map(){

        tile = new Textures[2];

        loadTextures();
    }
//---------------Loading textures to array-------------------------------------
    public void loadTextures(){
        try {
            tile[0] = new Textures();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/textures/floor.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
//        tile[1] = new Textures();
  //      tile[1].image= ImageIO.read(getClass().getResourceAsStream("/Textures/Wall"));

    //    tile[2] = new Textures();
      //  tile[2].image= ImageIO.read(getClass().getResourceAsStream("/Textures/Door"));

    }
//---------------------Drawing textures-----------------------
    public void drawTextures(Graphics2D g2){
        g2.drawImage(tile[0].image,0, 0, GamePanel.screenSize, GamePanel.screenSize, null);
    }
//---------------------Drawing whole map form txt-------------
}
