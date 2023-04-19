package belous.tetris.game.api.tetromino;

public interface Rotatable {
    void rotateClockwise();

    void rotateCounterclockwise();

    boolean[][] getCurrentRotation();
}
