package belous.tetris.game.impl;

import belous.tetris.game.api.Move;
import belous.tetris.game.api.State;
import belous.tetris.game.api.tetromino.Kind;
import belous.tetris.game.api.tetromino.Tetromino;

import java.util.Random;


public class Board {
    private final static Kind[] tetrominoKinds = Kind.values();
    private Tetromino active;
    private final StateImpl state;
    private final Random rand;

    public Board() {
        this.state = new StateImpl();
        this.rand = new Random(42);
    }

    public State getState() {
        return state;
    }

    public State makeMove(Move move) {
        switch (move) {
            case LEFT -> state.moveIfCan(EditableState.MoveVal.Z, EditableState.MoveVal.N, active);
            case RIGHT -> state.moveIfCan(EditableState.MoveVal.Z, EditableState.MoveVal.P, active);
            case CLOCKWISE -> state.replaceIfCan(active.x, active.y, active.get_current(), active.rotate_clockwise());
            case COUNTERCLOCKWISE ->
                    state.replaceIfCan(active.x, active.y, active.get_current(), active.rotate_counterclockwise());
            case DOWN -> state.moveIfCan(EditableState.MoveVal.P, EditableState.MoveVal.Z, active);
            case PASS -> {
            }
        }
        return state;
    }

    /**
     * Moves active tetromino one step down.
     * Creates new tetromino if last active tetromino reached bottom of the board.
     *
     * @return {@code False} if new tetromino needs to be created, and it cannot be placed on board,
     *         {@code True} otherwise.
     */
    public boolean tick() {
        boolean moved = state.moveIfCan(EditableState.MoveVal.P, EditableState.MoveVal.Z, active);
        if (!moved) {
            return introduceNewTetromino();
        }
        return true;
    }

    /**
     * Creates new tetromino.
     *
     * @return {@code False} if new tetromino cannot be placed on board, {@code True} otherwise.
     */
    private boolean introduceNewTetromino() {
        Kind kind = tetrominoKinds[rand.nextInt(tetrominoKinds.length)];

        return false;
    }
}
