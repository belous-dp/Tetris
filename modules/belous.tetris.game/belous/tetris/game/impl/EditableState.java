package belous.tetris.game.impl;

import belous.tetris.game.api.State;
import belous.tetris.game.api.tetromino.Tetromino;

public interface EditableState extends State {
    void setScore(int score);

    boolean replaceIfCan(byte x, byte y, boolean[][] lastMask, Tetromino next);

    enum MoveDir {
        LEFT(-1), RIGHT(1), DOWN(0);
        private final int val;
        MoveDir(int val) {
            this.val = val;
        }
        int val() {
            return val;
        }
    }
    boolean moveIfCan(MoveDir dir, Tetromino tetromino);

    boolean drawIfCan(Tetromino tetromino);
}
