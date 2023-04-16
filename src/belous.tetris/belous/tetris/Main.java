package belous.tetris;

import belous.tetris.game.api.HumanPlayer;
import belous.tetris.game.api.TetrisGame;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        TetrisGame tg = new TetrisGame(new HumanPlayer());
        System.out.println(tg.play());
    }
}