package belous.tetris;

import belous.tetris.game.api.HumanPlayer;
import belous.tetris.game.api.TetrisGame;

public class Main {
    public static void main(String[] args) {
        TetrisGame tg = new TetrisGame(new HumanPlayer());
        System.out.println("your score: " + tg.play());
    }
}