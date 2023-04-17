package belous.tetris.game.api;

import belous.tetris.game.api.tetromino.Tetromino;

public interface State {
    int getScore();

    byte[][] getLayout();
}
