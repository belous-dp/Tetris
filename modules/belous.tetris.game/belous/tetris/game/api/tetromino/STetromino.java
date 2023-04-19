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
        super(x, y, DEFAULT_ROTATION_IDX, DELTA_X, DELTA_Y, DELTA_X, DELTA_Y, ROTATIONS);
    }

    public static boolean[][] getDefaultRotation() {
        return ROTATIONS[DEFAULT_ROTATION_IDX];
    }
}
