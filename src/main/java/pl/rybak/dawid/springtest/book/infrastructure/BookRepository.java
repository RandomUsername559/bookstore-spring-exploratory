package pl.rybak.dawid.springtest.book.infrastructure;

import pl.rybak.dawid.springtest.book.Book;
import pl.rybak.dawid.springtest.book.BookId;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Optional<Book> find(BookId bookId);

    List<Book> findAll();

    Book save(Book book);

    void delete(BookId bookId);

    void update(Book book);

    Book findExisting(BookId bookId);

}
