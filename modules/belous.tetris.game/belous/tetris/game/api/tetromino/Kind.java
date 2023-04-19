package belous.tetris.game.api.tetromino;

public enum Kind {
    I(ITetromino.class), O(OTetromino.class), T(TTetromino.class), J(JTetromino.class),
    L(LTetromino.class), S(STetromino.class), Z(ZTetromino.class);
    private final Class<? extends Tetromino> clazz;

    Kind(Class<? extends Tetromino> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends Tetromino> getClazz() {
        return clazz;
    }
}
