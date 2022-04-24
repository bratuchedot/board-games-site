package mk.ukim.finki.board.games.site.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class GameAlreadyInFavouritesException extends RuntimeException {
    public GameAlreadyInFavouritesException(String gameName, String username) {
        super(String.format("Game \"%s\" already exists in favourites folder for user \"%s\"", gameName, username));
    }
}
