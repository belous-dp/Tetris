package belous.tetris;

import belous.tetris.game.api.State;
//import belous.tetris.game.api.tetromino.TTetromino;
//import belous.tetris.game.impl.EditableState;

import belous.tetris.game.api.Move;
import belous.tetris.game.api.Player;

/**
 * Cheating player: tries to change state of the game. Uncomment to check whether cheating succeeds.
 */
public class CheatingPlayer implements Player {

    @Override
    public Move makeMove(State state) {
//        EditableState hehe = (EditableState) state;
//        hehe.drawIfCan(new TTetromino((byte) 3, (byte) 4));
//        System.out.println("cheating succeeded");
        return null;
    }

    @Override
    public void stateUpdated(State state, int score) {
    }
}
