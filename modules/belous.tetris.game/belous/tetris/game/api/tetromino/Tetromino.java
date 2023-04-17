package belous.tetris.game.api.tetromino;

public abstract class Tetromino {
    public byte x, y;

    public abstract byte[][] rotate_clockwise();

    public abstract byte[][] rotate_counterclockwise();

    public abstract byte[][] get_current();

}
