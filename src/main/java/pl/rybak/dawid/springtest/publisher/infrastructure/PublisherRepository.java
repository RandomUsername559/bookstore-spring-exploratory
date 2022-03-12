package pl.rybak.dawid.springtest.publisher.infrastructure;

import pl.rybak.dawid.springtest.publisher.Publisher;

import java.util.List;
import java.util.Optional;


public interface PublisherRepository {
    Publisher save(Publisher publisher);

    Optional<Publisher> find(Long publisherId);

    Publisher findExisting(Long publisherId);

    List<Publisher> findAll();

    void update(Publisher publisher);

    void delete(Long publisherId);

}
