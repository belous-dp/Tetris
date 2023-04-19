package belous.tetris.ui;

import belous.tetris.game.api.Matrix;
import belous.tetris.game.api.Move;
import belous.tetris.game.api.tetromino.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class GamePanel extends JPanel {
    private final BlockingQueue<Move> queue;
    private final static int BORDERS_THICKNESS = 5;
    private final static int WIDTH = 300;
    private final static int HEIGHT = WIDTH * 2;
    private final static int SQUARE_SIDE_LEN = WIDTH / 10;

    private int updatedX, updatedY;
    private Matrix<Class<? extends Tetromino>> cells;

    private final static Map<Class<? extends Tetromino>, Color> COLOR_MAP = Map.of(
            ITetromino.class, Color.white, OTetromino.class, Color.red, TTetromino.class, Color.blue,
            JTetromino.class, Color.green, LTetromino.class, Color.yellow,
            STetromino.class, Color.orange, ZTetromino.class, Color.pink
    );


    public GamePanel(final BlockingQueue<Move> queue) {
        setBorder(BorderFactory.createLineBorder(Color.WHITE, BORDERS_THICKNESS));
        setBackground(Color.BLACK);

        this.queue = queue;

        setMoveKeyBinding(KeyEvent.VK_LEFT, "move_left", Move.LEFT);
        setMoveKeyBinding(KeyEvent.VK_RIGHT, "move_right", Move.RIGHT);
        setMoveKeyBinding(KeyEvent.VK_DOWN, "move_down", Move.DOWN);
        setMoveKeyBinding(KeyEvent.VK_X, "rotate_clockwise", Move.CLOCKWISE);
        setMoveKeyBinding(KeyEvent.VK_Z, "rotate_counterclockwise", Move.COUNTERCLOCKWISE);
    }

    private void setMoveKeyBinding(final int key, final String actionName, final Move move) {
        this.getInputMap().put(KeyStroke.getKeyStroke(key, 0), actionName);
        this.getActionMap().put(actionName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queue.offer(move);
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH + BORDERS_THICKNESS, HEIGHT + BORDERS_THICKNESS);
    }

    public static int indexToPixel(int idx) {
        return idx * SQUARE_SIDE_LEN;
    }

    public void updateState(int updatedX, int updatedY, Matrix<Class<? extends Tetromino>> cells) {
        this.updatedX = updatedX;
        this.updatedY = updatedY;
        this.cells = cells;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (cells == null) { // initial
            return;
        }
        final int h = cells.height();
        final int w = cells.width();
        for (int i = 0, x = updatedX; i < h; i++, x += SQUARE_SIDE_LEN) {
            for (int j = 0, y = updatedY; j < w; j++, y += SQUARE_SIDE_LEN) {
                final Class<? extends Tetromino> type = cells.get(i, j);
                g.setColor(type == null ? this.getBackground() : COLOR_MAP.get(type));
                //noinspection SuspiciousNameCombination
                g.fillRect(y, x, SQUARE_SIDE_LEN, SQUARE_SIDE_LEN);
            }
        }
    }
}
