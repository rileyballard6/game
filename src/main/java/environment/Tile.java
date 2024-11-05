package environment;

import java.awt.image.BufferedImage;

public class Tile {

    public BufferedImage image;
    public boolean collision;

    public Tile(BufferedImage tileSprite) {
        image = tileSprite;
    }
}
