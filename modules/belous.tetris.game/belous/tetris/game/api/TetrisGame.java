package belous.tetris.game.api;

import belous.tetris.game.impl.Board;

import static java.lang.Thread.sleep;

public class TetrisGame {
    private final Board board;
    private final Player player;

    public TetrisGame(final Player player) {
        this.board = new Board();
        this.player = player;
    }

    public GameResult play() throws InterruptedException {
        long speed = 1_000_000_000; // 1 sec
        final long MAX_RESPONSE_TIME = 10_000_000; // 10 ms
        final long DELAY_TIME = 10; // 10 ms
        State state = board.getState();
        long loopStart = System.nanoTime();
        player.stateUpdated(state);
        while (true) {
            long responseStart = System.nanoTime();
            Move move = player.makeMove(state);
//            System.out.println("response time=" + (System.nanoTime() - responseStart));
            if (System.nanoTime() - responseStart > MAX_RESPONSE_TIME) {
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
                player.stateUpdated(state);
                loopStart = System.nanoTime();
            }
            sleep(DELAY_TIME); // NOTE: busy waiting. maybe move player to separate thread?
        }
        return new GameResult(DefeatType.OUT_OF_SPACE, state);
    }
}
