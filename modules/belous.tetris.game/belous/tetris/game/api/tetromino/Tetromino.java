package belous.tetris.game.api.tetromino;

public abstract class Tetromino implements Rotatable {
    public static final int MAX_SIZE = 4;

    private byte x, y;
    private byte rotation;

    protected Tetromino(byte x, byte y, byte rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    public byte getX() {
        return x;
    }

    public byte getY() {
        return y;
    }

    public void moveLeft() {
        y--;
    }

    public void moveRight() {
        y++;
    }

    public void moveUp() {
        x--;
    }

    public void moveDown() {
        x++;
    }

    @Override
    public Tetromino rotateClockwise() {
        x += getDXClockwise()[rotation];
        y += getDYClockwise()[rotation];
        rotation--;
        rotation &= 0b11;
        return this;
    }

    protected abstract byte[] getDXClockwise();
    protected abstract byte[] getDYClockwise();

    @Override
    public Tetromino rotateCounterclockwise() {
        x += getDXCounterclockwise()[rotation];
        y += getDYCounterclockwise()[rotation];
        rotation++;
        rotation &= 0b11;
        return this;
    }

    protected abstract byte[] getDXCounterclockwise();
    protected abstract byte[] getDYCounterclockwise();

    @Override
    public boolean[][] getCurrentRotation() {
        return getRotations()[rotation];
    }

    protected abstract boolean[][][] getRotations();
}
