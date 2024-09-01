/* Saya Rifa Sania NIM 2206697 mengerjakan evaluasi Tugas Masa Depan dalam mata kuliah
Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan
kecurangan seperti yang telah dispesifikasikan. Aamiin. */

package viewmodel;

import view.GameFrame;

import javax.sound.sampled.Clip;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener {
    private GameFrame gameFrame;
    private Clip jumpSound;

    // Konstruktor
    public InputManager(GameFrame gameFrame) {
        this.gameFrame = gameFrame;

        gameFrame.addKeyListener(this);
    }

    // Method ketika sebuah tombol diketik
    @Override
    public void keyTyped(KeyEvent e) {

    }

    // Method ketika sebuah tombol ditekan
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            // Ketika tombol atas atau W ditekan, player melompat ke atas
            gameFrame.getGame().getPlayer().setVelocityY(-12);
            Audio.playSound(jumpSound, "cartoon_jump.wav", false);
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            // Ketika tombol bawah atau S ditekan, player bergerak ke bawah
            gameFrame.getGame().getPlayer().setVelocityY(6);
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            // Ketika tombol kiri atau A ditekan, player bergerak ke kiri
            gameFrame.getGame().getPlayer().setVelocityX(-6);
        }
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            // Ketika tombol kanan atau D ditekan, player bergerak ke kanan
            gameFrame.getGame().getPlayer().setVelocityX(3);
        }
        else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            // Ketika tombol spasi ditekan, game dihentikan
            gameFrame.getGame().stopGame();
        }
    }

    //Method yang dipanggil ketika sebuah tombol dilepas
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            // Ketika tombol kiri, kanan, A, atau D dilepas, kecepatan horizontal player di-reset ke 0
            gameFrame.getGame().getPlayer().setVelocityX(0);
        }
    }
}
