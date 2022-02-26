package pl.rybak.dawid.springtest.infrastructure;

import pl.rybak.dawid.springtest.Book;
import pl.rybak.dawid.springtest.BookId;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Optional<Book> find(BookId bookId);

    List<Book> findAll();

    Book save(Book book);
}
