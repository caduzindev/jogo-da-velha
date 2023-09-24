package modelo;

import listeners.MouseEventListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Field extends MouseEventListener {
    private final int index;
    private final Board board;
    private final JPanel component;

    private String symblo = null;

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
        var actualSymblo = this.board.getSymbol();
        this.symblo = actualSymblo;

        var label = new JLabel(actualSymblo);
        label.setFont(new Font("Arial", Font.BOLD, 60));
        label.setOpaque(true);
        label.setBackground(Color.DARK_GRAY);
        label.setForeground(Color.WHITE);

        var gridLayout = new GridBagConstraints();
        gridLayout.gridx = 0;
        gridLayout.gridy = 0;

        this.component.add(label, gridLayout);
        this.component.revalidate();
        this.component.repaint();

        this.board.didWin(this.getIndex());
    }

    public JPanel getComponent() {
        return component;
    }

    public String getSymblo() {
        return this.symblo;
    }
}
