package listeners;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseEventListener {
    private MouseAdapter mouseAdapter = null;
    protected <T> void onMouseClick(JComponent component, HandlerMouseClick function) {
        this.mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                function.handler(e);
            }
        };
        component.addMouseListener(this.mouseAdapter);
    }

    protected void removeListener(JComponent component) {
        if (this.mouseAdapter != null) {
            component.removeMouseListener(this.mouseAdapter);
        }
    }
}
