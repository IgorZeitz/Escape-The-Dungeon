package levels;

import java.awt.image.BufferedImage;

/**
 * Class specified for textures type
 *
 * @author Igor Zeitz
 */
public class Textures {

    /** BufferedImage to get a texture png */
    public BufferedImage image;
    /** Boolean variable to check if a texture can collide with player */
    public boolean collision;
    /** Boolean variable to check if a texture can end a level */
    public boolean endingTile;

}
