package mk.ukim.finki.board.games.site.web;

import mk.ukim.finki.board.games.site.model.enumerations.Role;
import mk.ukim.finki.board.games.site.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error,
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
        return "reg-auth";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String email,
                           @RequestParam String phone,
                           @RequestParam String avatarUrl,
                           @RequestParam Role role) {
        try {
            this.userService.register(username, password, repeatPassword, name, surname, email, phone, avatarUrl, role);
            return "redirect:/register?success=You have successfully created a new account. Login below.";
        } catch (RuntimeException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}
