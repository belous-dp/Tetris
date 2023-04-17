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
     * @param data data to be stored
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
}
