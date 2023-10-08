package modelo.game;

import helper.FileSound;
import helper.Path;

public class Sounds {
    private final String win;
    private final String tie;

    Sounds() {
        this.tie = Path.getResourcePath() + "tie.wav";
        this.win = Path.getResourcePath() + "win.wav";
    }
    public void emitTieSound() {
        FileSound.handleSound(this.tie);
    }

    public void emitWinSound() {
        FileSound.handleSound(this.win);
    }
}
