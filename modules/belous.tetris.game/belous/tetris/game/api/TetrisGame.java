package belous.tetris.game.api;

import belous.tetris.game.impl.Board;

public class TetrisGame {
    private final Board board;
    private final Player player;

    public TetrisGame(final Player player) {
        this.board = new Board();
        this.player = player;
    }

    public GameResult play() {
        long speed = 1000_000; // ns
        board.tick();
        State state = board.getState();
        long loopStart = System.nanoTime();
        while (true) {
            long responseStart = System.nanoTime();
            Move move = player.makeMove(state);
            if (System.nanoTime() - responseStart > 1000) {
                return new GameResult(DefeatType.OUT_OF_TIME, state);
            }
            state = board.makeMove(move);
            player.stateUpdated(state);
            if (System.nanoTime() - loopStart >= speed) {
                boolean ok = board.tick();
                if (!ok) {
                    break;
                }
                state = board.getState();
                loopStart = System.nanoTime();
            }
        }
        return new GameResult(DefeatType.OUT_OF_SPACE, state);
    }
}
