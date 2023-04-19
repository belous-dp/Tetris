package belous.tetris;

import belous.tetris.game.api.PassPlayer;
import belous.tetris.game.api.TetrisGame;

public class Main {
    public static void main(String[] args) {
        TetrisGame tg = new TetrisGame(new PassPlayer());
        try {
            System.out.println("Game result: " + tg.play());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}