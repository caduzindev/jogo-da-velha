package modelo.game;

import modelo.Field;
import modelo.user.User;

import java.util.ArrayList;

public class Winner {
    private ArrayList<Field> winnerFields;

    public void setWinnerFields(ArrayList<Field> winnerFields) {
        this.winnerFields = winnerFields;
    }

    public ArrayList<Field> getWinnerFields() {
        return winnerFields;
    }
}
