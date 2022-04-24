package mk.ukim.finki.board.games.site.web;

import mk.ukim.finki.board.games.site.model.Favourites;
import mk.ukim.finki.board.games.site.service.FavouritesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/favourites")
public class FavouritesController {
    private final FavouritesService favouritesService;

    public FavouritesController(FavouritesService favouritesService) {
        this.favouritesService = favouritesService;
    }

    @GetMapping
    public String getFavouritesPage(@RequestParam(required = false) String error, HttpServletRequest request, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
//        User user = (User) request.getSession().getAttribute("user");
        String username = request.getRemoteUser();
        Favourites favourites = this.favouritesService.getFavourites(username);
        model.addAttribute("games", this.favouritesService.listAllGamesInFavourites(favourites.getId()));
        return "favourites";
    }

    @PostMapping("/{id}/add")
    public String addGameToFavourites(@PathVariable Long id, HttpServletRequest request) {
        try {
//            User user = (User) request.getSession().getAttribute("user");
            String username = request.getRemoteUser();
            Favourites favourites = this.favouritesService.addGameToFavourites(username, id);
            return "redirect:/favourites";
        } catch (RuntimeException exception) {
            return "redirect:/games?error=" + exception.getMessage();
        }
    }

    @PostMapping("/{id}/remove")
    public String removeGameFromFavourites(@PathVariable Long id, HttpServletRequest request) {
        try {
//            User user = (User) request.getSession().getAttribute("user");
            String username = request.getRemoteUser();
            Favourites favourites = this.favouritesService.removeGameFromFavourites(username, id);
            return "redirect:/favourites";
        } catch (RuntimeException exception) {
            return "redirect:/games?error=" + exception.getMessage();
        }
    }
}
