package mk.ukim.finki.board.games.site.web;

import mk.ukim.finki.board.games.site.model.Publisher;
import mk.ukim.finki.board.games.site.service.PublisherService;
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
@RequestMapping("/publishers")
public class PublisherController {
    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping
    public String getPublisherPage(@RequestParam("page") Optional<Integer> page,
                                   @RequestParam("size") Optional<Integer> size,
                                   Model model) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Publisher> publisherPage = this.publisherService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("publisherPage", publisherPage);

        int totalPages = publisherPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

//        List<Publisher> publishers = this.publisherService.listAll();
//        model.addAttribute("publishers", publishers);
        return "publishers";
    }

    @GetMapping("/add")
    public String getAddPublisherPage(Model model) {
        List<Publisher> publishers = this.publisherService.listAll();
        model.addAttribute("publishers", publishers);
        return "publisher-form";
    }

    @GetMapping("/{id}/edit")
    public String getEditPublisherPage(@PathVariable Long id, Model model) {
        if (this.publisherService.findById(id) != null) {
            Publisher publisher = this.publisherService.findById(id);
            model.addAttribute("publisher", publisher);
            return "publisher-form";
        }
        return "redirect:/publishers?error=PublisherNotFoundException";
    }

    @PostMapping
    public String addPublisher(@RequestParam String name, @RequestParam String address) {
        this.publisherService.create(name, address);
        return "redirect:/publishers";
    }

    @PostMapping("/{id}")
    public String editPublisher(@PathVariable Long id, @RequestParam String name, @RequestParam String address) {
        this.publisherService.update(id, name, address);
        return "redirect:/publishers";
    }

    @DeleteMapping("/{id}/delete")
    public String deletePublisher(@PathVariable Long id) {
        this.publisherService.delete(id);
        return "redirect:/publishers";
    }
}

