package mk.ukim.finki.board.games.site.service.impl;

import mk.ukim.finki.board.games.site.model.Category;
import mk.ukim.finki.board.games.site.model.Publisher;
import mk.ukim.finki.board.games.site.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.board.games.site.repository.CategoryRepository;
import mk.ukim.finki.board.games.site.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category addCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public List<Category> listAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Page<Category> findPaginated(Pageable pageable) {
        List<Category> categories = this.listAll();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Category> list;

        if (categories.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, categories.size());
            list = categories.subList(startItem, toIndex);
        }

        Page<Category> publisherPage = new PageImpl<Category>(list, PageRequest.of(currentPage, pageSize), categories.size());
        return publisherPage;
    }

    @Override
    public Category findById(Long id) {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Override
    public Category create(String name, String description) {
        Category category = new Category(name, description);
        return this.categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, String name, String description) {
        Category category = this.findById(id);
        category.setName(name);
        category.setDescription(description);
        return this.categoryRepository.save(category);
    }

    @Override
    public Category delete(Long id) {
        Category category = this.findById(id);
        this.categoryRepository.delete(category);
        return category;
    }
}
