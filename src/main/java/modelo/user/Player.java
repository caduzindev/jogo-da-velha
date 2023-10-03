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

            var win = board.won(field.getIndex(), this);
            if (win) board.finishBoard();

            var tied = board.tied();
            if (tied) board.clearBoard();
        }
    }

    public String getSymbol() {
        return this.symbol.toString();
    }
}
