package modelo.user;

import modelo.Board;
import modelo.Field;

public interface User {
    void makeMove(Board board, Field field);
}
