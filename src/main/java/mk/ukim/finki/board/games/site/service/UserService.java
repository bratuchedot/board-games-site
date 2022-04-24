package mk.ukim.finki.board.games.site.service;

import mk.ukim.finki.board.games.site.model.User;
import mk.ukim.finki.board.games.site.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User addUser(User user);

    User register(String username, String password, String repeatPassword, String name, String surname, String email, String phone, String avatarUrl, Role role);

}
