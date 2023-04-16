package belous.tetris.game.api;

public class TetrisGame {
    private final Board board;
    private final Player player;

    public TetrisGame(final Player player) {
        this.board = new Board();
        this.player = player;
    }

    public int play() {
        return 0;
    }
}
