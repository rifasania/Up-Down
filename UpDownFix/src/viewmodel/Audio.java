/* Saya Rifa Sania NIM 2206697 mengerjakan evaluasi Tugas Masa Depan dalam mata kuliah
Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan
kecurangan seperti yang telah dispesifikasikan. Aamiin. */

package viewmodel;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {
    // Method untuk memainkan suara
    public static Clip playSound(Clip clip, String filename, Boolean looping){
        try {
            // Mengambil audio input
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File("assets/audio/" + filename).getAbsoluteFile());

            // Mendapatkan resource clip suara
            clip = AudioSystem.getClip();

            // Membuka audio clip dan memuat sampel dari audio input stream
            clip.open(audioIn);
            clip.start();
            if (looping){
                clip.loop(Clip.LOOP_CONTINUOUSLY); // loop audio jika diperlukan
            }

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        return clip;
    }

    // Method untuk menghentikan suara
    public static void stopSound(Clip clip){
        clip.stop();
    }
}
