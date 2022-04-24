package mk.ukim.finki.board.games.site.model.exceptions;

public class InvalidUserCredentialsException extends RuntimeException {
    public InvalidUserCredentialsException() {
        super("Runtime exception was thrown. Invalid user credentials.");
    }
}
