package belous.tetris.ui;

import belous.tetris.game.api.Matrix;
import belous.tetris.game.api.Move;
import belous.tetris.game.api.QueuePlayer;
import belous.tetris.game.api.State;
import belous.tetris.game.api.tetromino.Tetromino;

import java.util.concurrent.BlockingQueue;

public class UIPlayer extends QueuePlayer {
    private final GamePanel gamePanel;
    private final InfoPanel infoPanel;

    UIPlayer(BlockingQueue<Move> moves, GamePanel gamePanel, InfoPanel infoPanel) {
        super(moves);
        this.gamePanel = gamePanel;
        this.infoPanel = infoPanel;
    }

    @Override
    public void stateUpdated(State state, int score) {
        final int x = state.getLastUpdatedPosX();
        final int y = state.getLastUpdatedPosY();
        final int h = state.getLastUpdatedHeight();
        final int w = state.getLastUpdatedWidth();
        final Matrix<Class<? extends Tetromino>> updated = state.getLayout().copyOf(x, y, h, w);
        final int px = GamePanel.indexToPixel(x);
        final int py = GamePanel.indexToPixel(y);
        final int ph = GamePanel.indexToPixel(h);
        final int pw = GamePanel.indexToPixel(w);
        javax.swing.SwingUtilities.invokeLater(() -> {
            gamePanel.updateState(px, py, updated);
            gamePanel.repaint(py, px, pw, ph); // inverted cuz different coordinate systems
            infoPanel.updateScore(score);
        });
    }
}
