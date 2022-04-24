package mk.ukim.finki.board.games.site.service;

import mk.ukim.finki.board.games.site.model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface GameService {

    Game addGame(Game game);

    List<Game> listAll();

    Page<Game> findPaginated(Pageable pageable);

    Game findById(Long id);

    Game create(String name, String shortDescription, String description, Integer numberOfPlayers, Integer playingTimeInMinutes, Integer ageRating, LocalDate releaseDate, List<Long> categoriesIds, Long publisherId);

    Game create(MultipartFile photo, String name, String shortDescription, String description, Integer numberOfPlayers, Integer playingTimeInMinutes, Integer ageRating, LocalDate releaseDate, List<Long> categoriesIds, Long publisherId) throws IOException;

    Game update(Long id, String name, String shortDescription, String description, Integer numberOfPlayers, Integer playingTimeInMinutes, Integer ageRating, LocalDate releaseDate, List<Long> categoriesIds, Long publisherId);

    Game update(Long id, MultipartFile photo, String name, String shortDescription, String description, Integer numberOfPlayers, Integer playingTimeInMinutes, Integer ageRating, LocalDate releaseDate, List<Long> categoriesIds, Long publisherId) throws IOException;

    Game delete(Long id);

    Game like(Long id);

    List<Game> search(String searchWord, Long categoryId);
}
