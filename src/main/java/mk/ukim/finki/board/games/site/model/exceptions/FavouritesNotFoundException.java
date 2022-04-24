package mk.ukim.finki.board.games.site.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class FavouritesNotFoundException extends RuntimeException {
    public FavouritesNotFoundException(Long id) {
        super(String.format("Favourites folder with id %d was not found", id));
    }
}
