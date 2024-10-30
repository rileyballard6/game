package entities;

import java.awt.image.BufferedImage;

public class Entity {
    public int x,y;
    public int speed;

    public BufferedImage stand, standLeft, standRight, down1, down2, right1, left1;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
}
