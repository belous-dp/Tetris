package belous.tetris.game.impl;

public class StateImpl implements EditableState {
    private int score;

    public StateImpl() {
        this.score = 42;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }
}
