package pl.rybak.dawid.springtest.book;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.rybak.dawid.springtest.book.infrastructure.BookRepository;
import pl.rybak.dawid.springtest.publisher.Publisher;
import pl.rybak.dawid.springtest.publisher.PublisherDto;
import pl.rybak.dawid.springtest.publisher.infrastructure.PublisherRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BookService(BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    public List<Book> findALl() {
        return bookRepository.findAll();
    }

    @Transactional
    public BookId save(BookDto bookDto) {
        Book book = bookRepository.save(createBook(bookDto));
        return book.getBookId();
    }


    public Book find(BookId bookId) {
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

    @Transactional
    public void addPublisher(Long publisherId, BookId bookId) {
        Book book = bookRepository.findExisting(bookId);
        Publisher publisher = publisherRepository.findExisting(publisherId);
        book.addPublisher(publisher);
    }

    private Book createBook(BookDto bookDto) {
        Book book = new Book(bookDto.getAuthor(), bookDto.getTitle(),
                createChapters(bookDto.getChapterDtoSet())
        );
        return book;
    }

    private Set<Chapter> createChapters(Set<ChapterDto> chapterDtoSet) {
        return chapterDtoSet.stream()
                .map(dto -> new Chapter(dto.getChapterName(), dto.getPage()))
                .collect(Collectors.toSet());
    }

    private Publisher createPublisher(PublisherDto publisherDto) {
        Publisher publisher = new Publisher(publisherDto.getPublisherName()
        );
        return publisher;
    }
}
