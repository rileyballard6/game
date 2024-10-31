package entities;

import core.GamePanel;
import core.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gp, KeyHandler kh) {
        this.gamePanel = gp;
        this.keyHandler = kh;

        setEntityValues();
        getPlayerImage();
    }

    public void setEntityValues() {
        x = 100;
        y = 100;
        speed = 3;
    }

    public void getPlayerImage() {
        int characterWidth = 16;
        int characterHeight = 32;
        try {
            BufferedImage spriteSheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/characterSprites/spriteSheet.png")));
            standLeft = spriteSheet.getSubimage(0,0,characterWidth, characterHeight);
            left1 = spriteSheet.getSubimage(16,0,characterWidth, characterHeight);
            left2 = spriteSheet.getSubimage(32,0,characterWidth, characterHeight);

            standRight = spriteSheet.getSubimage(0,32,characterWidth, characterHeight);
            right1 = spriteSheet.getSubimage(16,32,characterWidth, characterHeight);
            right2 = spriteSheet.getSubimage(32,32,characterWidth, characterHeight);

            stand = spriteSheet.getSubimage(0,64,characterWidth, characterHeight);
            down1 = spriteSheet.getSubimage(16,64,characterWidth, characterHeight);
            down2 = spriteSheet.getSubimage(32,64,characterWidth, characterHeight);

            standUp = spriteSheet.getSubimage(0,96,characterWidth, characterHeight);
            up1 = spriteSheet.getSubimage(16,96,characterWidth, characterHeight);
            up2 = spriteSheet.getSubimage(32,96,characterWidth, characterHeight);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void update() {
        direction = "standing";

        if (keyHandler.upPressed) {
            y -= speed;
            direction = "up";
        }

        if (keyHandler.downPressed) {
            y += speed;
            direction = "down";
        }

        if (keyHandler.leftPressed) {
            x -= speed;
            direction = "left";
        }

        if (keyHandler.rightPressed) {
            x += speed;
            direction = "right";
        }

        spriteCounter++;
        if (spriteCounter > 14) {
            spriteNum++;
            if (spriteNum > 4) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D graphics2D) {

        BufferedImage characterImage = null;

        switch (direction) {
            case "down":
                characterImage = (spriteNum == 1) ? down1 : (spriteNum == 2) ? stand : (spriteNum == 3) ? down2 : stand;
                break;
            case "left":
                characterImage = (spriteNum == 1) ? left1 : (spriteNum == 2) ? standLeft : (spriteNum == 3) ? left2 : standLeft;
                break;
            case "right":
                characterImage = (spriteNum == 1) ? right1 : (spriteNum == 2) ? standRight : (spriteNum == 3) ? right2 : standRight;
                break;
            case "up":
                characterImage = (spriteNum == 1) ? up1 : (spriteNum == 2) ? standUp : (spriteNum == 3) ? up2 : standUp;
                break;
            case "standing":
                characterImage = stand;
        }

        graphics2D.drawImage(characterImage, x, y, gamePanel.gameTileSize, gamePanel.gameTileSize * 2, null);
    }

}
