package mk.ukim.finki.board.games.site.web;

import mk.ukim.finki.board.games.site.model.Category;
import mk.ukim.finki.board.games.site.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getCategoriesPage(@RequestParam("page") Optional<Integer> page,
                                    @RequestParam("size") Optional<Integer> size,
                                    Model model) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(4);

        Page<Category> categoryPage = this.categoryService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("categoryPage", categoryPage);

        int totalPages = categoryPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

//        List<Category> categories = this.categoryService.listAll();
//        model.addAttribute("categories", categories);
        return "categories";
    }

    @GetMapping("/add")
    public String getAddCategoryPage(Model model) {
        List<Category> categories = this.categoryService.listAll();
        model.addAttribute("categories", categories);
        return "category-form";
    }

    @GetMapping("/{id}/edit")
    public String getEditCategoryPage(@PathVariable Long id, Model model) {
        if (this.categoryService.findById(id) != null) {
            Category category = this.categoryService.findById(id);
            model.addAttribute("category", category);
            return "category-form";
        }
        return "redirect:/categories?error=CategoryNotFoundException";
    }

    @PostMapping
    public String addCategory(@RequestParam String name, @RequestParam String description) {
        this.categoryService.create(name, description);
        return "redirect:/categories";
    }

    @PostMapping("/{id}")
    public String editCategory(@PathVariable Long id, @RequestParam String name, @RequestParam String description) {
        this.categoryService.update(id, name, description);
        return "redirect:/categories";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteCategory(@PathVariable Long id) {
        this.categoryService.delete(id);
        return "redirect:/categories";
    }
}

