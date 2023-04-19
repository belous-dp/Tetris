package belous.tetris.game.impl;

import belous.tetris.game.api.Move;
import belous.tetris.game.api.PassPlayer;
import belous.tetris.game.api.State;
import belous.tetris.game.api.tetromino.Kind;
import belous.tetris.game.api.tetromino.Rotatable;
import belous.tetris.game.api.tetromino.Tetromino;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;


public class Board {
    private final static Kind[] tetrominoKinds = Kind.values();
    private Tetromino active;
    private final EditableState state;
    private final Random rand;
    private final static int START = 2; // todo move to state

    public Board() {
        this.state = new StateImpl(START);
        this.rand = new Random(42);
        if (!introduceNewTetromino()) {
            throw new AssertionError("cannot create new tetromino on empty field");
        }
    }

    public State getState() {
        return state;
    }

    public boolean makeMove(Move move) {
        return switch (move) {
            case LEFT -> state.doIfCan(active, Tetromino::moveLeft, Tetromino::moveRight);
            case RIGHT -> state.doIfCan(active, Tetromino::moveRight, Tetromino::moveLeft);
            case CLOCKWISE -> state.doIfCan(active,
                    Rotatable::rotateClockwise, Rotatable::rotateCounterclockwise);
            case COUNTERCLOCKWISE -> state.doIfCan(active,
                    Rotatable::rotateCounterclockwise, Rotatable::rotateClockwise);
            case DOWN -> state.doIfCan(active, Tetromino::moveDown, Tetromino::moveUp);
            case PASS -> false;
        };
    }

    /**
     * Moves active tetromino one step down.
     * Creates new tetromino if last active tetromino reached bottom of the board.
     * Returns number of burned lines.
     *
     * @return -1 if new tetromino needs to be created, and it cannot be placed on board,
     *         number of burned lines otherwise.
     */
    public int tick() {
        boolean moved = state.doIfCan(active, Tetromino::moveDown, Tetromino::moveUp);
        if (!moved) {
            int burned = burnLines();
            active = null;
            if (burned == 0) {
                boolean ok = introduceNewTetromino();
                return ok ? 0 : -1;
            } else {
                return burned;
            }
        }
        return 0;
    }

    /**
     * Creates new tetromino.
     *
     * @return {@code False} if new tetromino cannot be placed on board, {@code True} otherwise.
     */
    private boolean introduceNewTetromino() {
        Class<? extends Tetromino> clazz = tetrominoKinds[rand.nextInt(1, tetrominoKinds.length)].getClazz();
        try {
            boolean[][] defaultTetrominoMask = (boolean[][]) clazz
                    .getDeclaredMethod("getDefaultRotation")
                    .invoke(null);
            byte w = (byte) defaultTetrominoMask[0].length;
            byte y = (byte) (State.WIDTH / 2 - w / 2);
            Tetromino newTetromino = clazz.getConstructor(byte.class, byte.class).newInstance((byte) START, y);
            boolean introduced = state.drawIfCan(newTetromino);
            if (introduced) {
                active = newTetromino;
                return true;
            }
            return false;
        } catch (IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException | InstantiationException e) {
            throw new AssertionError(e);
        }
    }

    private int burnLines() {
        List<Byte> burned;
        if (active != null) {
            burned = state.getBurnedLines(active.getX(), Tetromino.MAX_SIZE);
        } else {
            burned = state.getBurnedLines(START, State.HEIGHT);
        }
        for (int i = burned.size() - 1; i >= 0; --i) {
            int cur = burned.get(i);
            int last = i > 0 ? burned.get(i - 1) : -1 + START;
            state.moveLinesDown(last + 1, cur - last - 1, burned.size() - i);
        }
        return burned.size();
    }
}
