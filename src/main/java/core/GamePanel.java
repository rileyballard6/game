package core;

import entities.Player;
import environment.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    public final int tileSize = 16;
    public final int scale = 3;

    //Screen Dimensions
    public final int gameTileSize = tileSize * scale;
    public final int maxScreenCol = 18;
    public final int maxScreenRow = 12;
    public final int screenWidth = gameTileSize * maxScreenCol;
    public final int screenHeight = gameTileSize * maxScreenRow;

    //World Dimensions
    public final int maxWorldCol = 36;
    public final int maxWorldRow = 23;
    public final int worldWidth = gameTileSize * maxWorldCol;
    public final int worldHeight = gameTileSize * maxWorldRow;

    final double FPS = 60;

    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    MouseHandler mouseHandler = new MouseHandler();
    Thread gameThread;
    public Player mainCharacter = new Player(this, keyHandler, mouseHandler);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        this.addMouseListener(mouseHandler);
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
        tileManager.draw(graphics2D);
        mainCharacter.draw(graphics2D);

        graphics2D.dispose();

    }
}
