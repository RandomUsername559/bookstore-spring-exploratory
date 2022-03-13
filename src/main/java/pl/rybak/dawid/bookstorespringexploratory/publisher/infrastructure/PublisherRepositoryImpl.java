package pl.rybak.dawid.bookstorespringexploratory.publisher.infrastructure;

import org.springframework.stereotype.Repository;
import pl.rybak.dawid.bookstorespringexploratory.publisher.Publisher;

import java.util.List;
import java.util.Optional;

@Repository
public class PublisherRepositoryImpl implements PublisherRepository {

    private final PublisherJpaRepository publisherJpaRepository;

    public PublisherRepositoryImpl(PublisherJpaRepository publisherJpaRepository) {
        this.publisherJpaRepository = publisherJpaRepository;
    }

    @Override
    public Publisher save(Publisher publisher) {
        return publisherJpaRepository.save(publisher);
    }

    @Override
    public Optional<Publisher> find(Long publisherId) {
        Optional<Publisher> byId = publisherJpaRepository.findById(publisherId);
        return byId;
    }

    @Override
    public Publisher findExisting(Long publisherId) {
        return find(publisherId).orElseThrow();
    }

    @Override
    public List<Publisher> findAll() {
        return publisherJpaRepository.findAll();
    }

    @Override
    public void update(Publisher publisher) {
        publisherJpaRepository.save(publisher);
    }

    @Override
    public void delete(Long publisherId) {
        publisherJpaRepository.deleteById(publisherId);
    }
}
