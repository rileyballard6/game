package core;

import entities.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int tileSize = 16;
    final int scale = 3;

    public final int gameTileSize = tileSize * scale;
    final int maxScreenCol = 18;
    final int maxScreenRow = 12;
    final int screenWidth = gameTileSize * maxScreenCol;
    final int screenHeight = gameTileSize * maxScreenRow;

    final double FPS = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Player mainCharacter = new Player(this, keyHandler);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    //Instantiates the thread object with the gamePanel object
    public void startGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    //Core of the game. Updates information and Draws info on screen
    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            update();

            repaint();


            try {
                double remainingTime = nextDrawTime - System.nanoTime();

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime/1000000);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void update() {
        mainCharacter.update();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D)graphics;
        mainCharacter.draw(graphics2D);

        graphics2D.dispose();

    }
}
