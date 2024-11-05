package environment;

import core.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class TileManager {

    GamePanel gamePanel;
    Tile[] tiles;

    public TileManager(GamePanel gp) {
        gamePanel = gp;
        tiles = new Tile[10];

        loadTileImage();
    }

    public void loadTileImage() {
        try {
            BufferedImage spriteSheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/mapTiles/spriteSheet.png")));

            //Grass tile
            tiles[0] = new Tile();
            tiles[0].image = spriteSheet.getSubimage(128, 0, gamePanel.tileSize, gamePanel.tileSize);

        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public void draw(Graphics2D graphics2D) {
        for (int i = 0; i < gamePanel.screenHeight; i+= gamePanel.gameTileSize) {
            for (int j = 0; j < gamePanel.screenWidth; j+=gamePanel.gameTileSize) {
                graphics2D.drawImage(tiles[0].image, j,i, gamePanel.gameTileSize, gamePanel.gameTileSize, null);
            }
        }
    }
}
