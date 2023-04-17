package belous.tetris.game.api.tetromino;

import belous.tetris.game.impl.Utils;

import java.util.List;

public class TTetromino extends Tetromino {
    private byte rotation = 0;

    static private final boolean[][][] ROTATIONS = {
            Utils.strsToBools(List.of(
                    "***",
                    ".*."
            )),
            Utils.strsToBools(List.of(
                    ".*.",
                    ".**",
                    ".*."
            )),
            Utils.strsToBools(List.of(
                    ".*.",
                    "***"
            )),
            Utils.strsToBools(List.of(
                    ".*.",
                    "**.",
                    ".*."
            ))
    };

    public TTetromino(byte x, byte y) {
        super(x, y);
    }

    @Override
    public Tetromino rotateClockwise() {
        rotation--;
        rotation &= 0b11;
        if (rotation == 3) {
            x--; // to preserve middle cell
        } else if (rotation == 0) {
            x++; // to preserve middle cell
        }
        return this;
    }

    @Override
    public Tetromino rotateCounterclockwise() {
        rotation++;
        rotation &= 0b11;
        if (rotation == 1) {
            x--; // to preserve middle cell
        } else if (rotation == 0) {
            x++; // to preserve middle cell
        }
        return this;
    }

    @Override
    public boolean[][] getCurrentRotation() {
        return ROTATIONS[rotation];
    }

    public static boolean[][] getDefaultRotation() {
        return ROTATIONS[0];
    }
}
