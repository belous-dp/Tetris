package belous.tetris.game.api.tetromino;

public class OTetromino extends Tetromino {
    static private final byte[][] ROTATIONS = new byte[][]{
            {1, 1},
            {1, 1}
    };

    public OTetromino(byte x, byte y) {
        super(x, y);
    }

    @Override
    public byte[][] rotateClockwise() {
        return ROTATIONS;
    }

    @Override
    public byte[][] rotateCounterclockwise() {
        return ROTATIONS;
    }

    @Override
    public byte[][] getCurrentRotation() {
        return ROTATIONS;
    }

    public static byte[][] getDefaultRotation() {
        return ROTATIONS;
    }
}
