package belous.tetris.game.impl;

import belous.tetris.game.api.Matrix;
import belous.tetris.game.api.State;
import belous.tetris.game.api.tetromino.Tetromino;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static java.lang.Math.max;
import static java.lang.Math.min;

@SuppressWarnings("SuspiciousNameCombination")
public class StateImpl implements EditableState {
    private final int START;
    private final List<List<Class<? extends Tetromino>>> layout;
    private final Matrix<Class<? extends Tetromino>> returnedState;
    private int updatedX1, updatedY1, updatedX2, updatedY2;


    public StateImpl(int start) {
        layout = new ArrayList<>(HEIGHT + start);
        for (int i = 0; i < HEIGHT + start; i++) {
            final ArrayList<Class<? extends Tetromino>> row = new ArrayList<>(WIDTH);
            for (int j = 0; j < WIDTH; j++) {
                row.add(null);
            }
            layout.add(row);
        }
        returnedState = new Matrix<>(layout, start);
        updatedX1 = updatedY1 = 0;
        updatedX2 = State.HEIGHT;
        updatedY2 = State.WIDTH;
        this.START = start;
    }

    @Override
    public Matrix<Class<? extends Tetromino>> getLayout() {
        return returnedState;
    }

    @Override
    public int getLastUpdatedPosX() {
        return updatedX1 - START;
    }

    @Override
    public int getLastUpdatedPosY() {
        return updatedY1;
    }

    @Override
    public int getLastUpdatedHeight() {
        return updatedX2 - updatedX1;
    }

    @Override
    public int getLastUpdatedWidth() {
        return updatedY2 - updatedY1;
    }

    private void clearUpdated() {
        updatedX1 = updatedY1 = Byte.MAX_VALUE;
        updatedX2 = updatedY2 = Byte.MIN_VALUE;
    }

    private void clear(Tetromino tetromino) {
        clear(tetromino.getX(), tetromino.getY(), tetromino.getCurrentRotation(), tetromino.getClass());
    }

    @Override
    public boolean doIfCan(Tetromino tetromino, Consumer<Tetromino> modify,
                           Consumer<Tetromino> rollback) {
        if (tetromino == null) {
            return false;
        }
        clearUpdated();
        clear(tetromino);
        modify.accept(tetromino);
        if (isFree(tetromino)) {
            assign(tetromino);
            return true;
        } else {
            rollback.accept(tetromino);
            assign(tetromino);
            return false;
        }
    }

    @Override
    public boolean drawIfCan(Tetromino tetromino) {
        if (isFree(tetromino)) {
//            clearUpdated();
            assign(tetromino);
            return true;
        } else {
            return false;
        }
    }

    private void clear(byte x, byte y, boolean[][] lastMask, Class<? extends Tetromino> type) {
        assign(x, y, lastMask, null, type);
    }

    private boolean isFree(Tetromino tetromino) {
        return isFree(tetromino.getX(), tetromino.getY(), tetromino.getCurrentRotation());
    }

    private boolean isFree(byte x, byte y, boolean[][] mask) {
        boolean free = true;
        for (int i = 0; i < mask.length && free; i++) {
            for (int j = 0; j < mask[i].length; j++) {
                if (mask[i][j] && (x + i < 0 || y + j < 0 || x + i >= layout.size() ||
                        x + i <= layout.size() && y + j >= layout.get(x + i).size() ||
                        layout.get(x + i).get(y + j) != null)) {
                    free = false;
                    break;
                }
            }
        }
        return free;
    }

    private void assign(Tetromino tetromino) {
        assign(tetromino.getX(), tetromino.getY(), tetromino.getCurrentRotation(), tetromino.getClass(), null);
    }

    private void assign(byte x, byte y, boolean[][] mask, Class<? extends Tetromino> type,
                        Class<? extends Tetromino> prevType) {
        updatedX1 = min(updatedX1, x);
        updatedY1 = min(updatedY1, y);
        updatedX2 = max(updatedX2, x + mask.length);
        updatedY2 = max(updatedY2, y + mask[0].length);

        for (int i = 0; i < mask.length; i++) {
            for (int j = 0; j < mask[i].length; j++) {
                if (mask[i][j]) {
                    assert (x + i >= 0 && x + i < layout.size() &&
                            y + j >= 0 && y + j < layout.get(x + i).size() &&
                            layout.get(x + i).get(y + j) == prevType);
                    layout.get(x + i).set(y + j, type);
                }
            }
        }
    }

    @Override
    public List<Byte> getBurnedLines(int start, int length) {
        List<Byte> res = new ArrayList<>();
        for (int i = start; i < start + length && i < layout.size(); i++) {
            boolean full = true;
            for (var c : layout.get(i)) {
                if (c == null) {
                    full = false;
                    break;
                }
            }
            if (full) {
                res.add((byte) i);
            }
        }
        return res;
    }

    @Override
    public void moveLinesDown(int start, int count, int step) {
        updatedX1 = start;
        updatedX2 = start + count + step;
        updatedY1 = 0;
        updatedY2 = State.WIDTH;
        for (int i = start + count - 1; i >= start; i--) {
            List<Class<? extends Tetromino>> cur = layout.get(i);
            List<Class<? extends Tetromino>> to = layout.get(i + step);
            for (int j = 0; j < State.WIDTH; j++) {
                to.set(j, cur.get(j));
                cur.set(j, null);
            }
        }
    }
}
