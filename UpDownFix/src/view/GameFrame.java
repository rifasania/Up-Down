/* Saya Rifa Sania NIM 2206697 mengerjakan evaluasi Tugas Masa Depan dalam mata kuliah
Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan
kecurangan seperti yang telah dispesifikasikan. Aamiin. */

package view;

import viewmodel.Collision;
import viewmodel.Game;
import viewmodel.InputManager;
import viewmodel.ObjectManager;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JPanel{
    // Deklarasi variabel
    private Game game;
    private ObjectManager objectManager;
    private InputManager inputManager;
    private Collision collision;

    private int frameWidth = 640;
    private int frameHeight = 640;

    private Image backgroundImage;
    private Image playerImage;
    private Image footingImage;
    private Image swingImage;
    private Image blockImage;

    // Konstruktor
    public GameFrame() {
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setFocusable(true);

        // Inisialisasi gambar
        backgroundImage = new ImageIcon(getClass().getResource("../assets/image/background1.jpg")).getImage();
        playerImage = new ImageIcon(getClass().getResource("../assets/image/kirby.png")).getImage();
        footingImage = new ImageIcon(getClass().getResource("../assets/image/footing2.png")).getImage();
        swingImage = new ImageIcon(getClass().getResource("../assets/image/swing3.png")).getImage();
        blockImage = new ImageIcon(getClass().getResource("../assets/image/block.png")).getImage();

        MenuFrame mainMenu = new MenuFrame();
        game = new Game(this, mainMenu);
        objectManager = new ObjectManager(this);
        inputManager = new InputManager(this);
        collision = new Collision(this);
    }

    // Method untuk menggambar komponen
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.draw(g);
    }

    // setter dan getter
    public int getFrameWidth() { return frameWidth; }
    public int getFrameHeight() { return frameHeight; }
    public Game getGame() { return game; }
    public ObjectManager getObjectManager() { return objectManager; }
    public Collision getCollision() { return collision; }
    public Image getBackgroundImage() { return backgroundImage; }
    public Image getPlayerImage() { return playerImage; }
    public Image getFootingImage() { return footingImage; }
    public Image getSwingImage() { return swingImage; }
    public Image getBlockImage() { return blockImage; }

}
