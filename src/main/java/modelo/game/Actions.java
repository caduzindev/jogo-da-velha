package modelo.game;

import javax.swing.*;

abstract public class Actions {
    private final Sounds sounds;
    private final Animations animations;

    Actions() {
        this.sounds = new Sounds();
        this.animations = new Animations();
    }

    public void emitTieSound() {
        this.sounds.emitTieSound();
    }

    public void emitWinSound() {
        this.sounds.emitWinSound();
    }

    public void tieAnimation(JFrame frame) {
        this.animations.tieAnimation(frame);
    }
}
