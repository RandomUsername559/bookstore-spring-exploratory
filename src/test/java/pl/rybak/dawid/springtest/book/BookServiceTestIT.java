package pl.rybak.dawid.springtest.book;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import pl.rybak.dawid.springtest.publisher.Publisher;
import pl.rybak.dawid.springtest.publisher.PublisherDto;
import pl.rybak.dawid.springtest.publisher.PublisherService;

import java.util.List;
import java.util.Set;

@SpringBootTest
class BookServiceTestIT {
    @Autowired
    private BookService bookService;
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void cleanDatabaseEach() {


        List<String> tables = jdbcTemplate.queryForList(
                "SELECT table_name FROM information_schema.tables\n" +
                        "WHERE table_schema = 'public';", String.class);
        for (String table : tables) {
            jdbcTemplate.execute("truncate table " + table + " cascade");
        }

    }


    @Test
    void shouldIncreaseSizeOfTotalBooksByAddingBook() {

        // GIVEN
        BookDto bookDto = new BookDto(Author.of("Oda"), Title.of("One Piece"), Set.of());

        //WHEN
        bookService.save(bookDto);

        // THEN
        Assertions.assertThat(bookService.findALl()).hasSize(1);
    }

    @Test
    void shouldSaveAllFieldsOfBook() {

        // GIVEN
        BookDto bookDto = new BookDto(Author.of("Oda"), Title.of("One Piece"), Set.of());

        //WHEN
        BookId bookId = bookService.save(bookDto);

        // THEN
        Book actualBook = bookService.find(bookId);
        Book expectedBookDto = new Book(Author.of("Oda"), Title.of("One Piece"), Set.of());
        Assertions.assertThat(actualBook.getBookId()).isNotNull();
        assertAreEqual(actualBook, expectedBookDto);
    }

    @Test
    void shouldFindWantedElementInDatabase() {

        //GIVEN
        BookDto bookDto = new BookDto(Author.of("Kentarou"), Title.of("Berserk"), Set.of());
        BookId bookId = bookService.save(bookDto);

        //WHEN
        Book actualBook = bookService.find(bookId);

        //THEN
        Book expectedBook = new Book(Author.of("Kentarou"), Title.of("Berserk"), Set.of());
        assertAreEqual(actualBook, expectedBook);

    }

    @Test
    void shouldFindAllElementsInDatabase() {

        //GIVEN
        BookDto bookDtoOne = new BookDto(Author.of("Kentarou"), Title.of("Berserk"), Set.of());
        BookDto bookDtoTwo = new BookDto(Author.of("Oda"), Title.of("One Piece"), Set.of());
        BookDto bookDtoThree = new BookDto(Author.of("Tolkien"), Title.of("Hobbit"), Set.of());
        bookService.save(bookDtoOne);
        bookService.save(bookDtoTwo);
        bookService.save(bookDtoThree);

        //WHEN
        bookService.findALl();

        //THEN
        Assertions.assertThat(bookService.findALl()).hasSize(3);
    }

    @Test
    void shouldUpdateRecordInDatabase() {

        //GIVEN
        BookDto bookDto = new BookDto(Author.of("Ada"), Title.of("Two Piece"), Set.of());
        BookDto updatedBookDto = new BookDto(Author.of("Oda"), Title.of("One Piece"), Set.of());
        BookId bookId = bookService.save(bookDto);

        //WHEN
        bookService.update(bookId, updatedBookDto);

        //THEN
        Book actualBook = bookService.find(bookId);
        Book expectedBook = new Book(Author.of("Oda"), Title.of("One Piece"), Set.of());
        Assertions.assertThat(actualBook.getBookId()).isNotNull();
        assertAreEqual(actualBook, expectedBook);
    }

    @Test
    void shouldDecreaseSizeOfTotalBooksByDeletingBook() {

        //GIVEN
        BookDto bookDto = new BookDto(Author.of("Kentarou"), Title.of("Berserk"), Set.of());
        BookId bookId = bookService.save(bookDto);

        //WHEN
        bookService.delete(bookId);

        //THEN
        Assertions.assertThat(bookService.findALl()).isEmpty();
    }

