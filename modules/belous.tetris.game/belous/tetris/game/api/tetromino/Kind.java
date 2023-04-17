package belous.tetris.game.api.tetromino;

public enum Kind {
    I(ITetromino.class), O(OTetromino.class), T(TTetromino.class), J(clazz), L(clazz), S(clazz), Z(clazz), E(clazz);
    private final Class<?> clazz;

    Kind(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
