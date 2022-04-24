package mk.ukim.finki.board.games.site.repository;

import mk.ukim.finki.board.games.site.model.Favourites;
import mk.ukim.finki.board.games.site.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavouritesRepository extends JpaRepository<Favourites, Long> {
    Optional<Favourites> findByUser(User user);
}
