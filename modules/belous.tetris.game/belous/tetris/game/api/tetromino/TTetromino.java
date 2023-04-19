package belous.tetris.game.api.tetromino;

import belous.tetris.game.impl.Utils;

import java.util.List;

public class TTetromino extends Tetromino {
    private byte rotation = 0;

    static private final boolean[][][] ROTATIONS = {
            Utils.strsToBools(List.of(
                    "***",
                    ".*."
            )),
            Utils.strsToBools(List.of(
                    "*.",
                    "**",
                    "*."
            )),
            Utils.strsToBools(List.of(
                    ".*.",
                    "***"
            )),
            Utils.strsToBools(List.of(
                    ".*",
                    "**",
                    ".*"
            ))
    };

    static private final byte[] DELTA_X_COUNTERCLOCKWISE = {-1, 0, 0, +1};
    static private final byte[] DELTA_Y_COUNTERCLOCKWISE = {+1, -1, 0, 0};
    static private final byte[] DELTA_X_CLOCKWISE = {-1, +1, 0, 0};
    static private final byte[] DELTA_Y_CLOCKWISE = {0, -1, +1, 0};

    public TTetromino(byte x, byte y) {
        super(x, y);
    }

    @Override
    public Tetromino rotateClockwise() {
        x += DELTA_X_CLOCKWISE[rotation];
        y += DELTA_Y_CLOCKWISE[rotation];
        rotation--;
        rotation &= 0b11;
        return this;
    }

    @Override
    public Tetromino rotateCounterclockwise() {
        x += DELTA_X_COUNTERCLOCKWISE[rotation];
        y += DELTA_Y_COUNTERCLOCKWISE[rotation];
        rotation++;
        rotation &= 0b11;
        return this;
    }

    @Override
    public boolean[][] getCurrentRotation() {
        return ROTATIONS[rotation];
    }

    public static boolean[][] getDefaultRotation() {
        return ROTATIONS[0];
    }
}
