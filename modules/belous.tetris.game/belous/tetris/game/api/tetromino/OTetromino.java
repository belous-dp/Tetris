package belous.tetris.game.api.tetromino;

public class OTetromino extends Tetromino {
    static private final byte[][] ROTATIONS = new byte[][]{
            {1, 1},
            {1, 1}
    };

    @Override
    public byte[][] rotate_clockwise() {
        return ROTATIONS;
    }

    @Override
    public byte[][] rotate_counterclockwise() {
        return ROTATIONS;
    }

    @Override
    public byte[][] get_current() {
        return ROTATIONS;
    }
}
