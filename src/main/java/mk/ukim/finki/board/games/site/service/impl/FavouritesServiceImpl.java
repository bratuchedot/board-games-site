package mk.ukim.finki.board.games.site.service.impl;

import mk.ukim.finki.board.games.site.model.Favourites;
import mk.ukim.finki.board.games.site.model.Game;
import mk.ukim.finki.board.games.site.model.User;
import mk.ukim.finki.board.games.site.model.exceptions.FavouritesNotFoundException;
import mk.ukim.finki.board.games.site.model.exceptions.GameAlreadyInFavouritesException;
import mk.ukim.finki.board.games.site.model.exceptions.GameNotFoundException;
import mk.ukim.finki.board.games.site.model.exceptions.UserNotFoundException;
import mk.ukim.finki.board.games.site.repository.FavouritesRepository;
import mk.ukim.finki.board.games.site.repository.GameRepository;
import mk.ukim.finki.board.games.site.repository.UserRepository;
import mk.ukim.finki.board.games.site.service.FavouritesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavouritesServiceImpl implements FavouritesService {
    private final FavouritesRepository favouritesRepository;
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    public FavouritesServiceImpl(FavouritesRepository favouritesRepository, UserRepository userRepository, GameRepository gameRepository) {
        this.favouritesRepository = favouritesRepository;
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> listAllGamesInFavourites(Long id) {
        if (this.favouritesRepository.findById(id).isEmpty())
            throw new FavouritesNotFoundException(id);
        return this.favouritesRepository.findById(id).get().getGames();
    }

    @Override
    public Favourites getFavourites(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        return this.favouritesRepository.findByUser(user)
                .orElseGet(() -> {
                    Favourites favourites = new Favourites(user);
                    return this.favouritesRepository.save(favourites);
                });
    }

    @Override
    public Favourites addGameToFavourites(String username, Long gameId) {
        Favourites favourites = getFavourites(username);
        Game game = this.gameRepository.findById(gameId)
                .orElseThrow(()-> new GameNotFoundException(gameId));
        if (favourites.getGames().stream().anyMatch(g -> g.getId().equals(gameId)))
            throw new GameAlreadyInFavouritesException(game.getName(), username);
        favourites.getGames().add(game);
        return this.favouritesRepository.save(favourites);
    }

    @Override
    public Favourites removeGameFromFavourites(String username, Long gameId) {
        Favourites favourites = getFavourites(username);
        Game game = this.gameRepository.findById(gameId)
                .orElseThrow(()-> new GameNotFoundException(gameId));
        if (favourites.getGames().stream().anyMatch(g -> !g.getId().equals(gameId)))
            throw new GameNotFoundException(gameId);
        favourites.getGames().remove(game);
        return this.favouritesRepository.save(favourites);
    }
}
