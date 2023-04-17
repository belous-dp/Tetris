package belous.tetris.game.impl;

import belous.tetris.game.api.Move;

public class StateImpl implements EditableState {
    public static final int height = 20;
    public static final int width = 10;
    private int score;
    private final byte[][] layout;

    public StateImpl() {
        this.score = 0;
        layout = new byte[height][width];
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public byte[][] getLayout() {
        return new byte[0][];
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }
}
