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

    /**
     * Constructs unmodifiable matrix from the data.
     *
     * @param data data to be stored
     */
    public Matrix(final List<List<T>> data) {
        List<List<T>> rows = new ArrayList<>(data.size());
        for (List<T> row : data) {
            rows.add(Collections.unmodifiableList(row));
        }
        matrix = Collections.unmodifiableList(rows);
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
        return matrix.get(x).get(y);
    }
}
