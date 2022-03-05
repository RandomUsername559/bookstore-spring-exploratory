package pl.rybak.dawid.springtest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rybak.dawid.springtest.infrastructure.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public BookId save(BookDto bookDto) {
        Book book = bookRepository.save(createBook(bookDto));
        return book.getBookId();
    }

    @Transactional
    public Book findExisting(BookId bookId) {
        return bookRepository.findExisting(bookId);
    }

    @Transactional
    public void delete(BookId bookId) {
        bookRepository.delete(bookId);
    }

    @Transactional
    public void update(BookId bookId, BookDto bookDto) {
        Book book = bookRepository.findExisting(bookId);
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());
        bookRepository.update(book);
    }

    private Book createBook(BookDto bookDto) {
        return new Book(bookDto.getAuthor(), bookDto.getTitle(), createChapters(bookDto.getChapterDtoList()));
    }

    private List<Chapter> createChapters(List<ChapterDto> chapterDtoList) {
        return chapterDtoList.stream()
                .map(dto -> new Chapter(dto.getChapterName(), dto.getPage()))
                .collect(Collectors.toList());
    }
}
