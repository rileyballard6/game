package core;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    final int tileSize = 16;
    final int scale = 3;

    final int gameTileSize = tileSize * scale;
    final int maxScreenCol = 18;
    final int maxScreenRow = 12;
    final int screenWidth = gameTileSize * maxScreenCol;
    final int screenHeight = gameTileSize * maxScreenRow;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }
}
