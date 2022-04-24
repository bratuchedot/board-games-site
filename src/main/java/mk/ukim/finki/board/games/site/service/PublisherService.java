package mk.ukim.finki.board.games.site.service;

import mk.ukim.finki.board.games.site.model.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PublisherService {

    Publisher addPublisher(Publisher publisher);

    List<Publisher> listAll();

    Page<Publisher> findPaginated(Pageable pageable);

    Publisher findById(Long id);

    Publisher create(String name, String address);

    Publisher update(Long id, String name, String address);

    Publisher delete(Long id);
}
