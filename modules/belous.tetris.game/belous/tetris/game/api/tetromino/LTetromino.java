package belous.tetris.game.api.tetromino;

import belous.tetris.game.impl.Utils;

import java.util.List;

public class LTetromino extends Tetromino {
    static private final boolean[][][] ROTATIONS = {
            Utils.strsToBools(List.of(
                    "*.",
                    "*.",
                    "**"
            )),
            Utils.strsToBools(List.of(
                    "..*",
                    "***"
            )),
            Utils.strsToBools(List.of(
                    "**",
                    ".*",
                    ".*"
            )),
            Utils.strsToBools(List.of(
                    "***",
                    "*.."
            ))
    };
    static private final byte DEFAULT_ROTATION_IDX = 3;

    static private final byte[] DELTA_X_COUNTERCLOCKWISE = {0, 0, +1, -1};
    static private final byte[] DELTA_Y_COUNTERCLOCKWISE = {-1, 0, 0, +1};
    static private final byte[] DELTA_X_CLOCKWISE = {+1, 0, 0, -1};
    static private final byte[] DELTA_Y_CLOCKWISE = {-1, +1, 0, 0};

    public LTetromino(byte x, byte y) {
        super(x, y, DEFAULT_ROTATION_IDX, DELTA_X_CLOCKWISE, DELTA_Y_CLOCKWISE,
                DELTA_X_COUNTERCLOCKWISE, DELTA_Y_COUNTERCLOCKWISE, ROTATIONS);
    }

    public static boolean[][] getDefaultRotation() {
        return ROTATIONS[DEFAULT_ROTATION_IDX];
    }
}
