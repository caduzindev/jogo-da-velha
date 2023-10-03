package modelo.game;

import modelo.Field;
import modelo.user.IA;
import modelo.user.Player;
import modelo.user.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class Game {
    private JFrame frame;
    protected final ArrayList<Field> fields = new ArrayList<>();
    public boolean finished = false;
    private final User player = new Player();
    private final User IA = new IA();
    private Winner winner;
    protected JFrame getFrame() {
        if (this.frame != null) return this.frame;
        JFrame frame = new JFrame("Jogo da Velha");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(0,3,3,3));

        this.frame = frame;
        return this.frame;
    }
    public boolean won(int posIndexField, User user) {
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

            if (allMatchInRow) {
                this.setWinner(user, this.getFieldsWinner(indexRange -> rowIndex + indexRange));
                return true;
            }

            if (allMatchInCol) {
                this.setWinner(user, this.getFieldsWinner(indexRange -> colIndex + indexRange * 3));
                return true;
            }
        }

        boolean allMatchInMainDiagonal = IntStream.range(0, 3)
                .allMatch(indexRange -> {
                    var valueActualField = this.getFields().get(indexRange * 3 + indexRange).getValue();
                    if (valueActualField == null) return false;
                    return valueActualField.equals(field.getValue());
                });
        if (allMatchInMainDiagonal) {
            this.setWinner(user, this.getFieldsWinner(indexRange -> indexRange * 3 + indexRange));
            return true;
        }

        boolean allMatchInAntiDiagonal = IntStream.range(0, 3)
                .allMatch(indexRange -> {
                    var valueActualField = this.getFields().get(indexRange * 3 + (2 - indexRange)).getValue();
                    if (valueActualField == null) return false;
                    return valueActualField.equals(field.getValue());
                });
        if (allMatchInAntiDiagonal) {
            this.setWinner(user, this.getFieldsWinner(indexRange -> indexRange * 3 + (2 - indexRange)));
            return true;
        }

        return false;
    }

    public boolean tied() {
        return this.getFields().stream().noneMatch(field -> field.getValue() == null);
    }

    public ArrayList<Field> getFields() {
        return this.fields;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public User getPlayer() {
        return this.player;
    }

    public User getIA() {
        return this.IA;
    }

    private void setWinner(User user, ArrayList<Field> fields) {
        this.winner = new Winner(user);
        winner.setWinnerFields(fields);
    }

    public Winner getWinner() {
        return this.winner;
    }

    private ArrayList<Field> getFieldsWinner(Function<Integer, Integer> expression) {
        return IntStream.range(0,3)
                .mapToObj(indexRange -> this.getFields().get(expression.apply(indexRange)))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
