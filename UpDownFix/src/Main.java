/* Saya Rifa Sania NIM 2206697 mengerjakan evaluasi Tugas Masa Depan dalam mata kuliah
Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan
kecurangan seperti yang telah dispesifikasikan. Aamiin. */

import view.MenuFrame;

import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        // Membuat frame utama untuk menu
        JFrame menuFrame = new JFrame("Up Down");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(640, 640);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setResizable(false);

        // Membuat instansiasi MenuFrame
        MenuFrame mainMenu = new MenuFrame();
        // Menambahkan panel utama dari MenuFrame ke menuFrame
        menuFrame.add(mainMenu.getMainPanel());
        // Menampilkan menuFrame
        menuFrame.setVisible(true);
    }
}

// Music : https://www.chosic.com/download-audio/29781/
// Game Over : https://mixkit.co/free-sound-effects/game-over/
// Score Sound : https://mixkit.co/free-sound-effects/game/
// Jump Sound : https://mixkit.co/free-sound-effects/game/
// Image Kayu : https://pin.it/7HVPpiObL
// Image Player : https://pin.it/5Tn3nlNzj
// Image Footing : https://pin.it/6Rn43nHwb