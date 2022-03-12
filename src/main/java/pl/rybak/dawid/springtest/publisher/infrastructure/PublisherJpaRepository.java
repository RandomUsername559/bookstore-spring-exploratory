package pl.rybak.dawid.springtest.publisher.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.rybak.dawid.springtest.publisher.Publisher;

interface PublisherJpaRepository extends JpaRepository<Publisher, Long> {
}
