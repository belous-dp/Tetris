package belous.tetris.game.impl;

import belous.tetris.game.api.State;
import belous.tetris.game.api.tetromino.Tetromino;

import java.util.List;
import java.util.function.Consumer;

public interface EditableState extends State {
    boolean doIfCan(Tetromino tetromino, Consumer<Tetromino> modify, Consumer<Tetromino> rollback);

    boolean drawIfCan(Tetromino tetromino);

    List<Byte> getBurnedLines(int start, int length);

    void moveLinesDown(int start, int count, int step);
}
