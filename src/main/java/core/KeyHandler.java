package core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int keyPressed = e.getKeyCode();

        if (keyPressed == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (keyPressed == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (keyPressed == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (keyPressed == KeyEvent.VK_D) {
            rightPressed = true;
        }
    }



    @Override
    public void keyReleased(KeyEvent e) {

        int keyReleased = e.getKeyCode();

        if (keyReleased == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (keyReleased == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (keyReleased == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (keyReleased == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }

}
