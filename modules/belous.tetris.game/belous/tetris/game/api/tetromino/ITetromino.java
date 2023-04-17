package belous.tetris.game.api.tetromino;

import belous.tetris.game.impl.Utils;

import java.util.List;

public class ITetromino extends Tetromino {
    private byte rotation = 0;

    static private final boolean[][][] ROTATIONS = {
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

    public ITetromino(byte x, byte y) {
        super(x, y);
    }

    @Override
    public Tetromino rotateClockwise() {
        return rotate();
    }

    private ITetromino rotate() {
        if (rotation == 0) {
            x -= 2;
            y += 2;
        } else {
            x += 2;
            y -= 2;
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
