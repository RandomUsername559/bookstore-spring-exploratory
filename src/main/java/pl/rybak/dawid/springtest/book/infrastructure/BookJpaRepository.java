package pl.rybak.dawid.springtest.book.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.rybak.dawid.springtest.book.Book;

interface BookJpaRepository extends JpaRepository<Book, Long> {
}

