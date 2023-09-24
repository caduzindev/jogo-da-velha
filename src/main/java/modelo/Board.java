package modelo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board {
    private JFrame frame;
    private final ArrayList<Field> fields = new ArrayList<>();
    private final SymbolField[] symbolises = {SymbolField.X, SymbolField.CIRCLE};
    private int alternateSymbol = 0;

    private final int[][] moveTo = { {-1,1},{-3,3},{-2,2},{-4,4} };

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

    public String getSymbol() {
        var symbol = this.symbolises[this.alternateSymbol];
        this.alternateSymbol ^= 1;

        return symbol.toString();
    }

    public void didWin(int posIndexField) {
        Field field = this.fields.get(posIndexField);
        for (var pos : this.moveTo) {
            var firstPos = pos[0];
            var secondPos = pos[1];

            var totFirstDirection = dfsCombinations(
                    field.getIndex() + firstPos,
                    firstPos,
                    field.getSymblo()
            );
            var totSecondDirection = dfsCombinations(
                    field.getIndex() + secondPos,
                    secondPos,
                    field.getSymblo()
            );

            var soma = (totFirstDirection + totSecondDirection) + 1;
            if (soma == 3) {
                System.out.println("Temos um vencedor!!!!");
                this.clearBoard();
            }
        }
    }

    private int dfsCombinations(int index, int move, String symbol) {
        if (index < 0 || index >= this.fields.size()) return 0;

        var actualSymbol = this.fields.get(index).getSymblo();
        if (actualSymbol != null && actualSymbol.equals(symbol)) {
            return 1 + dfsCombinations(index + move, move, symbol);
        }

        return 0;
    }

    private void clearBoard() {
        for (Field field : this.fields) {
            field.remove();
        }
        this.fields.clear();
        this.initialize();
    }
}
