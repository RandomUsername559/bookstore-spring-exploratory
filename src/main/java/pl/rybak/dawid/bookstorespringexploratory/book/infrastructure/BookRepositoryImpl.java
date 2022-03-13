package pl.rybak.dawid.bookstorespringexploratory.book.infrastructure;

import org.springframework.stereotype.Repository;
import pl.rybak.dawid.bookstorespringexploratory.book.Book;
import pl.rybak.dawid.bookstorespringexploratory.book.BookId;

import java.util.List;
import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final BookJpaRepository bookJpaRepository;

    public BookRepositoryImpl(BookJpaRepository bookJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
    }

    @Override
    public Optional<Book> find(BookId bookId) {
        Optional<Book> byId = bookJpaRepository.findById(bookId.getAsLong());
        return byId;
    }

    @Override
    public List<Book> findAll() {
        return bookJpaRepository.findAll();
    }

    @Override
    public Book save(Book book) {
        return bookJpaRepository.save(book);
    }

    @Override
    public void delete(BookId bookId) {
        bookJpaRepository.deleteById(bookId.getAsLong());
    }

    @Override
    public void update(Book book) {
        bookJpaRepository.save(book);
    }

    @Override
    public Book findExisting(BookId bookId) {
        return find(bookId).orElseThrow();
    }
}
