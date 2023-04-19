package belous.tetris.game.api.tetromino;

public abstract class Tetromino implements Rotatable {
    public static final int MAX_SIZE = 4;

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
}
