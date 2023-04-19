package belous.tetris.game.api;

import belous.tetris.game.api.tetromino.Tetromino;

public interface State {
    byte HEIGHT = 20;
    byte WIDTH = 10;
    int getScore();

    Matrix<Class<? extends Tetromino>> getLayout();

    int getLastUpdatedPosX();
    int getLastUpdatedPosY();
    int getLastUpdatedHeight();
    int getLastUpdatedWidth();
}
