package belous.tetris.game.api.tetromino;

public interface Rotatable {
    byte[][] rotateClockwise();

    byte[][] rotateCounterclockwise();

    byte[][] getCurrentRotation();
}
