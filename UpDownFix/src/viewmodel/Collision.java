/* Saya Rifa Sania NIM 2206697 mengerjakan evaluasi Tugas Masa Depan dalam mata kuliah
Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan
kecurangan seperti yang telah dispesifikasikan. Aamiin. */

package viewmodel;

import model.Block;
import model.Footing;
import model.Player;
import model.Swing;
import view.GameFrame;

public class Collision {
    private GameFrame gameFrame;

    // Konstruktor
    public Collision(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    // Method untuk mendeteksi dan menangani tabrakan antara player dan footing
    public void collide(Player player, Footing pipe) {
        if (checkCollision(player, pipe))
        {
            // Menghitung jarak dari berbagai sisi
            float leftDistance = Math.abs((player.getPosX() + player.getWidth()) - pipe.getPosX());
            float rightDistance = Math.abs(player.getPosX() - (pipe.getPosX() + pipe.getWidth()));
            float downDistance = Math.abs((player.getPosY() + player.getHeight()) - pipe.getPosY());
            float upDistance = Math.abs(player.getPosY() - (pipe.getPosY() + pipe.getHeight()));

            // Mengatasi tabrakan berdasarkan sisi terdekat
            if (leftDistance < rightDistance && leftDistance < downDistance && leftDistance < upDistance) {
                player.setVelocityX(-3);
                player.setPosX(pipe.getPosX() - player.getWidth());
            }
            else if (rightDistance < downDistance && rightDistance < upDistance) {
                player.setVelocityX(0);
                player.setPosX(pipe.getPosX() + pipe.getWidth());
            }
            else if (downDistance < upDistance) {
                player.setVelocityY(0);
                player.setPosY(pipe.getPosY() - player.getHeight());
            }
            else {
                player.setVelocityY(0);
                player.setPosY(pipe.getPosY() + pipe.getHeight());
            }
        }
    }

    // Method untuk mendeteksi dan menangani tabrakan antara player dan swing
    public void collide(Player player, Swing swing) {
        if (checkCollision(player, swing)) {
            float bottomDistance = swing.getPosY() + swing.getHeight() - player.getPosY();

            if (bottomDistance >= 0 && bottomDistance <= player.getHeight()) {
                // Pemain berada di bawah ayunan dan bersentuhan dengan bagian bawah ayunan
                player.setVelocityY(0); // Hentikan gerakan vertikal
                player.setPosY(swing.getPosY() + swing.getHeight()); // Posisikan pemain tepat di bawah ayunan
            } else {
                float leftDistance = Math.abs((player.getPosX() + player.getWidth()) - swing.getPosX());
                float rightDistance = Math.abs(player.getPosX() - (swing.getPosX() + swing.getWidth()));
                float upDistance = Math.abs((player.getPosY() + player.getHeight()) - swing.getPosY());

                if (leftDistance < rightDistance && leftDistance < upDistance) {
                    player.setVelocityX(-3);
                    player.setPosX(swing.getPosX() - player.getWidth());
                } else if (rightDistance < upDistance) {
                    player.setVelocityX(0);
                    player.setPosX(swing.getPosX() + swing.getWidth());
                } else {
                    player.setVelocityY(0); // Stop falling
                    player.setPosY(swing.getPosY() - player.getHeight());
                }
            }
        }
    }

    // Method untuk mendeteksi dan menangani tabrakan antara player dan block
    public void collide(Player player, Block block) {
        if (checkCollision(player, block)) {
            float leftDistance = Math.abs((player.getPosX() + player.getWidth()) - block.getPosX());
            float rightDistance = Math.abs(player.getPosX() - (block.getPosX() + block.getWidth()));
            float downDistance = Math.abs((player.getPosY() + player.getHeight()) - block.getPosY());
            float upDistance = Math.abs(player.getPosY() - (block.getPosY() + block.getHeight()));

            if (leftDistance < rightDistance && leftDistance < downDistance && leftDistance < upDistance) {
                player.setVelocityX(-3);
                player.setPosX(block.getPosX() - player.getWidth());
            } else if (rightDistance < downDistance && rightDistance < upDistance) {
                player.setVelocityX(0);
                player.setPosX(block.getPosX() + block.getWidth());
            } else if (downDistance < upDistance) {
                player.setVelocityY(0);
                player.setPosY(block.getPosY() - player.getHeight());
            } else {
                player.setVelocityY(0);
                player.setPosY(block.getPosY() + block.getHeight());
            }
        }
    }

    // Method untuk memeriksa apakah terjadi tabrakan antara player dan footing
    private boolean checkCollision(Player player, Footing footing) {
        return  player.getPosX() < footing.getPosX() + footing.getWidth() &&
                player.getPosX() + player.getWidth() > footing.getPosX() &&
                player.getPosY() < footing.getPosY() + footing.getHeight() &&
                player.getPosY() + player.getHeight() > footing.getPosY();
    }

    // Method untuk memeriksa apakah terjadi tabrakan antara player dan footing
    private boolean checkCollision(Player player, Swing swing) {
        return player.getPosX() < swing.getPosX() + swing.getWidth() &&
                player.getPosX() + player.getWidth() > swing.getPosX() &&
                player.getPosY() < swing.getPosY() + swing.getHeight() &&
                player.getPosY() + player.getHeight() > swing.getPosY();
    }

    // Method untuk memeriksa apakah terjadi tabrakan antara player dan block
    private boolean checkCollision(Player player, Block block) {
        return player.getPosX() < block.getPosX() + block.getWidth() &&
                player.getPosX() + player.getWidth() > block.getPosX() &&
                player.getPosY() < block.getPosY() + block.getHeight() &&
                player.getPosY() + player.getHeight() > block.getPosY();
    }

    private boolean isPlayerStandingOnSwing(Player player, Swing swing) {
        return player.getPosY() + player.getHeight() >= swing.getPosY() &&
                player.getPosY() + player.getHeight() <= swing.getPosY() + 10 &&
                player.getPosX() + player.getWidth() >= swing.getPosX() &&
                player.getPosX() <= swing.getPosX() + swing.getWidth();
    }
}
