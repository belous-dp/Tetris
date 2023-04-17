package belous.tetris.game.api;

import belous.tetris.game.api.tetromino.*;

import java.util.Map;

public class PassPlayer implements Player {
    private static final Map<Class<? extends Tetromino>, Character> tetrominoToChar = Map.of(
            ITetromino.class, 'I', OTetromino.class, 'O', TTetromino.class, 'T'
    ); // todo maybe take from tetromino.Kinds?

    private void printState(State state) {
        final Matrix<Class<? extends Tetromino>> layout = state.getLayout();
        for (int i = 0; i < State.height; i++) {
            for (int j = 0; j < State.width; j++) {
                final Class<? extends Tetromino> type = layout.get(i, j);
                System.out.print(type == null ? '.' : tetrominoToChar.get(type));
            }
            System.out.println();
        }
    }

    @Override
    public Move makeMove(State state) {
        return Move.PASS;
    }

    @Override
    public void stateUpdated(State state) {

    }
}
