package core;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int tileSize = 16;
    final int scale = 3;

    final int gameTileSize = tileSize * scale;
    final int maxScreenCol = 18;
    final int maxScreenRow = 12;
    final int screenWidth = gameTileSize * maxScreenCol;
    final int screenHeight = gameTileSize * maxScreenRow;

    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }

    //Instantiates the thread object with the gamePanel object
    public void startGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    //Core of the game. Updates information and Draws info on screen
    @Override
    public void run() {

        while (gameThread != null) {
            System.out.println("Test Running");

            update();

            repaint();
        }

    }

    public void update() {

    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D)graphics;

        graphics2D.setColor(Color.blue);

        graphics2D.fillRect(100, 100, gameTileSize, gameTileSize);

        graphics2D.dispose();

    }
}
