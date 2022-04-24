package mk.ukim.finki.board.games.site.web;

import mk.ukim.finki.board.games.site.model.enumerations.Role;
import mk.ukim.finki.board.games.site.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getProfilePage(@RequestParam(required = false) String error,
                                 @RequestParam(required = false) String success,
                                 Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        if (success != null && !success.isEmpty()) {
            model.addAttribute("isSuccessful", true);
            model.addAttribute("success", success);
        }
        return "profile";
    }

    @GetMapping("/user/{id}/edit")
    public String getEditUserPage(@PathVariable Long id) {
        if (this.userService.findById(id) != null) {
            return "user-form";
        }
        return "redirect:/profile?error=UserNotFoundException";
    }

    @PostMapping("/user/{id}")
    public String editUser(@PathVariable Long id,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String email,
                           @RequestParam String phone,
                           @RequestParam String avatarUrl,
                           @RequestParam Role role) {
        this.userService.edit(id, name, surname, email, phone, avatarUrl, role);
        return "redirect:/profile?success=You have successfully edited your account. The changes will be visible after you re-login.";
    }
}
