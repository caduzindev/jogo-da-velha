package modelo.user;

import modelo.Board;
import modelo.Field;
import modelo.SymbolField;

import javax.swing.*;
import java.awt.*;

public class Player implements User {
    private final SymbolField symbol = SymbolField.X;
    @Override
    public void makeMove(Board board, Field field) {
        field.check(this.getSymbol());
        board.didWin(field.getIndex());
    }

    private String getSymbol() {
        return this.symbol.toString();
    }
}
