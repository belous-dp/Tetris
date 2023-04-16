package belous.tetris;

import belous.tetris.game.TetrisGame;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        TetrisGame tg = new TetrisGame();
        System.out.println(tg.play());
    }
}