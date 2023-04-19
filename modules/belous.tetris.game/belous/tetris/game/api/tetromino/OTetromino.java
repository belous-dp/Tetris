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
        super(x, y, DEFAULT_ROTATION_IDX, DELTA, DELTA, DELTA, DELTA, ROTATIONS);
    }

    public static boolean[][] getDefaultRotation() {
        return ROTATIONS[DEFAULT_ROTATION_IDX];
    }
}
