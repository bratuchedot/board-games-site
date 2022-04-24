package mk.ukim.finki.board.games.site.repository;

import mk.ukim.finki.board.games.site.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
