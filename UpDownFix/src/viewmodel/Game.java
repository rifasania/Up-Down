/* Saya Rifa Sania NIM 2206697 mengerjakan evaluasi Tugas Masa Depan dalam mata kuliah
Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan
kecurangan seperti yang telah dispesifikasikan. Aamiin. */

package viewmodel;

import model.*;
import view.GameFrame;
import view.MenuFrame;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game implements ActionListener {
    // Deklarasi variabel untuk instansiasi
    private GameFrame gameFrame;
    private int playerStartPosX = 360/8;
    private int playerStartPosY = 640/2;
    private int playerWidth = 54;
    private int playerHeight = 44;
    private Player player;
    private Timer gameLoop;
    private int gravity = 1;
    private boolean isGameOver = false;
    private int score = 0;
    private int scoreUp = 0;
    private int scoreDown = 0;
    private int totalScore = 0;
    private String username;
    private MenuFrame menuFrame;
    private int highScore = 0;
    private Clip backgroundSound;
    private Clip gameOverSound;
    private Clip scoreSound;
    private static final int minScore = 60;
    private static final int maxScore = 100;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setScoreUp(int scoreUp) {
        this.scoreUp = scoreUp;
    }

    public void setScoreDown(int scoreDown) {
        this.scoreDown = scoreDown;
    }
    public Player getPlayer() { return player; }
    public boolean getIsGameOver() { return isGameOver; }


    // Konstruktor
    public Game(GameFrame gameFrame, MenuFrame menuFrame) {
        // Inisialisasi game
        this.gameFrame = gameFrame;
        this.menuFrame = menuFrame;

        // Membuat objek player
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, gameFrame.getPlayerImage());

        // Membuat dan memulai game loop
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();

        // Inisialisasi suara background
        backgroundSound = Audio.playSound(backgroundSound, "background_music.wav", true);

        // Inisialisasi suara game over dan skor dengan null terlebih dahulu
        gameOverSound = null;
        scoreSound = null;

    }

    // Method untuk menggambar elemen-elemen game
    public void draw(Graphics g) {
        // Menggambar background
        g.drawImage(gameFrame.getBackgroundImage(), 0, 0, 640, 640, null);

        // Menggambar swing
        for (Swing swing : gameFrame.getObjectManager().getSwings()) {
            g.drawImage(swing.getImage(), swing.getPosX(), swing.getPosY(), swing.getWidth(), swing.getHeight(), null);
        }

        // Menggambar block
        for (Block block : gameFrame.getObjectManager().getBlocks()) {
            g.drawImage(block.getImage(), block.getPosX(), block.getPosY(), block.getWidth(), block.getHeight(), null);
        }

        // Menggambar player
        g.drawImage(gameFrame.getPlayerImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        // Menggambar footing
        for (Footing footing : gameFrame.getObjectManager().getFootings()) {
            g.drawImage(footing.getImage(), footing.getPosX(), footing.getPosY(), footing.getWidth(), footing.getHeight(), null);
        }

        // Menggambar label skor
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Score: " + String.valueOf(totalScore), 10, 35);
        g.drawString("Up: " + String.valueOf(scoreUp), 10, 70);
        g.drawString("Down: " + String.valueOf(scoreDown), 10, 105);
    }

    // Method untuk menggerakkan elemen-elemen dalam game
    public void move() {
        // Menggerakkan player
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosX(player.getPosX() + player.getVelocityX());
        player.setPosY(Math.max(player.getPosY(), 0));
        player.setPosX(Math.min(player.getPosX(), gameFrame.getFrameWidth() - player.getWidth()));

        // Menggerakkan dan memeriksa collision dengan footing
        for (Footing footing : gameFrame.getObjectManager().getFootings()) {
            footing.setPosX(footing.getPosX() + footing.getVelocityX());

            // Memeriksa apakah player mendarat di atas footing
            if (player.getPosY() + player.getHeight() >= footing.getPosY() &&
                    player.getPosY() <= footing.getPosY() &&
                    player.getPosX() >= footing.getPosX() &&
                    player.getPosX() <= footing.getPosX() + footing.getWidth() &&
                    !footing.isScored()) {
                scoreDown += 1; // Tambahkan point down dengan nilai 1 setiap pijakan
                int score = randomScore(footing.getHeight(), gameFrame.getFrameHeight());
                totalScore += score;
                footing.setScored(true); // set true untuk pemberian score tiap footing yang sudah diinjak
                Audio.playSound(scoreSound, "sound_score2.wav", false); // Mainkan suara skor bertambah
            }

            gameFrame.getCollision().collide(player, footing);
        }

        // Menggerakkan dan memeriksa collision dengan swing
        for (Swing swing : gameFrame.getObjectManager().getSwings()) {
            swing.setPosX(swing.getPosX() + swing.getVelocityX());

            // Memeriksa apakah player menyentuh swing
            if (player.getPosY() + player.getHeight() >= swing.getPosY() &&
                    player.getPosY() <= swing.getPosY() + swing.getHeight() &&
                    player.getPosX() >= swing.getPosX() &&
                    player.getPosX() <= swing.getPosX() + swing.getWidth() &&
                    !swing.isScored()) {
                scoreUp += 1; // Tambahkan skor up dengan nilai 1 tiap berhasil bergelantung di swing
                int score = randomScore(swing.getHeight(), gameFrame.getFrameHeight());
                totalScore += score;
                swing.setScored(true); // set true untuk pemberian score tiap swing yang sudah diinjak
                Audio.playSound(scoreSound, "sound_score2.wav", false); // Mainkan suara skor bertambah
            }

            // Mengizinkan player untuk melewati swing dengan overflow
            if (!checkOverflow(player, swing)) {
                gameFrame.getCollision().collide(player, swing);
            }
        }

        // Menggerakkan dan memeriksa collision dengan block
        for (Block block : gameFrame.getObjectManager().getBlocks()) {
            block.setPosX(block.getPosX() + block.getVelocityX());
            gameFrame.getCollision().collide(player, block);
        }

        // Memeriksa apakah gameover
        if (player.getPosY() > gameFrame.getFrameHeight() || player.getPosX() < 0) {
            isGameOver = true;
        }
    }

    // Method untuk menghitung score berdasarkan ukuran objek
    private int randomScore(int size, int maxSize) {
        // Semakin kecil ukuran, semakin tinggi skor
        double ratio = 1.0 - ((double) size / maxSize);
        return (int) (minScore + ratio * (maxScore - minScore));
    }

    // Method untuk memeriksa apakah player berdiri di atas swing
    private boolean checkPlayerStandingOnSwing(Player player, Swing swing) {
        return player.getPosY() + player.getHeight() >= swing.getPosY() &&
                player.getPosY() + player.getHeight() <= swing.getPosY() + swing.getHeight() &&
                player.getPosX() + player.getWidth() >= swing.getPosX() &&
                player.getPosX() <= swing.getPosX() + swing.getWidth();
    }

    // Method untuk memeriksa apakah player melewati swing
    private boolean checkOverflow(Player player, Swing swing) {
        return player.getPosY() + player.getHeight() >= swing.getPosY() &&
                player.getPosY() <= swing.getPosY() + swing.getHeight();
    }

    // Implementasi ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        gameFrame.repaint();
        if (isGameOver) {
            gameLoop.stop();
            gameFrame.getObjectManager().getObjectCooldown().stop();
            saveScoreAndUsername(); // Panggil method untuk menyimpan username dan skor

            playGameOverSound();

            if (showGameOverMessage()) {
                // Pengguna memilih "Back to Menu"
                JFrame gameFrame1 = (JFrame) SwingUtilities.getWindowAncestor(gameFrame);
                gameFrame1.dispose(); // Menutup layar game
                menuFrame.setVisible(true); // Tampilkan MenuFrame
                menuFrame.refreshScoreTable(); // Panggil method untuk memperbarui tabel skor
            }
        }
    }

    // Method untuk menyimpan score dan username
    public void saveScoreAndUsername() {
        // Simpan username dan skor
        try {
            TableScore tableScore = new TableScore();
            tableScore.insertUpdate(username, totalScore, scoreUp, scoreDown);
            highScore = tableScore.getHighScore(username); // Mendapatkan skor tertinggi sebelumnya
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Method untuk me-restart game
    public void Restart() {
        // Me-reset posisi dan kecepatan player
        player.setPosY(playerStartPosY);
        player.setPosX(playerStartPosX);
        player.setVelocityY(-6);
        player.setVelocityX(0);

        // Membersihkan objek objek game
        gameFrame.getObjectManager().getFootings().clear();
        score = 0; // Reset score menjadi 0
        scoreUp = 0; // Reset scoreUp menjadi 0
        scoreDown = 0; // Reset scoreDown menjadi 0
        totalScore = 0; // Reset totalScore menjadi 0
        isGameOver = false;

        // Memulai kembali game loop dan object cooldown
        gameLoop.start();
        gameFrame.getObjectManager().getObjectCooldown().start();

        // Me-restart suara background
        Audio.stopSound(backgroundSound);
        backgroundSound = Audio.playSound(backgroundSound, "background_music.wav", false);
    }

    // Method untuk menampilkan pesan game over
    private boolean showGameOverMessage() {
        String message = "GAME OVER!\n\n" +
                "Username: " + username + "\n" +
                "Score: " + totalScore + "\n" +
                "Up: " + scoreUp + "\n" +
                "Down: " + scoreDown + "\n";

        if (totalScore == highScore) {
            message += "\nNew HighScore!";
        }

        int option = JOptionPane.showOptionDialog(
                null,
                message,
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new String[]{"Restart", "Back to Menu"},
                "Restart"
        );

        if (option == JOptionPane.YES_OPTION) {
            restartGame();
            return false; // Tidak kembali ke menu
        } else {
            return true; // Kembali ke menu
        }
    }

    // Method untuk me-restart game
    public void restartGame() {
        Restart();
        gameLoop.start();
        gameFrame.getObjectManager().getObjectCooldown().start();
    }

    // Method untuk memainkan suara game over
    private void playGameOverSound() {
        Audio.stopSound(backgroundSound); // Hentikan suara background

        // Inisialisasi gameOverSound jika belum diinisialisasi
        if (gameOverSound == null) {
            gameOverSound = Audio.playSound(null, "game_over.wav", false);
        } else {
            Audio.playSound(gameOverSound, "game_over.wav", false); // Mainkan suara game over
        }
    }

    // Method untuk menghentikan game
    public void stopGame() {
        isGameOver = true;
    }

    // Getter dan setter variabel lainnya
    public GameFrame getGameFrame() {
        return gameFrame;
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public int getPlayerStartPosX() {
        return playerStartPosX;
    }

    public void setPlayerStartPosX(int playerStartPosX) {
        this.playerStartPosX = playerStartPosX;
    }

    public int getPlayerStartPosY() {
        return playerStartPosY;
    }

    public void setPlayerStartPosY(int playerStartPosY) {
        this.playerStartPosY = playerStartPosY;
    }

    public int getPlayerWidth() {
        return playerWidth;
    }

    public void setPlayerWidth(int playerWidth) {
        this.playerWidth = playerWidth;
    }

    public int getPlayerHeight() {
        return playerHeight;
    }

    public void setPlayerHeight(int playerHeight) {
        this.playerHeight = playerHeight;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Timer getGameLoop() {
        return gameLoop;
    }

    public void setGameLoop(Timer gameLoop) {
        this.gameLoop = gameLoop;
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public int getScore() {
        return score;
    }

    public int getScoreUp() {
        return scoreUp;
    }

    public int getScoreDown() {
        return scoreDown;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public String getUsername() {
        return username;
    }

    public MenuFrame getMenuFrame() {
        return menuFrame;
    }

    public void setMenuFrame(MenuFrame menuFrame) {
        this.menuFrame = menuFrame;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public Clip getBackgroundSound() {
        return backgroundSound;
    }

    public void setBackgroundSound(Clip backgroundSound) {
        this.backgroundSound = backgroundSound;
    }

    public Clip getGameOverSound() {
        return gameOverSound;
    }

    public void setGameOverSound(Clip gameOverSound) {
        this.gameOverSound = gameOverSound;
    }

    public Clip getScoreSound() {
        return scoreSound;
    }

    public void setScoreSound(Clip scoreSound) {
        this.scoreSound = scoreSound;
    }
}
