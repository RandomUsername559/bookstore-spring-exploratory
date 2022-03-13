package pl.rybak.dawid.bookstorespringexploratory.publisher.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.rybak.dawid.bookstorespringexploratory.publisher.Publisher;

interface PublisherJpaRepository extends JpaRepository<Publisher, Long> {
}
