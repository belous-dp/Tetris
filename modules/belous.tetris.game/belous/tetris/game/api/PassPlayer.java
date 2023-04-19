package belous.tetris.game.api;

import belous.tetris.game.api.tetromino.*;

import java.util.HashMap;
import java.util.Map;

public class PassPlayer implements Player {
    private int step;

    private final Map<Class<? extends Tetromino>, Character> tetrominoToChar;

    public PassPlayer() {
        step = 0;
        tetrominoToChar = new HashMap<>();
        final Kind[] tetrominoKinds = Kind.values();
        for (Kind tetrominoKind : tetrominoKinds) {
            tetrominoToChar.put(tetrominoKind.getClazz(), tetrominoKind.name().charAt(0));
        }
    }

    private void printState(State state, int score) {
        System.out.println("===== step " + step + " =====");
        System.out.println("      current score: " + score);
        final Matrix<Class<? extends Tetromino>> layout = state.getLayout();
        for (int i = 0; i < State.HEIGHT; i++) {
            for (int j = 0; j < State.WIDTH; j++) {
                final Class<? extends Tetromino> type = layout.get(i, j);
                System.out.print(type == null ? '.' : tetrominoToChar.get(type));
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public Move makeMove(State state) {
        return Move.PASS;
    }

    @Override
    public void stateUpdated(State state, int score) {
        step++;
        printState(state, score);
    }
}
