package belous.tetris.game.api;

import belous.tetris.game.impl.StateImpl;

public class Board {
    private StateImpl state;

    public State getState() {
        return state;
    }

    public int makeMove(Move move) {
        return 0;
    }
}
