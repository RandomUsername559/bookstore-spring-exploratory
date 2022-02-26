package pl.rybak.dawid.springtest;

import org.springframework.stereotype.Service;
import pl.rybak.dawid.springtest.infrastructure.BookRepository;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findALl() {
//        Book book = bookRepository.findById(444L).orElseThrow();
//        book.setAaname("One Piece");
//        bookRepository.save(book);
//
//        Book testBook = new Book("My Little Pony", "abc");
//        Book testBook2 = new Book("Hentai", "abc");
//        Book testBook3 = new Book("Hentaiii", "abc");
////        Book deleteMe = new Book("delete","delete");
//        bookRepository.save(testBook);
//        bookRepository.save(testBook2);
//        bookRepository.save(testBook3);
//        bookRepository.delete(deleteMe);


        return bookRepository.findAll();
    }

    public BookId save(Author authorName, Title title) {
        Book book = bookRepository.save(new Book(title,authorName));
        return book.getId();
    }

    public Book findExisting(BookId bookId) {
        return bookRepository.find(bookId).orElseThrow();
    }
}
