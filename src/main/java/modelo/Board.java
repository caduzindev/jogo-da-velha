package modelo;

import modelo.user.IA;
import modelo.user.Player;
import modelo.user.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {
    private JFrame frame;
    private final ArrayList<Field> fields = new ArrayList<>();
    private final User player = new Player();
    private final User IA = new IA();
    public boolean finished = false;

    public void initialize() {
        for (var i = 0;i<9;i++) {
            this.fields.add(new Field(i, this));
        }
        JFrame frame = this.getFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().setLayout(new GridLayout(0,3,3,3));

        for (Field field : this.getFields()) {
            var component = field.getComponent();
            frame.getContentPane().add(component);
        }

        frame.pack();
        frame.setVisible(true);
    }

    private JFrame getFrame() {
        if (this.frame != null) return this.frame;
        JFrame frame = new JFrame("Jogo da Velha");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(0,3,3,3));

        this.frame = frame;
        return this.frame;
    }

    public ArrayList<Field> getFields() {
        return this.fields;
    }

    public boolean won(int posIndexField) {
        Field field = this.fields.get(posIndexField);
        for (int i = 0; i < 3; i++) {
            int rowIndex = i * 3;
            int colIndex = i;

            boolean allMatchInRow = IntStream.range(0, 3)
                    .allMatch(indexRange -> {
                        var valueActualField = this.getFields().get(rowIndex + indexRange).getValue();
                        if (valueActualField == null) return false;
                        return valueActualField.equals(field.getValue());
                    });

            boolean allMatchInCol = IntStream.range(0, 3)
                    .allMatch(indexRange -> {
                        var valueActualField = this.getFields().get(colIndex + indexRange * 3).getValue();
                        if (valueActualField == null) return false;
                        return valueActualField.equals(field.getValue());
                    });

            if (allMatchInRow || allMatchInCol) {
                return true;
            }
        }

        boolean allMatchInMainDiagonal = IntStream.range(0, 3)
                .allMatch(indexRange -> {
                    var valueActualField = this.getFields().get(indexRange * 3 + indexRange).getValue();
                    if (valueActualField == null) return false;
                    return valueActualField.equals(field.getValue());
                });

        boolean allMatchInAntiDiagonal = IntStream.range(0, 3)
                .allMatch(indexRange -> {
                    var valueActualField = this.getFields().get(indexRange * 3 + (2 - indexRange)).getValue();
                    if (valueActualField == null) return false;
                    return valueActualField.equals(field.getValue());
                });

        return allMatchInMainDiagonal || allMatchInAntiDiagonal;
    }

    public boolean tied() {
        return this.getFields().stream().noneMatch(field -> field.getValue() == null);
    }

    public void clearBoard() {
        for (Field field : this.fields) {
            field.remove();
        }
        this.fields.clear();
        this.initialize();
    }

    public User getPlayer() {
        return this.player;
    }

    public User getIA() {
        return this.IA;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public ArrayList<Field> avaiableFields() {
        return this.getFields()
                .stream()
                .filter(actualField -> actualField.getValue() == null)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
