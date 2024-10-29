package entities;

import core.GamePanel;
import core.KeyHandler;

import java.awt.*;

public class Player extends Entity{

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gp, KeyHandler kh) {
        this.gamePanel = gp;
        this.keyHandler = kh;

        setEntityValues();
    }

    public void setEntityValues() {
        x = 100;
        y = 100;
        speed = 4;
    }

    public void update() {
        if (keyHandler.upPressed) {
            y -= speed;
        }

        if (keyHandler.downPressed) {
            y += speed;
        }

        if (keyHandler.leftPressed) {
            x -= speed;
        }

        if (keyHandler.rightPressed) {
            x += speed;
        }
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.blue);

        graphics2D.fillRect(x, y, gamePanel.gameTileSize, gamePanel.gameTileSize);
    }

}
