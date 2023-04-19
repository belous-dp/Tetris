package belous.tetris.game.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Unmodifiable view of backing 2d list.
 *
 * @param <T> type of the stored data
 */
public class Matrix<T> {
    private final List<List<T>> matrix;
    private final int startX;

    /**
     * Constructs unmodifiable matrix from the data.
     *
     * @param data data to be stored
     */
    public Matrix(final List<List<T>> data) {
        this(data, 0);
    }

    /**
     * Constructs unmodifiable matrix from the data.
     *
     * @param data   data to be stored
     * @param startX index of the row to begin view with
     */
    public Matrix(final List<List<T>> data, final int startX) {
        if (startX >= data.size()) {
            throw new IllegalArgumentException("start of the view cannot be greater than data.size() - 1");
        }
        List<List<T>> rows = new ArrayList<>(data.size());
        for (List<T> row : data) {
            rows.add(Collections.unmodifiableList(row));
        }
        this.matrix = Collections.unmodifiableList(rows);
        this.startX = startX;
    }

    /**
     * @see #copyOf(int, int, int, int)
     */
    private Matrix(final Matrix<T> data, int fromX, int fromY, int height, int width) {
        List<List<T>> rows = new ArrayList<>(height);
        for (int i = fromX; i < fromX + height; i++) {
            List<T> row = data.get(i);
            List<T> rowCopy = new ArrayList<>(width);
            for (int j = fromY; j < fromY + width; j++) {
                rowCopy.add(row.get(j));
            }
            rows.add(Collections.unmodifiableList(rowCopy));
        }
        this.matrix = Collections.unmodifiableList(rows);
        this.startX = 0;
    }

    /**
     * Returns element of the matrix.
     *
     * @param x first index (row) of the element
     * @param y second index (column) of the element
     * @return the element at matrix[x][y]
     * @throws IndexOutOfBoundsException if any of indices ({@code x}, {@code y})
     *                                   is out of range (index < 0 || index >= size()) of the corresponding array
     */
    public T get(int x, int y) {
        return matrix.get(x + startX).get(y);
    }

    private List<T> get(int x) {
        return matrix.get(x + startX);
    }

    /**
     * Returns copy of submatrix {@code [fromX : fromX + height, fromY : fromY + height]}.
     *
     * @param fromX  X (row) index to begin copy from, inclusive
     * @param fromY  Y (column) index to begin copy from, inclusive
     * @param height number of rows to include to the result matrix
     * @param width  number of columns to include to the result matrix
     * @return copy of submatrix {@code [fromX : fromX + height, fromY : fromY + height]}
     */
    public Matrix<T> copyOf(int fromX, int fromY, int height, int width) {
        return new Matrix<>(this, fromX, fromY, height, width);
    }

    /**
     * Returns height of the matrix.
     *
     * @return height of the matrix
     */
    public int height() {
        return matrix.size() - startX;
    }

    /**
     * Returns width of the matrix.
     *
     * @return width of the matrix
     */
    public int width() {
        assert (!matrix.isEmpty());
        return matrix.get(0).size();
    }
}
