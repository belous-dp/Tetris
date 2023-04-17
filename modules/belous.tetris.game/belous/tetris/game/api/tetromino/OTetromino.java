package belous.tetris.game.api.tetromino;

import belous.tetris.game.impl.Utils;

import java.util.List;

public class OTetromino extends Tetromino {
    static private final boolean[][] ROTATIONS =
            Utils.strsToBools(List.of(
                    "**",
                    "**"
            ));

    public OTetromino(byte x, byte y) {
        super(x, y);
    }

    @Override
    public Tetromino rotateClockwise() {
        return this;
    }

    @Override
    public Tetromino rotateCounterclockwise() {
        return this;
    }

    @Override
    public boolean[][] getCurrentRotation() {
        return ROTATIONS;
    }

    public static boolean[][] getDefaultRotation() {
        return ROTATIONS;
    }
}
