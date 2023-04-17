package belous.tetris.game.api.tetromino;

public class ITetromino extends Tetromino {
    private byte rotation = 0;

    static private final byte[][][] ROTATIONS = new byte[][][]{
            {       {0, 1, 0},
                    {0, 1, 0},
                    {0, 1, 0},
                    {0, 1, 0}       },
            {       {0, 0, 0},
                    {1, 1, 1},
                    {0, 0, 0}       }
    };

    public ITetromino(byte x, byte y) {
        super(x, y);
    }

    @Override
    public byte[][] rotateClockwise() {
        rotation ^= 1;
        return ROTATIONS[rotation];
    }

    @Override
    public byte[][] rotateCounterclockwise() {
        rotation ^= 1;
        return ROTATIONS[rotation];
    }

    @Override
    public byte[][] getCurrentRotation() {
        return ROTATIONS[rotation];
    }

    public static byte[][] getDefaultRotation() {
        return ROTATIONS[0];
    }
}