    @Test
    void shouldAddChaptersToDatabase() {

        //GIVEN
        ChapterDto chapterDto = new ChapterDto("Prologue", 1);
        BookDto bookDto = new BookDto(Author.of("Tolkien"), Title.of("Hobbit"), Set.of(chapterDto));

        //WHEN
        BookId bookId = bookService.save(bookDto);


        //THEN
        Book book = bookService.find(bookId);
        Chapter chapter = new Chapter("Prologue", 1);
        Book expectedBook = new Book(Author.of("Tolkien"), Title.of("Hobbit"), Set.of(chapter));
        assertAreEqual(book, expectedBook);
    }


    @Test
    void shouldAddPublisherToBook() {
        //GIVEN
        Long publisherId = createPublisher();
        BookId bookId = createBook();

        //WHEN
        bookService.addPublisher(publisherId, bookId);

        //THEN
        Book actualBook = bookService.find(bookId);
        Publisher expectedPublisher = new Publisher("Shonen Jump");
        Book expectedBook = new Book(Author.of("Tolkien"), Title.of("Hobbit"), Set.of());
        expectedBook.addPublisher(expectedPublisher);
        assertAreEqual(actualBook, expectedBook);
    }

    private BookId createBook() {
        BookDto bookDto = new BookDto(Author.of("Tolkien"), Title.of("Hobbit"), Set.of());
        BookId bookId = bookService.save(bookDto);
        return bookId;
    }

    private Long createPublisher() {
        PublisherDto publisherDto = new PublisherDto("Shonen Jump");
        Long publisherId = publisherService.save(publisherDto);
        return publisherId;
    }

//    @Test
//    void fu() {
//        //GIVEN
//        PublisherDto publisherDtoOne = new PublisherDto("Publisher 1");
//        PublisherDto publisherDtoTwo = new PublisherDto("Publisher 2");
//        PublisherDto publisherDtoThree = new PublisherDto("Publisher 3");
//
//        BookDto firstBookPublishedByPublisherOne = new BookDto(Author.of("a"), Title.of("1"), Set.of(), Set.of(publisherDtoOne));
//        BookDto secondBookPublishedByPublisherOne = new BookDto(Author.of("b"), Title.of("1"), Set.of(), Set.of(publisherDtoOne));
//
//        BookDto firstBookPublishedByPublisherTwo = new BookDto(Author.of("a"), Title.of("2"), Set.of(), Set.of(publisherDtoTwo));
//        BookDto secondBookPublishedByPublisherTwo = new BookDto(Author.of("b"), Title.of("2"), Set.of(), Set.of(publisherDtoTwo));
//
//        BookDto firstBookPublishedByPublisherTree = new BookDto(Author.of("a"), Title.of("3"), Set.of(), Set.of(publisherDtoThree));
//        BookDto secondBookPublishedByPublisherThree = new BookDto(Author.of("b"), Title.of("3"), Set.of(), Set.of(publisherDtoThree));
//
//        //AND
//        Set<ChapterDto> chapterDtos = Set.of(
//                new ChapterDto("Act 1", 1),
//                new ChapterDto("Act 2", 2),
//                new ChapterDto("Act 3", 3)
//        );
//
//        Set<PublisherDto> publisherDtos = Set.of(
//                publisherDtoOne,
//                publisherDtoTwo,
//                publisherDtoThree
//        );
//
//        BookDto bookSharedByPublishers = new BookDto(Author.of("Author"), Title.of("Title"), chapterDtos, publisherDtos);
//
//        //WHEN
//        bookService.save(bookSharedByPublishers);
//
//        //THEN
//
//
//    }


    private void assertAreEqual(Book actualBook, Book expectedBook) {
        Assertions.assertThat(actualBook)
                .usingRecursiveComparison()
                .ignoringFields("id", "chapters.id", "chapters.book", "publishers.id", "publishers.publishedBooks.id")
                .ignoringCollectionOrder()
                .isEqualTo(expectedBook);
    }


}