package pl.rybak.dawid.springtest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootTest
public class BookServiceUnitTest {
    @Autowired
    private BookService bookService;
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
//    @Test
//    void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
//        List<Book> books = bookService.findALl();
//        List<Book> books2 = bookService.findALl();
//
//        Assertions.assertThat(books.size()).isEqualTo(1);
//
//    }

    @Test
    void shouldIncreaseSizeOfTotalBooksByAddingBook() {

        // GIVEN && WHEN
        BookDto bookDto = new BookDto(Author.of("Oda"), Title.of("One Piece"), List.of());
        bookService.save(bookDto);

        // THEN
        Assertions.assertThat(bookService.findALl().size()).isEqualTo(1);
    }

    @Test
    void shouldSaveAllFieldsOfBook() {

        // GIVEN && WHEN
        BookDto bookDto = new BookDto(Author.of("Oda"), Title.of("One Piece"), List.of());
        BookId bookId = bookService.save(bookDto);

        // THEN
        Book actualBook = bookService.findExisting(bookId);
        Book expectedBookDto = new Book(Author.of("Oda"), Title.of("One Piece"), List.of());
        Assertions.assertThat(actualBook.getBookId()).isNotNull();
        assertAreEqual(actualBook, expectedBookDto);
    }


    @Test
    void shouldDecreaseSizeOfTotalBooksByDeletingBook() {

        //GIVEN
        BookDto bookDto = new BookDto(Author.of("Kentarou"), Title.of("Berserk"), List.of());
        BookId bookId = bookService.save(bookDto);

        //WHEN
        bookService.delete(bookId);

        //THEN
        Assertions.assertThat(bookService.findALl().size()).isZero();
    }

    @Test
    void shouldUpdateRecordInDatabase() {

        //GIVEN
        BookDto bookDto = new BookDto(Author.of("Ada"), Title.of("Two Piece"), List.of());

        BookId bookId = bookService.save(bookDto);

        //WHEN
        BookDto updatedBookDto = new BookDto(Author.of("Oda"), Title.of("One Piece"), List.of());
        bookService.update(bookId, updatedBookDto);

        //THEN
        Book actualBook = bookService.findExisting(bookId);
        Book expectedBook = new Book(Author.of("Oda"), Title.of("One Piece"), List.of());
        Assertions.assertThat(actualBook.getBookId()).isNotNull();
        Assertions.assertThat(actualBook)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .usingOverriddenEquals()
                .isEqualTo(expectedBook);
    }

    @Test
    void shouldAddChaptersToDatabase() {

        //GIVEN
        ChapterDto chapterDto = new ChapterDto("Prologue", 1);
        BookDto bookDto = new BookDto(Author.of("Tolkien"), Title.of("Hobbit"), List.of(chapterDto));

        //WHEN
        BookId bookId = bookService.save(bookDto);


        //THEN
        Book book = bookService.findExisting(bookId);
        Chapter chapter = new Chapter("Prologue", 1);
        Book expectedBook = new Book(Author.of("Tolkien"), Title.of("Hobbit"), List.of(chapter));

        assertAreEqual(book, expectedBook);


    }

    private void assertAreEqual(Book actualBook, Book expectedBook) {
        Assertions.assertThat(actualBook)
                .usingRecursiveComparison()
                .ignoringFields("id", "chapters.id", "chapters.book")
                .ignoringCollectionOrder()
                .isEqualTo(expectedBook);
    }
}