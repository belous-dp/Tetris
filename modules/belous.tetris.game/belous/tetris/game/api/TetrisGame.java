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
        long speed = 1_000_000_000; // 1 sec
        State state = board.getState();
        long loopStart = System.nanoTime();
        while (true) {
            long responseStart = System.nanoTime();
            Move move = player.makeMove(state);
//            System.out.println("response time=" + (System.nanoTime() - responseStart));
            if (System.nanoTime() - responseStart > 10_000_000) {
                return new GameResult(DefeatType.OUT_OF_TIME, state);
            }
            if (board.makeMove(move)) {
                state = board.getState();
                player.stateUpdated(state);
            }
            if (System.nanoTime() - loopStart >= speed) {
                boolean ok = board.tick();
                if (!ok) {
                    break;
                }
                state = board.getState();
//                player.stateUpdated(state); // todo remove
                loopStart = System.nanoTime();
            }
        }
        return new GameResult(DefeatType.OUT_OF_SPACE, state);
    }
}
