package belous.tetris.game.api.tetromino;

public abstract class Tetromino implements Rotatable {
    public static final int MAX_SIZE = 4;

    private byte x, y;
    private byte rotation;

    private final byte[] dxClockwise;
    private final byte[] dyClockwise;
    private final byte[] dxCounterclockwise;
    private final byte[] dyCounterclockwise;
    private final boolean[][][] rotations;

    protected Tetromino(byte x, byte y, byte rotation, byte[] dxClockwise, byte[] dyClockwise,
                        byte[] dxCounterclockwise, byte[] dyCounterclockwise, boolean[][][] rotations) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.dxClockwise = dxClockwise;
        this.dyClockwise = dyClockwise;
        this.dxCounterclockwise = dxCounterclockwise;
        this.dyCounterclockwise = dyCounterclockwise;
        this.rotations = rotations;
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
        x += dxClockwise[rotation];
        y += dyClockwise[rotation];
        rotation--;
        rotation &= 0b11;
        return this;
    }

    @Override
    public Tetromino rotateCounterclockwise() {
        x += dxCounterclockwise[rotation];
        y += dyCounterclockwise[rotation];
        rotation++;
        rotation &= 0b11;
        return this;
    }

    @Override
    public boolean[][] getCurrentRotation() {
        return rotations[rotation];
    }
}
