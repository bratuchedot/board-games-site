package mk.ukim.finki.board.games.site.service;

import mk.ukim.finki.board.games.site.model.Favourites;
import mk.ukim.finki.board.games.site.model.Game;

import java.util.List;

public interface FavouritesService {

    List<Game> listAllGamesInFavourites(Long id);

    Favourites getFavourites(String username);

    Favourites addGameToFavourites(String username, Long gameId);

    Favourites removeGameFromFavourites(String username, Long gameId);
}
