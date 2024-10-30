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
        try {
            stand = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/characterSprites/characterStand.png")));
            standLeft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/characterSprites/characterStandLeft.png")));
            standRight = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/characterSprites/characterStandRight.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/characterSprites/characterWalkFront1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/characterSprites/characterWalkFront2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/characterSprites/characterWalkLeft1.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/characterSprites/characterWalkRight1.png")));


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
                characterImage = (spriteNum > 2) ? left1 : standLeft;
                break;
            case "right":
                characterImage = (spriteNum > 2) ? right1 : standRight;
                break;
            case "standing":
                characterImage = stand;
        }

        graphics2D.drawImage(characterImage, x, y, gamePanel.gameTileSize, gamePanel.gameTileSize * 2, null);
    }

}
