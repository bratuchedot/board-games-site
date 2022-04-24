package mk.ukim.finki.board.games.site.model.exceptions;

public class InvalidArgumentsException extends RuntimeException {
    public InvalidArgumentsException() {
        super("Runtime exception was thrown. Invalid arguments.");
    }
}
