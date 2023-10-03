package modelo.user;

import modelo.Board;
import modelo.Field;
import modelo.SymbolField;

import java.util.ArrayList;

public class IA implements User{
    private final SymbolField symbol = SymbolField.CIRCLE;

    @Override
    public void makeMove(Board board, Field adversaryField) {
        if (!board.isFinished()) {
            int bestValue = Integer.MIN_VALUE;
            Field bestField = adversaryField;

            for (Field actualField : board.avaiableFields()) {
                actualField.setValue(this.getSymbol());
                int bestValueMove = minimax(board, actualField, this,false);

                if (bestValueMove > bestValue) {
                    bestValue = bestValueMove;
                    bestField = actualField;
                }
                actualField.setValue(null);
            }

            bestField.check(this.getSymbol());

            var win = board.won(bestField.getIndex(), this);
            if (win) board.finishBoard();

            var tied = board.tied();
            if (tied) board.clearBoard();
        }
    }

    private int minimax(Board board, Field field, User user, boolean isMax) {
        var wonGame = board.won(field.getIndex(), user);

        if (field.getValue().equals(this.getSymbol()) && wonGame) {
            return 1;
        }
        if (field.getValue().equals(SymbolField.X.toString()) && wonGame) {
            return -1;
        }

        if (board.tied()) return 0;

        if (isMax) {
            int bestValue = Integer.MIN_VALUE;

            for (Field actualField : board.avaiableFields()) {
                actualField.setValue(this.getSymbol());
                bestValue = Math.max(bestValue,minimax(board,actualField, user,false));
                actualField.setValue(null);
            }

            return bestValue;
        } else {
            int bestValue = Integer.MAX_VALUE;

            for (Field actualField : board.avaiableFields()) {
                actualField.setValue(SymbolField.X.toString());
                bestValue = Math.min(bestValue,minimax(board,actualField, user,true));
                actualField.setValue(null);
            }

            return bestValue;
        }
    }
    public String getSymbol() {
        return this.symbol.toString();
    }
}
