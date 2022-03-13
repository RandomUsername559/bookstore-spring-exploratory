package pl.rybak.dawid.bookstorespringexploratory.book.infrastructure;

import pl.rybak.dawid.bookstorespringexploratory.book.Book;
import pl.rybak.dawid.bookstorespringexploratory.book.BookId;

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
