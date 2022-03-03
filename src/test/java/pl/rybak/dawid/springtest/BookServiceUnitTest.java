package pl.rybak.dawid.springtest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
    public void cleanDatabaseEach() {

        List<String> tables = jdbcTemplate.queryForList(
                "SELECT table_name FROM information_schema.tables\n" +
                        "WHERE table_schema = 'public';", String.class);

        for (String table : tables) {
            jdbcTemplate.execute("truncate " + table);
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
        bookService.save(Author.of("Oda"), Title.of("One Piece"));

        // THEN
        Assertions.assertThat(bookService.findALl().size()).isEqualTo(1);
    }

    @Test
    void shouldSaveAllFieldsOfBook() {

        // GIVEN && WHEN
        BookId bookId = bookService.save(Author.of("Oda"), Title.of("One Piece"));

        // THEN
        Book actualBook = bookService.findExisting(bookId);
        Book expectedBook = new Book(Title.of("One Piece"), Author.of("Oda"));
        Assertions.assertThat(actualBook.getBookId()).isNotNull();
        Assertions.assertThat(actualBook)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .usingOverriddenEquals()
                .isEqualTo(expectedBook);
    }

    @Test
    void shouldDecreaseSizeOfTotalBooksByDeletingBook() {

        //GIVEN
        BookId bookId = bookService.save(Author.of("Kentarou"), Title.of("Berserk"));

        //WHEN
        bookService.delete(bookId);

        //THEN
        Assertions.assertThat(bookService.findALl().size()).isZero();
    }

    @Test
    void shouldUpdateRecordInDatabase() {

        //GIVEN
        BookId bookId = bookService.save(Author.of("Ada"), Title.of("Two Piece"));

        //WHEN
        BookDto bookDto = new BookDto(Author.of("Oda"), Title.of("One Piece"));
        bookService.update(bookId, bookDto);

        //THEN
        Book actualBook = bookService.findExisting(bookId);
        Book expectedBook = new Book(Title.of("One Piece"), Author.of("Oda"));
        Assertions.assertThat(actualBook.getBookId()).isNotNull();
        Assertions.assertThat(actualBook)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .usingOverriddenEquals()
                .isEqualTo(expectedBook);
    }
}