/* Saya Rifa Sania NIM 2206697 mengerjakan evaluasi Tugas Masa Depan dalam mata kuliah
Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan
kecurangan seperti yang telah dispesifikasikan. Aamiin. */

package viewmodel;

import model.Block;
import model.Footing;
import model.Swing;
import view.GameFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ObjectManager {
    // Deklarasi variabel
    private GameFrame gameFrame;
    private int objectStartPosX = 640;
    private int objectStartPosY = 0;
    private int objectWidth = 64;
    private int objectHeight = 512;
    private ArrayList<Footing> footings;
    private ArrayList<Swing> swings;
    private ArrayList<Block> blocks;
    private Timer objectCooldown;
    private boolean placeObject = true;

    // Konstruktor
    public ObjectManager(GameFrame gameFrame) {
        this.gameFrame = gameFrame;

        // Inisialisasi ArrayList untuk menempatkan objek-objek game
        footings = new ArrayList<Footing>();
        swings = new ArrayList<>();
        blocks = new ArrayList<>();

        // Membuat dan memulai timer untuk menempatkan objek-objek game
        objectCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (placeObject) {
                    placeFootings();
                } else {
                    placeSwings();
                    placeBlocks();
                }
                placeObject = !placeObject;
            }
        });
        objectCooldown.start();
    }

    // Method untuk menempatkan footing
    public void placeFootings() {
        // Menentukan posisi Y secara acak untuk footing
        int randomPosY = (int) (objectStartPosY - objectHeight/4 - Math.random() * (objectHeight/2));
        int openingSpace = 640/4;

        // Membuat objek Footing baru dan menambahkannya ke ArrayList footings
        Footing newFooting = new Footing(objectStartPosX, (randomPosY + openingSpace + objectHeight), objectWidth, objectHeight, gameFrame.getFootingImage());
        footings.add(newFooting);
    }

    // Method untuk menempatkan ayunan (swing)
    public void placeSwings() {
        // Menentukan posisi Y secara acak untuk ayunan
        int randomPosY = (int) (-objectHeight / 2 - Math.random() * (objectHeight / 2));

        // Membuat objek Swing baru dan menambahkannya ke ArrayList swings
        Swing newSwing = new Swing(objectStartPosX, randomPosY, objectWidth, objectHeight, gameFrame.getSwingImage());
        swings.add(newSwing);
    }

    // Method untuk menempatkan block
    public void placeBlocks() {
        // Mengambil ayunan terakhir yang ditempatkan
        Swing latestSwing = swings.get(swings.size() - 1);
        int blockPosY = latestSwing.getPosY() + latestSwing.getHeight();

        // Membuat objek Block baru dan menambahkannya ke ArrayList blocks
        Block newBlock = new Block(objectStartPosX, blockPosY, objectWidth, 10, gameFrame.getBlockImage());
        blocks.add(newBlock);
    }

    // getter dan setter
    public ArrayList<Footing> getFootings() {
        return footings;
    }

    public ArrayList<Swing> getSwings() {
        return swings;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public Timer getObjectCooldown() {
        return objectCooldown;
    }

    public GameFrame getGameFrame() {
        return gameFrame;
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public int getObjectStartPosX() {
        return objectStartPosX;
    }

    public void setObjectStartPosX(int objectStartPosX) {
        this.objectStartPosX = objectStartPosX;
    }

    public int getObjectStartPosY() {
        return objectStartPosY;
    }

    public void setObjectStartPosY(int objectStartPosY) {
        this.objectStartPosY = objectStartPosY;
    }

    public int getObjectWidth() {
        return objectWidth;
    }

    public void setObjectWidth(int objectWidth) {
        this.objectWidth = objectWidth;
    }

    public int getObjectHeight() {
        return objectHeight;
    }

    public void setObjectHeight(int objectHeight) {
        this.objectHeight = objectHeight;
    }

    public void setFootings(ArrayList<Footing> footings) {
        this.footings = footings;
    }

    public void setSwings(ArrayList<Swing> swings) {
        this.swings = swings;
    }

    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    public void setObjectCooldown(Timer objectCooldown) {
        this.objectCooldown = objectCooldown;
    }

    public boolean isPlaceObject() {
        return placeObject;
    }

    public void setPlaceObject(boolean placeObject) {
        this.placeObject = placeObject;
    }
}
