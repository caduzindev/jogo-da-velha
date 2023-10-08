package modelo;

import listeners.MouseEventListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Field extends MouseEventListener {
    private final int index;
    private final Board board;
    private final JPanel component;

    private String value = null;

    public Field(int index, Board board) {
        super();
        this.index = index;
        this.board = board;
        this.component = this.setupPanel();
    }
    public void remove() {
        this.removeListener(this.getComponent());
        Container parent = this.getComponent().getParent();
        parent.remove(this.getComponent());
        parent.revalidate();
        parent.repaint();
    }
    private JPanel setupPanel() {
        var panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.DARK_GRAY);
        panel.setPreferredSize(new Dimension(200,200));
        panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        this.onMouseClick(panel, this::onClickField);

        return panel;
    }
    public int getIndex() {
        return index;
    }
    public void onClickField(MouseEvent event) {
        if (this.getValue() == null) {
            this.board.getPlayer().makeMove(this.board, this);
            this.board.getIA().makeMove(this.board, this);
            if (this.board.isFinished()) this.board.setFinished(false);
        }
    }

    public void check(String value) {
        this.setValue(value);

        var label = new JLabel(value);
        label.setFont(new Font("Arial", Font.BOLD, 60));
        label.setOpaque(true);
        label.setBackground(new Color(255, 255, 255, 0));
        label.setForeground(Color.WHITE);

        var gridLayout = new GridBagConstraints();
        gridLayout.gridx = 0;
        gridLayout.gridy = 0;

        this.getComponent().add(label, gridLayout);
        this.refresh();
    }

    public void setBackgroundColor(Color color) {
        this.getComponent().setBackground(color);
        this.refresh();
    }

    public JPanel getComponent() {
        return component;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void refresh() {
        this.getComponent().revalidate();
        this.getComponent().repaint();
    }
}
