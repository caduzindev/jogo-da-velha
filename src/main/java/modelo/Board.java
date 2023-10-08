package modelo;

import modelo.game.Game;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import java.util.stream.Collectors;

public class Board extends Game {
    public void initialize() {
        for (var i = 0;i<9;i++) {
            this.fields.add(new Field(i, this));
        }
        JFrame frame = this.getFrame();

        for (Field field : this.getFields()) {
            var component = field.getComponent();
            frame.getContentPane().add(component);
        }

        frame.setSize(600,600);
        frame.setVisible(true);
    }

    public void clearBoard() {
        for (Field field : this.fields) {
            field.remove();
        }
        this.fields.clear();
        this.initialize();
    }

    public ArrayList<Field> avaiableFields() {
        return this.getFields()
                .stream()
                .filter(actualField -> actualField.getValue() == null)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private void paintFieldsWinner() {
        this.getWinner().getWinnerFields().forEach(field -> field.setBackgroundColor(Color.GREEN));
    }

    public void finishBoardWin() {
        this.setFinished(true);
        this.paintFieldsWinner();
        this.emitWinSound();
        this.clearBoard();
    }

    public void finishBoardTie() {
        this.setFinished(true);
        this.tieAnimation(this.getFrame());
        this.emitTieSound();
        this.clearBoard();
    }
}
