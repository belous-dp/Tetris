package belous.tetris.game.api;

public class OutOfTimeException extends RuntimeException {
    public OutOfTimeException(String message) {
        super(message);
    }
}
