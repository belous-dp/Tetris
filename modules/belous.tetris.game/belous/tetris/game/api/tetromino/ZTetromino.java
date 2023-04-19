package belous.tetris.game.api.tetromino;

import belous.tetris.game.impl.Utils;

import java.util.List;

public class ZTetromino extends Tetromino {
    private byte rotation = 0;

    static private final boolean[][][] ROTATIONS = {
            Utils.strsToBools(List.of(
                    "**.",
                    ".**"
            )),
            Utils.strsToBools(List.of(
                    ".*",
                    "**",
                    "*."
            ))
    };

    public ZTetromino(byte x, byte y) {
        super(x, y);
    }

    @Override
    public Tetromino rotateClockwise() {
        return rotate();
    }

    private ZTetromino rotate() {
        if (rotation == 0) {
            x--;
            y++;
        } else {
            x++;
            y--;
        }
        rotation ^= 1;
        return this;
    }

    @Override
    public Tetromino rotateCounterclockwise() {
        return rotate();
    }

    @Override
    public boolean[][] getCurrentRotation() {
        return ROTATIONS[rotation];
    }

    public static boolean[][] getDefaultRotation() {
        return ROTATIONS[0];
    }
}
