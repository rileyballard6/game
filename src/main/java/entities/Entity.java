package entities;

import java.awt.image.BufferedImage;

public class Entity {
    public int x,y;
    public int speed;

    public BufferedImage stand, standLeft, standRight, standUp, down1, down2, right1, right2, left1, left2, up1, up2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
}
