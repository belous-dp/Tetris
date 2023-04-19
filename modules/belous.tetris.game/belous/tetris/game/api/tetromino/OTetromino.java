package belous.tetris.game.api.tetromino;

import belous.tetris.game.impl.Utils;

import java.util.List;

public class OTetromino extends Tetromino {
    static private final boolean[][][] ROTATIONS = {
            Utils.strsToBools(List.of(
                    "**",
                    "**"
            )),
            Utils.strsToBools(List.of(
                    "**",
                    "**"
            )),
            Utils.strsToBools(List.of(
                    "**",
                    "**"
            )),
            Utils.strsToBools(List.of(
                    "**",
                    "**"
            ))
    };
    static private final byte DEFAULT_ROTATION_IDX = 0;

    static private final byte[] DELTA = {0, 0, 0, 0};

    public OTetromino(byte x, byte y) {
        super(x, y, DEFAULT_ROTATION_IDX);
    }

    @Override
    protected byte[] getDXClockwise() {
        return DELTA;
    }

    @Override
    protected byte[] getDYClockwise() {
        return DELTA;
    }

    @Override
    protected byte[] getDXCounterclockwise() {
        return DELTA;
    }

    @Override
    protected byte[] getDYCounterclockwise() {
        return DELTA;
    }

    @Override
    protected boolean[][][] getRotations() {
        return ROTATIONS;
    }

    public static boolean[][] getDefaultRotation() {
        return ROTATIONS[DEFAULT_ROTATION_IDX];
    }
}
