package belous.tetris.game.impl;

import belous.tetris.game.api.State;

public interface EditableState extends State {
    void setScore(int score);
}
