package belous.tetris.game.impl;

import belous.tetris.game.api.State;
import belous.tetris.game.api.tetromino.Tetromino;

import java.util.function.Consumer;

public interface EditableState extends State {
    void setScore(int score);

    boolean doIfCan(Tetromino tetromino, Consumer<Tetromino> modify, Consumer<Tetromino> rollback);

    boolean drawIfCan(Tetromino tetromino);
}
