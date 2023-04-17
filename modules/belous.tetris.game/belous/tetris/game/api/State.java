package belous.tetris.game.api;

import belous.tetris.game.api.tetromino.Kind;

public interface State {
    byte height = 20;
    byte width = 10;
    int getScore();

    Kind[][] getLayout(); // todo unmodifiable view
}
