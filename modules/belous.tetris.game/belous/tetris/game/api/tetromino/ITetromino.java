package belous.tetris.game.api.tetromino;

import belous.tetris.game.impl.Utils;

import java.util.List;

public class ITetromino extends Tetromino {
    static private final boolean[][][] ROTATIONS = {
            Utils.strsToBools(List.of(
                    "****"
            )),
            Utils.strsToBools(List.of(
                    "*",
                    "*",
                    "*",
                    "*"
            )),
            Utils.strsToBools(List.of(
                    "****"
            )),
            Utils.strsToBools(List.of(
                    "*",
                    "*",
                    "*",
                    "*"
            ))
    };
    static private final byte DEFAULT_ROTATION_IDX = 0;

    static private final byte[] DELTA_X = {-2, +2, -2, +2};
    static private final byte[] DELTA_Y = {+2, -2, +2, -2};

    public ITetromino(byte x, byte y) {
        super(x, y, DEFAULT_ROTATION_IDX, DELTA_X, DELTA_Y, DELTA_X, DELTA_Y, ROTATIONS);
    }

    public static boolean[][] getDefaultRotation() {
        return ROTATIONS[DEFAULT_ROTATION_IDX];
    }
}
