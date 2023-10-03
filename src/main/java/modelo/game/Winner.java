package modelo.game;

import modelo.Field;
import modelo.user.User;

import java.util.ArrayList;

public class Winner {
    private final User user;
    private ArrayList<Field> winnerFields;

    Winner(User user) {
        this.user = user;
    }

    public void setWinnerFields(ArrayList<Field> winnerFields) {
        this.winnerFields = winnerFields;
    }

    public User getUser() {
        return user;
    }

    public ArrayList<Field> getWinnerFields() {
        return winnerFields;
    }
}
