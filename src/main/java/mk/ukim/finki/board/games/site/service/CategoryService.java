package mk.ukim.finki.board.games.site.service;

import mk.ukim.finki.board.games.site.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    Category addCategory(Category category);

    List<Category> listAll();

    Page<Category> findPaginated(Pageable pageable);

    Category findById(Long id);

    Category create(String name, String description);

    Category update(Long id, String name, String description);

    Category delete(Long id);
}
