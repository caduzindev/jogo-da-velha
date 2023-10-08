package helper;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileSound {
    private static final ExecutorService soundExecutor = Executors.newSingleThreadExecutor();
    public static void handleSound(String path) {
        soundExecutor.submit(() -> {
            try {
                File soundFile = new File(path);
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);

                clip.start();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        });
    }
}
