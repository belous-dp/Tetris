package belous.tetris.game.api;

import java.util.concurrent.BlockingQueue;

public abstract class QueuePlayer implements Player {
    private final BlockingQueue<Move> moves;

    protected QueuePlayer(BlockingQueue<Move> moves) {
        this.moves = moves;
    }

    @Override
    public Move makeMove(State state) {
        Move move = moves.poll();
        return move == null ? Move.PASS : move;
    }
}
