package mk.ukim.finki.board.games.site.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException{
    public PasswordsDoNotMatchException() {
        super("Passwords do not match. Try again.");
    }
}
