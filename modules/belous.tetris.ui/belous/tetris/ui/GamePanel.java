package belous.tetris.ui;

import belous.tetris.game.api.Move;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.concurrent.BlockingQueue;

public class GamePanel extends JPanel {
    private final BlockingQueue<Move> queue;

    public GamePanel(final BlockingQueue<Move> queue) {
        // set colors
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        setBackground(Color.BLACK);

        this.queue = queue;

        setKeyBinding(KeyEvent.VK_LEFT, "move_left", Move.LEFT);
        setKeyBinding(KeyEvent.VK_RIGHT, "move_right", Move.RIGHT);
        setKeyBinding(KeyEvent.VK_DOWN, "move_down", Move.DOWN);

    }

    private void setKeyBinding(final int key, final String actionName, final Move move) {
        this.getInputMap().put(KeyStroke.getKeyStroke(key, 0), actionName);
        this.getActionMap().put("move_down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queue.put(move);
            }
        });
    }

    public Dimension getPreferredSize() {
        return new Dimension(250, 500);
    }
}
