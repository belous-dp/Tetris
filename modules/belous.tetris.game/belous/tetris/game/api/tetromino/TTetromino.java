package belous.tetris.game.api.tetromino;

public class TTetromino extends Tetromino {
    private byte rotation = 0;

    static private final byte[][][] ROTATIONS = new byte[][][]{
            {       {0, 0, 0},
                    {1, 1, 1},
                    {0, 1, 0},      },

            {       {0, 1, 0},
                    {0, 1, 1},
                    {0, 1, 0}       },

            {       {0, 1, 0},
                    {1, 1, 1},
                    {0, 0, 0},      },

            {       {0, 1, 0},
                    {1, 1, 0},
                    {0, 1, 0},      },
    };

    @Override
    public byte[][] rotate_clockwise() {
        rotation--;
        rotation &= 0b11;
        System.out.println(rotation);
        return ROTATIONS[rotation];
    }

    @Override
    public byte[][] rotate_counterclockwise() {
        rotation++;
        rotation &= 0b11;
        System.out.println(rotation);
        return ROTATIONS[rotation];
    }

    @Override
    public byte[][] get_current() {
        return ROTATIONS[rotation];
    }
}
