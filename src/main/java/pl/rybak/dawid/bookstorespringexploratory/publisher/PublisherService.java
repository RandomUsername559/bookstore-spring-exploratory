package pl.rybak.dawid.bookstorespringexploratory.publisher;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rybak.dawid.bookstorespringexploratory.publisher.infrastructure.PublisherRepository;

import java.util.List;


@Service
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Transactional
    public Long save(PublisherDto publisherDto) {
        Publisher publisher = publisherRepository.save(createPublisher(publisherDto));
        return publisher.getId();
    }

    public Publisher find(Long publisherId) {
        return publisherRepository.findExisting(publisherId);
    }

    public List<Publisher> findALl() {
        return publisherRepository.findAll();
    }

    @Transactional
    public void update(Long publisherId, PublisherDto updatedPublisherDto) {
        Publisher publisher = publisherRepository.findExisting(publisherId);
        publisher.setPublisherName(updatedPublisherDto.getPublisherName());
        publisherRepository.update(publisher);
    }

    @Transactional
    public void delete(Long publisherId) {
        publisherRepository.delete(publisherId);
    }

    private Publisher createPublisher(PublisherDto publisherDtoSet) {
        Publisher publisher = new Publisher(publisherDtoSet.getPublisherName());
        return publisher;
    }

}
