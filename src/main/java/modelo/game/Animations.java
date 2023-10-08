package modelo.game;

import helper.Path;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Animations {
    protected void tieAnimation(JFrame frame) {
        String pathImage = Path.getResourcePath() + "old_lady.jpg";
        Image image = new ImageIcon(pathImage).getImage().getScaledInstance(300,300, Image.SCALE_DEFAULT);
        JLabel label = new JLabel(new ImageIcon(image));

        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setSize(new Dimension(300,300));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setSize(new Dimension(600,600));
        panel.setOpaque(false);
        panel.add(label,BorderLayout.CENTER);

        frame.getLayeredPane().add(panel, JLayeredPane.POPUP_LAYER);

        frame.repaint();

        Timer timer = new Timer(7000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    frame.getLayeredPane().remove(panel);
                    frame.getLayeredPane().revalidate();
                    frame.getLayeredPane().repaint();
                });
            }
        });

        timer.setRepeats(false);
        timer.start();
    }
}
