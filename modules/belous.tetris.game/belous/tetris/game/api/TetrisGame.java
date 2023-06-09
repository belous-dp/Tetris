package belous.tetris.game.api;

import belous.tetris.game.impl.Board;

import static java.lang.Thread.sleep;

public class TetrisGame {
    private final Board board;
    private final Player player;
    private static final long MAX_RESPONSE_TIME = 10_000_000; // 10 ms
    private static final long DELAY_TIME = 9; // 10 ms
    private static final int[] SCORING = {0, 40, 100, 300, 1200};

    public TetrisGame(final Player player) {
        this.board = new Board();
        this.player = player;
    }

    public long play() throws InterruptedException {
        long speed = 500_000_000; // 0.5 sec
        int score = 0;
        State state = board.getState();
        long loopStart = System.nanoTime();
        player.stateUpdated(state, score);
        while (true) {
            long responseStart = System.nanoTime();
            Move move = player.makeMove(state);
            if (System.nanoTime() - responseStart > MAX_RESPONSE_TIME) {
                throw new OutOfTimeException("player response took too long");
            }
            if (board.makeMove(move)) {
                state = board.getState();
                player.stateUpdated(state, score);
            }
            if (System.nanoTime() - loopStart >= speed) {
                int burned = board.tick();
                if (burned == -1) {
                    break;
                }
                score += SCORING[burned];
                state = board.getState();
                player.stateUpdated(state, score);
                loopStart = System.nanoTime();
            }
            sleep(DELAY_TIME); // NOTE: busy waiting. maybe move player to separate thread?
        }
        return score;
    }
}
