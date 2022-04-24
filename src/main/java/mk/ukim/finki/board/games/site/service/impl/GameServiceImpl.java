package mk.ukim.finki.board.games.site.service.impl;

import mk.ukim.finki.board.games.site.model.Category;
import mk.ukim.finki.board.games.site.model.Game;
import mk.ukim.finki.board.games.site.model.Publisher;
import mk.ukim.finki.board.games.site.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.board.games.site.model.exceptions.GameNotFoundException;
import mk.ukim.finki.board.games.site.model.exceptions.PublisherNotFoundException;
import mk.ukim.finki.board.games.site.repository.CategoryRepository;
import mk.ukim.finki.board.games.site.repository.GameRepository;
import mk.ukim.finki.board.games.site.repository.PublisherRepository;
import mk.ukim.finki.board.games.site.service.GameService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final CategoryRepository categoryRepository;
    private final PublisherRepository publisherRepository;

    public GameServiceImpl(GameRepository gameRepository, CategoryRepository categoryRepository, PublisherRepository publisherRepository) {
        this.gameRepository = gameRepository;
        this.categoryRepository = categoryRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Game addGame(Game game) {
        return this.gameRepository.save(game);
    }

    @Override
    public List<Game> listAll() {
        return this.gameRepository.findAll();
    }

    @Override
    public Page<Game> findPaginated(Pageable pageable) {
        List<Game> games = this.listAll();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Game> list;

        if (games.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, games.size());
            list = games.subList(startItem, toIndex);
        }

        Page<Game> gamePage = new PageImpl<Game>(list, PageRequest.of(currentPage, pageSize), games.size());
        return gamePage;
    }

    @Override
    public Game findById(Long id) {
        return this.gameRepository.findById(id)
                .orElseThrow(() -> new GameNotFoundException(id));
    }


    @Override
    public Game create(String name, String shortDescription, String description, Integer numberOfPlayers, Integer playingTimeInMinutes, Integer ageRating, LocalDate releaseDate, List<Long> categoriesIds, Long publisherId) {
        List<Category> categories = this.categoryRepository.findAllById(categoriesIds);
        Publisher publisher = this.publisherRepository.findById(publisherId)
                .orElseThrow(() -> new PublisherNotFoundException(publisherId));
        Game game = new Game(name, shortDescription, description, numberOfPlayers, playingTimeInMinutes, ageRating, releaseDate, categories, publisher);
        return this.gameRepository.save(game);
    }

    @Override
    public Game update(Long id, String name, String shortDescription, String description, Integer numberOfPlayers, Integer playingTimeInMinutes, Integer ageRating, LocalDate releaseDate, List<Long> categoriesIds, Long publisherId) {
        Game game = this.findById(id);
        List<Category> categories = this.categoryRepository.findAllById(categoriesIds);
        Publisher publisher = this.publisherRepository.findById(publisherId)
                .orElseThrow(() -> new PublisherNotFoundException(publisherId));
        game.setName(name);
        game.setShortDescription(shortDescription);
        game.setDescription(description);
        game.setNumberOfPlayers(numberOfPlayers);
        game.setPlayingTimeInMinutes(playingTimeInMinutes);
        game.setAgeRating(ageRating);
        game.setReleaseDate(releaseDate);
        game.setCategories(categories);
        game.setPublisher(publisher);
        return this.gameRepository.save(game);
    }

    @Override
    public Game delete(Long id) {
        Game game = this.findById(id);
        this.gameRepository.delete(game);
        return game;
    }

    @Override
    public Game like(Long id) {
        Game game = this.findById(id);
        Integer incrementLikes = game.getLikes() + 1;
        game.setLikes(incrementLikes);
        return this.gameRepository.save(game);
    }

    @Override
    public List<Game> search(String searchWord, Long categoryId) {
        if (searchWord == null && categoryId == null) {
            return this.gameRepository.findAll();
        } else if (categoryId == null) {
            return this.gameRepository.findAll().stream()
                    .filter(game -> game.getName().contains(searchWord) ||
                            game.getShortDescription().contains(searchWord))
                    .collect(Collectors.toList());
        } else if (searchWord == null) {
            Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
            return this.gameRepository.findByCategories(category);
        } else {
            Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
            return this.gameRepository.findByCategories(category).stream()
                    .filter(game -> game.getName().contains(searchWord) ||
                            game.getShortDescription().contains(searchWord))
                    .collect(Collectors.toList());
        }
    }
}
