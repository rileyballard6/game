package environment;

import core.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    GamePanel gamePanel;
    Tile[] tiles;
    int mapTileNumber[][];

    public TileManager(GamePanel gp) {
        gamePanel = gp;
        tiles = new Tile[10];

        mapTileNumber = new int[gp.maxScreenCol][gp.maxScreenRow];

        loadTileImage();
        loadMapTxt("/worldMaps/map1");
    }

    public void loadTileImage() {
        try {
            BufferedImage spriteSheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/mapTiles/spriteSheet.png")));

            //Grass tile
            tiles[0] = new Tile(spriteSheet.getSubimage(128, 0, gamePanel.tileSize, gamePanel.tileSize));

            //Dirt tile
            tiles[1] = new Tile(spriteSheet.getSubimage(112, 64, gamePanel.tileSize, gamePanel.tileSize));

            //Water tile
            tiles[2] = new Tile(spriteSheet.getSubimage(64, 64, gamePanel.tileSize, gamePanel.tileSize));


        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public void loadMapTxt(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {
                String line = br.readLine();

                while (col < gamePanel.maxScreenCol) {
                    String[] numbers = line.split(" ");

                    int number = Integer.parseInt(numbers[col]);

                    mapTileNumber[col][row] = number;
                    col++;
                }

                if (col == gamePanel.maxScreenCol) {
                    col = 0;
                    row ++;
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void draw(Graphics2D graphics2D) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {
            int tileNum = mapTileNumber[col][row];
            graphics2D.drawImage(tiles[tileNum].image, x, y, gamePanel.gameTileSize, gamePanel.gameTileSize, null);
            col++;
            x += gamePanel.gameTileSize;
            if (col == gamePanel.maxScreenCol) {
                col = 0;
                x = 0;
                row ++;
                y += gamePanel.gameTileSize;
            }


        }

    }
}
