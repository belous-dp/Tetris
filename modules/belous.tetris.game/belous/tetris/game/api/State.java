package belous.tetris.game.api;

import belous.tetris.game.api.tetromino.Tetromino;

public interface State {
    byte height = 20;
    byte width = 10;
    int getScore();

    Matrix<Class<? extends Tetromino>> getLayout();
}
