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

    @Override
    public byte[][] rotate_clockwise() {
        rotation ^= 1;
        return ROTATIONS[rotation];
    }

    @Override
    public byte[][] rotate_counterclockwise() {
        rotation ^= 1;
        return ROTATIONS[rotation];
    }

    @Override
    public byte[][] get_current() {
        return ROTATIONS[rotation];
    }
}
