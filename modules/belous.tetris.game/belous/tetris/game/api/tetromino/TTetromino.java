package belous.tetris.game.api.tetromino;

import belous.tetris.game.impl.Utils;

import java.util.List;

public class TTetromino extends Tetromino {
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
    static private final byte DEFAULT_ROTATION_IDX = 0;

    static private final byte[] DELTA_X_COUNTERCLOCKWISE = {-1, 0, 0, +1};
    static private final byte[] DELTA_Y_COUNTERCLOCKWISE = {+1, -1, 0, 0};
    static private final byte[] DELTA_X_CLOCKWISE = {-1, +1, 0, 0};
    static private final byte[] DELTA_Y_CLOCKWISE = {0, -1, +1, 0};

    public TTetromino(byte x, byte y) {
        super(x, y, DEFAULT_ROTATION_IDX);
    }

    @Override
    protected byte[] getDXClockwise() {
        return DELTA_X_CLOCKWISE;
    }

    @Override
    protected byte[] getDYClockwise() {
        return DELTA_Y_CLOCKWISE;
    }

    @Override
    protected byte[] getDXCounterclockwise() {
        return DELTA_X_COUNTERCLOCKWISE;
    }

    @Override
    protected byte[] getDYCounterclockwise() {
        return DELTA_Y_COUNTERCLOCKWISE;
    }

    @Override
    protected boolean[][][] getRotations() {
        return ROTATIONS;
    }

    public static boolean[][] getDefaultRotation() {
        return ROTATIONS[DEFAULT_ROTATION_IDX];
    }
}
