package entities;

import core.GamePanel;
import core.KeyHandler;
import core.MouseHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gamePanel;
    KeyHandler keyHandler;
    MouseHandler mouseHandler;

    public Player(GamePanel gp, KeyHandler kh, MouseHandler mh) {
        this.gamePanel = gp;
        this.keyHandler = kh;
        this.mouseHandler = mh;

        setEntityValues();
        getPlayerImage();
    }

    public void setEntityValues() {
        x = gamePanel.gameTileSize * 2; // places the player at 2 tiles over
        y = gamePanel.gameTileSize * 2; // places the player at 2 tiles down
        speed = gamePanel.gameTileSize / 24; // or some other scaling approach for speed
    }


    public void getPlayerImage() {
        int characterWidth = 16;
        int characterHeight = 32;
        try {
            BufferedImage spriteSheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/characterSprites/spriteSheet.png")));

            stand = spriteSheet.getSubimage(0,0,characterWidth, characterHeight);
            down1 = spriteSheet.getSubimage(16,0,characterWidth, characterHeight);
            down2 = spriteSheet.getSubimage(32,0,characterWidth, characterHeight);

            standLeft = spriteSheet.getSubimage(0,32,characterWidth, characterHeight);
            left1 = spriteSheet.getSubimage(16,32,characterWidth, characterHeight);
            left2 = spriteSheet.getSubimage(32,32,characterWidth, characterHeight);

            standRight = spriteSheet.getSubimage(0,64,characterWidth, characterHeight);
            right1 = spriteSheet.getSubimage(16,64,characterWidth, characterHeight);
            right2 = spriteSheet.getSubimage(32,64,characterWidth, characterHeight);

            standUp = spriteSheet.getSubimage(0,96,characterWidth, characterHeight);
            up1 = spriteSheet.getSubimage(16,96,characterWidth, characterHeight);
            up2 = spriteSheet.getSubimage(32,96,characterWidth, characterHeight);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void update() {
        boolean isMoving = false;

        if (mouseHandler.click) {
            if (mouseHandler.mouseX > x && mouseHandler.mouseX < x + gamePanel.gameTileSize) {
                if (mouseHandler.mouseY > y && mouseHandler.mouseY < (y + gamePanel.gameTileSize * 2)) {
                    System.out.println("Player clicked!!");
                }
            }
            mouseHandler.click = false;
        }

        if (keyHandler.upPressed) {
            y -= keyHandler.sprinting ? (speed * 2) : speed;
            direction = "up";
            isMoving = true;
        }

        if (keyHandler.downPressed) {
            y += keyHandler.sprinting ? (speed * 2) : speed;
            direction = "down";
            isMoving = true;
        }

        if (keyHandler.leftPressed) {
            x -= keyHandler.sprinting ? (speed * 2) : speed;
            direction = "left";
            isMoving = true;
        }

        if (keyHandler.rightPressed) {
            x += keyHandler.sprinting ? (speed * 2) : speed;
            direction = "right";
            isMoving = true;
        }

        if (isMoving) {
            spriteCounter++;
            if (spriteCounter > 14) {
                spriteNum++;
                if (spriteNum > 4) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            spriteNum = 2; // Assuming spriteNum 2 is the "stand" pose in each direction
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
        }

        graphics2D.drawImage(characterImage, x, y, gamePanel.gameTileSize, gamePanel.gameTileSize * 2, null);
    }


}
