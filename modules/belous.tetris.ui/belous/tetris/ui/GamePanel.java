package belous.tetris.ui;

import belous.tetris.game.api.Matrix;
import belous.tetris.game.api.Move;
import belous.tetris.game.api.tetromino.ITetromino;
import belous.tetris.game.api.tetromino.OTetromino;
import belous.tetris.game.api.tetromino.TTetromino;
import belous.tetris.game.api.tetromino.Tetromino;

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
            ITetromino.class, Color.white, OTetromino.class, Color.red, TTetromino.class, Color.blue
    );


    public GamePanel(final BlockingQueue<Move> queue) {
        // set colors
        setBorder(BorderFactory.createLineBorder(Color.WHITE, BORDERS_THICKNESS));
        setBackground(Color.BLACK);

        this.queue = queue;

        setKeyBinding(KeyEvent.VK_LEFT, "move_left", Move.LEFT);
        setKeyBinding(KeyEvent.VK_RIGHT, "move_right", Move.RIGHT);
        setKeyBinding(KeyEvent.VK_DOWN, "move_down", Move.DOWN);
    }

    private void setKeyBinding(final int key, final String actionName, final Move move) {
        this.getInputMap().put(KeyStroke.getKeyStroke(key, 0), actionName);
        this.getActionMap().put(actionName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean added = queue.offer(move);
                assert(added);
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
        System.out.println(g.getClip());
        if (cells == null) { // initial
//            g.setColor(this.getBackground());
//            g.fillRect(0, 0, WIDTH, HEIGHT);
            return;
        }
        final int h = cells.height();
        final int w = cells.width();
        for (int i = 0, x = updatedX; i < h; i++, x += SQUARE_SIDE_LEN) {
            for (int j = 0, y = updatedY; j < w; j++, y += SQUARE_SIDE_LEN) {
                final Class<? extends Tetromino> type = cells.get(i, j);
                g.setColor(type == null ? this.getBackground() : COLOR_MAP.get(type));
                System.out.println("" + y + " " + x);
                System.out.println("" + this.getSize().width + " " + this.getSize().height);
                g.fillRect(y, x, SQUARE_SIDE_LEN, SQUARE_SIDE_LEN);
            }
        }
    }
}
