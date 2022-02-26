package pl.rybak.dawid.springtest.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.rybak.dawid.springtest.Book;

interface BookJpaRepository extends JpaRepository<Book, Long> {
}

