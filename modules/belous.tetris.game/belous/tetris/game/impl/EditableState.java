package belous.tetris.game.impl;

import belous.tetris.game.api.State;
import belous.tetris.game.api.tetromino.Tetromino;

public interface EditableState extends State {
    void setScore(int score);

    void replaceIfCan(byte x, byte y, boolean[][] lastMask, Tetromino next);

    enum MoveVal {
        P(1), N(-1), Z(0);
        private final int val;
        MoveVal(int val) {
            this.val = val;
        }
        int val() {
            return val;
        }
    }
    boolean moveIfCan(MoveVal x, MoveVal y, Tetromino tetromino);

    boolean drawIfCan(Tetromino tetromino);
}
