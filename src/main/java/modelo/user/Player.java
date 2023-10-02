package modelo.user;

import modelo.Board;
import modelo.Field;
import modelo.SymbolField;

public class Player implements User {
    private final SymbolField symbol = SymbolField.X;
    @Override
    public void makeMove(Board board, Field field) {
        if (!board.isFinished()) {
            field.check(this.getSymbol());

            var win = board.won(field.getIndex());
            if (win) board.clearBoard();

            var tied = board.tied();
            if (tied) board.clearBoard();

            if (win || tied) board.setFinished(true);
        }
    }

    public String getSymbol() {
        return this.symbol.toString();
    }
}
