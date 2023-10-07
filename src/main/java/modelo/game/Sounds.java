package modelo.game;

import helper.FileSound;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

abstract public class Sounds {
    private final String win;
    private final String tie;

    Sounds() {
        this.tie = this.getResourcePath() + "tie.wav";
        this.win = this.getResourcePath() + "win.wav";
    }
    private String getResourcePath() {
        return System.getProperty("user.dir")
                + File.separator
                + "src"
                + File.separator
                + "main"
                + File.separator
                + "resources"
                + File.separator;
    }
    protected void emitTieSound() {
        try {
            FileSound.handleSound(this.tie);
        } catch (UnsupportedAudioFileException | LineUnavailableException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void emitWinSound() {
        try {
            FileSound.handleSound(this.win);
        } catch (UnsupportedAudioFileException | LineUnavailableException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
