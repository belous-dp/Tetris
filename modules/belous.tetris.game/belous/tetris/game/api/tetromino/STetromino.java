package belous.tetris.game.api.tetromino;

import belous.tetris.game.impl.Utils;

import java.util.List;

public class STetromino extends Tetromino {
    static private final boolean[][][] ROTATIONS = {
            Utils.strsToBools(List.of(
                    ".**",
                    "**."
            )),
            Utils.strsToBools(List.of(
                    "*.",
                    "**",
                    ".*"
            )),
            Utils.strsToBools(List.of(
                    ".**",
                    "**."
            )),
            Utils.strsToBools(List.of(
                    "*.",
                    "**",
                    ".*"
            ))
    };
    static private final byte DEFAULT_ROTATION_IDX = 0;

    static private final byte[] DELTA_X = {-1, +1, -1, +1};
    static private final byte[] DELTA_Y = {+1, -1, +1, -1};

    public STetromino(byte x, byte y) {
        super(x, y, DEFAULT_ROTATION_IDX);
    }

    @Override
    protected byte[] getDXClockwise() {
        return DELTA_X;
    }

    @Override
    protected byte[] getDYClockwise() {
        return DELTA_Y;
    }

    @Override
    protected byte[] getDXCounterclockwise() {
        return DELTA_X;
    }

    @Override
    protected byte[] getDYCounterclockwise() {
        return DELTA_Y;
    }

    @Override
    protected boolean[][][] getRotations() {
        return ROTATIONS;
    }

    public static boolean[][] getDefaultRotation() {
        return ROTATIONS[DEFAULT_ROTATION_IDX];
    }
}
