package belous.tetris.game.api.tetromino;

public enum Kind {
    I(ITetromino.class), O(OTetromino.class), T(TTetromino.class), J(JTetromino.class);//, L(clazz), S(clazz), Z(clazz), E(clazz);
    private final Class<? extends Tetromino> clazz;

    Kind(Class<? extends Tetromino> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends Tetromino> getClazz() {
        return clazz;
    }
}
