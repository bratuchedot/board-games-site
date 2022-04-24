package mk.ukim.finki.board.games.site.service.impl;

import mk.ukim.finki.board.games.site.model.Publisher;
import mk.ukim.finki.board.games.site.model.exceptions.PublisherNotFoundException;
import mk.ukim.finki.board.games.site.repository.PublisherRepository;
import mk.ukim.finki.board.games.site.service.PublisherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Publisher addPublisher(Publisher publisher) {
        return this.publisherRepository.save(publisher);
    }

    @Override
    public List<Publisher> listAll() {
        return this.publisherRepository.findAll();
    }

    @Override
    public Page<Publisher> findPaginated(Pageable pageable) {
        List<Publisher> publishers = this.listAll();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Publisher> list;

        if (publishers.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, publishers.size());
            list = publishers.subList(startItem, toIndex);
        }

        Page<Publisher> publisherPage = new PageImpl<Publisher>(list, PageRequest.of(currentPage, pageSize), publishers.size());
        return publisherPage;
    }

    @Override
    public Publisher findById(Long id) {
        return this.publisherRepository.findById(id)
                .orElseThrow(() -> new PublisherNotFoundException(id));
    }

    @Override
    public Publisher create(String name, String address) {
        Publisher publisher = new Publisher(name, address);
        return this.publisherRepository.save(publisher);
    }

    @Override
    public Publisher update(Long id, String name, String address) {
        Publisher publisher = this.findById(id);
        publisher.setName(name);
        publisher.setAddress(address);
        return this.publisherRepository.save(publisher);
    }

    @Override
    public Publisher delete(Long id) {
        Publisher publisher = this.findById(id);
        this.publisherRepository.delete(publisher);
        return publisher;
    }
}
