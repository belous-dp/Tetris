package belous.tetris.game.impl;

import belous.tetris.game.api.tetromino.Kind;
import belous.tetris.game.api.tetromino.Tetromino;

public class StateImpl implements EditableState {
    private int score;
    private final Kind[][] layout;

    public StateImpl() {
        this.score = 0;
        layout = new Kind[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                layout[i][j] = Kind.E;
            }
        }
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public Kind[][] getLayout() {
        return null;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void replaceIfCan(byte x, byte y, byte[][] lastMask, byte[][] newMask) {

    }

    @Override
    public boolean moveIfCan(MoveVal x, MoveVal y, Tetromino tetromino) {
        return false;
    }

    @Override
    public boolean drawIfCan(Tetromino tetromino) {
        return false;
    }
}
