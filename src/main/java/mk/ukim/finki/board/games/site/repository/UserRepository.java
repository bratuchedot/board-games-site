package mk.ukim.finki.board.games.site.repository;

import mk.ukim.finki.board.games.site.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndAndPassword(String username, String password);

    Optional<User> findByUsername(String username);
}
