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

    public TTetromino(byte x, byte y) {
        super(x, y);
    }

    @Override
    public byte[][] rotateClockwise() {
        rotation--;
        rotation &= 0b11;
        System.out.println(rotation);
        return ROTATIONS[rotation];
    }

    @Override
    public byte[][] rotateCounterclockwise() {
        rotation++;
        rotation &= 0b11;
        System.out.println(rotation);
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
