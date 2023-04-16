package belous.tetris;

import belous.tetris.game.api.State;
//import belous.tetris.game.impl.EditableState;

import belous.tetris.game.api.Move;
import belous.tetris.game.api.Player;

/**
 * Cheating player: tries to change state of the game. Uncomment to check whether cheating succeeds.
 */
public class CheatingPlayer implements Player {

    @Override
    public Move makeMove(State state) {
        System.out.println("init score=" + state.getScore());
//        EditableState hehe = (EditableState) state;
//        hehe.setScore(1337);
        if (state.getScore() == 1337) {
            System.out.println("cheating succeeded");
        }
        return null;
    }
}
