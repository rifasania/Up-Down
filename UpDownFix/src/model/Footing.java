/* Saya Rifa Sania NIM 2206697 mengerjakan evaluasi Tugas Masa Depan dalam mata kuliah
Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan
kecurangan seperti yang telah dispesifikasikan. Aamiin. */

package model;

import java.awt.*;

public class Footing {
    // Deklarasi variabel
    private int posX;
    private int posY;
    private int width;
    private int height;
    private Image image;
    private int velocityX;
    private boolean passed;
    private boolean isScored = false;
    private int scoreValue;

    // Konstruktor
    public Footing(int posX, int posY, int width, int height, Image image) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;

        this.velocityX = -3;
        this.passed = false;
    }

    // getter dan setter
    public int getPosX() { return posX; }

    public void setPosX(int posX) { this.posX = posX; }

    public int getPosY() { return posY; }

    public void setPosY(int posY) { this.posY = posY; }

    public int getWidth() { return width; }

    public void setWidth(int width) { this.width = width; }

    public int getHeight() { return height; }

    public void setHeight(int height) { this.height = height; }

    public Image getImage() { return image; }

    public void setImage(Image image) { this.image = image; }

    public int getVelocityX() { return velocityX; }

    public void setVelocityX(int velocityX) { this.velocityX = velocityX; }

    public boolean getPassed() { return passed; }

    public void setPassed(boolean passed) { this.passed = passed; }

    public boolean isScored() {
        return isScored;
    }

    public void setScored(boolean scored) {
        isScored = scored;
    }

    public int getScoreValue() {
        return scoreValue;
    }
}
