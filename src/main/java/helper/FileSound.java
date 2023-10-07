package helper;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class FileSound {
    public static void handleSound(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException, InterruptedException {
        File soundFile = new File(path);
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);

        clip.addLineListener(event -> {
            if (event.getType() == LineEvent.Type.STOP) {
                synchronized (clip) {
                    clip.notify();
                }
            }
        });

        clip.start();

        synchronized (clip) {
            try {
                clip.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        clip.close();
    }
}
