package belous.tetris.game.api;

import belous.tetris.game.api.tetromino.Kind;

public class PassPlayer implements Player {
    private void printState(State state) {
        final Kind[][] layout = state.getLayout();
        for (Kind[] kinds : layout) {
            for (Kind kind : kinds) {
                System.out.print(kind == Kind.E ? '.' : kind);
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
