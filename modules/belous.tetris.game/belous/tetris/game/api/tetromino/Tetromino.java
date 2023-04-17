package belous.tetris.game.api.tetromino;

public abstract class Tetromino implements Rotatable {
    protected byte x, y;

    public Tetromino(byte x, byte y) {
        this.x = x;
        this.y = y;
    }

    public byte getX() {
        return x;
    }

    public byte getY() {
        return y;
    }

}
