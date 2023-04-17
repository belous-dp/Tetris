package belous.tetris.game.api.tetromino;

public interface Rotatable {
    Tetromino rotateClockwise();

    Tetromino rotateCounterclockwise();

    boolean[][] getCurrentRotation();
}
