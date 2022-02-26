package pl.rybak.dawid.springtest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BookServiceUnitTest {

    @Autowired
    private BookService bookService;

    @Test
    void whenApplicationStarts_thenHibernateCreatesInitialRecords() {
        List<Book> books = bookService.findALl();
        List<Book> books2 = bookService.findALl();

        Assertions.assertThat(books.size()).isEqualTo(6);

    }

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
        Assertions.assertThat(actualBook.getId()).isNotNull();
        Assertions.assertThat(actualBook)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .usingOverriddenEquals()
                .isEqualTo(expectedBook);
    }
}