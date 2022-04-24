package mk.ukim.finki.board.games.site.web;

import mk.ukim.finki.board.games.site.model.Category;
import mk.ukim.finki.board.games.site.model.Game;
import mk.ukim.finki.board.games.site.model.Publisher;
import mk.ukim.finki.board.games.site.service.CategoryService;
import mk.ukim.finki.board.games.site.service.GameService;
import mk.ukim.finki.board.games.site.service.PublisherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;
    private final CategoryService categoryService;
    private final PublisherService publisherService;

    public GameController(GameService gameService, CategoryService categoryService, PublisherService publisherService) {
        this.gameService = gameService;
        this.categoryService = categoryService;
        this.publisherService = publisherService;
    }

    @GetMapping
    public String getGamesPage(@RequestParam(required = false) String error,
                               @RequestParam(required = false) String searchWord,
                               @RequestParam(required = false) Long categoryId,
                               Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Category> categories = this.categoryService.listAll();
        List<Game> games;
        if (searchWord == null && categoryId == null) {
            games = this.gameService.listAll();
        } else {
            games = this.gameService.search(searchWord, categoryId);
        }
        model.addAttribute("games", games);
        model.addAttribute("categories", categories);
        return "games";
    }

    @GetMapping("/add")
    public String getAddGamePage(Model model) {
        List<Category> categories = this.categoryService.listAll();
        List<Publisher> publishers = this.publisherService.listAll();
        model.addAttribute("categories", categories);
        model.addAttribute("publishers", publishers);
        return "game-form";
    }

    @GetMapping("/{id}/edit")
    public String getEditGamePage(@PathVariable Long id, Model model) {
        if (this.gameService.findById(id) != null) {
            Game game = this.gameService.findById(id);
            List<Category> categories = this.categoryService.listAll();
            List<Publisher> publishers = this.publisherService.listAll();
            model.addAttribute("game", game);
            model.addAttribute("categories", categories);
            model.addAttribute("publishers", publishers);
            return "game-form";
        }
        return "redirect:/games?error=GameNotFoundException";
    }

    @PostMapping
    public String addGame(@RequestParam(required = false) MultipartFile photo,
                          @RequestParam String name,
                          @RequestParam String shortDescription,
                          @RequestParam String description,
                          @RequestParam Integer numberOfPlayers,
                          @RequestParam Integer playingTimeInMinutes,
                          @RequestParam Integer ageRating,
                          @RequestParam String releaseDate,
                          @RequestParam List<Long> categoriesIds,
                          @RequestParam Long publisherId) throws IOException {
        if (photo == null || photo.isEmpty()) {
            this.gameService.create(name, shortDescription, description, numberOfPlayers, playingTimeInMinutes, ageRating, LocalDate.parse(releaseDate), categoriesIds, publisherId);
        } else {
            this.gameService.create(photo, name, shortDescription, description, numberOfPlayers, playingTimeInMinutes, ageRating, LocalDate.parse(releaseDate), categoriesIds, publisherId);
        }
        return "redirect:/games";
    }

    @PostMapping("/{id}")
    public String editGame(@PathVariable Long id,
                           @RequestParam(required = false) MultipartFile photo,
                           @RequestParam String name,
                           @RequestParam String shortDescription,
                           @RequestParam String description,
                           @RequestParam Integer numberOfPlayers,
                           @RequestParam Integer playingTimeInMinutes,
                           @RequestParam Integer ageRating,
                           @RequestParam String releaseDate,
                           @RequestParam List<Long> categoriesIds,
                           @RequestParam Long publisherId) throws IOException {
        if (photo == null || photo.isEmpty()) {
            this.gameService.update(id, name, shortDescription, description, numberOfPlayers, playingTimeInMinutes, ageRating, LocalDate.parse(releaseDate), categoriesIds, publisherId);
        } else {
            this.gameService.update(id, photo, name, shortDescription, description, numberOfPlayers, playingTimeInMinutes, ageRating, LocalDate.parse(releaseDate), categoriesIds, publisherId);
        }
        return "redirect:/games";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteGame(@PathVariable Long id) {
        this.gameService.delete(id);
        return "redirect:/games";
    }

    @PostMapping("/{id}/like")
    public String markDone(@PathVariable Long id) {
        this.gameService.like(id);
        return "redirect:/games";
    }

}
