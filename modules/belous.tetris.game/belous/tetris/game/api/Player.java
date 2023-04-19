package belous.tetris.game.api;

public interface Player {
    Move makeMove(State state);
    void stateUpdated(State state, long score);
}
