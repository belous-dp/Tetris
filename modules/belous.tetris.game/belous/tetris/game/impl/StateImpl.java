package belous.tetris.game.impl;

import belous.tetris.game.api.Matrix;
import belous.tetris.game.api.tetromino.Tetromino;

import java.util.ArrayList;
import java.util.List;

public class StateImpl implements EditableState {
    private int score;
    private final List<List<Class<? extends Tetromino>>> layout;
    private final Matrix<Class<? extends Tetromino>> returnedState;

    public StateImpl(int start) {
        this.score = 0;
        layout = new ArrayList<>(height + start);
        for (int i = 0; i < height + start; i++) {
            final ArrayList<Class<? extends Tetromino>> row = new ArrayList<>(width);
            for (int j = 0; j < width; j++) {
                row.add(null);
            }
            layout.add(row);
        }
        returnedState = new Matrix<>(layout, start);
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public Matrix<Class<? extends Tetromino>> getLayout() {
        return returnedState;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public boolean replaceIfCan(byte x, byte y, boolean[][] lastMask, Tetromino next) {
        clear(x, y, lastMask, next.getClass());
        if (isFree(next)) {
            assign(next);
            return true;
        } else {
            // roll back changes
            assign(x, y, lastMask, next.getClass(), null);
            return false;
        }
    }

    private void clear(byte x, byte y, boolean[][] lastMask, Class<? extends Tetromino> type) {
        assign(x, y, lastMask, null, type);
    }

    private void clear(Tetromino tetromino) {
        clear(tetromino.getX(), tetromino.getY(), tetromino.getCurrentRotation(), tetromino.getClass());
    }

    private boolean isFree(byte x, byte y, boolean[][] mask) {
        boolean free = true;
        for (int i = 0; i < mask.length && free; i++) {
            for (int j = 0; j < mask[i].length; j++) {
                if (mask[i][j] && (x + i >= layout.size() ||
                        x + i <= layout.size() && y + j >= layout.get(x + i).size() ||
                        layout.get(x + i).get(y + j) != null)) {
                    free = false;
                    break;
                }
            }
        }
        return free;
    }

    private boolean isFree(Tetromino tetromino) {
        return isFree(tetromino.getX(), tetromino.getY(), tetromino.getCurrentRotation());
    }

    private void assign(byte x, byte y, boolean[][] mask, Class<? extends Tetromino> type,
                        Class<? extends Tetromino> prevType) {
        for (int i = 0; i < mask.length; i++) {
            for (int j = 0; j < mask[i].length; j++) {
                if (mask[i][j]) {
                    assert(x + i < layout.size() && y + j < layout.get(x + i).size() &&
                            layout.get(x + i).get(y + j) == prevType);
                    layout.get(x + i).set(y + j, type);
                }
            }
        }
    }

    private void assign(Tetromino tetromino) {
        assign(tetromino.getX(), tetromino.getY(), tetromino.getCurrentRotation(), tetromino.getClass(), null);
    }

    @Override
    public boolean moveIfCan(MoveDir dir, Tetromino tetromino) {
        clear(tetromino);
        final int dx = dir == MoveDir.DOWN ? 1 : 0;
        final int dy = dir.val();
        tetromino.addX(dx);
        tetromino.addY(dy);
        if (isFree(tetromino)) {
            assign(tetromino);
            return true;
        } else {
            // roll back changes
            tetromino.addX(-dx);
            tetromino.addY(-dy);
            assign(tetromino);
            return false;
        }
    }

    @Override
    public boolean drawIfCan(Tetromino tetromino) {
        if (isFree(tetromino)) {
            assign(tetromino);
            return true;
        } else {
            return false;
        }
    }
}
