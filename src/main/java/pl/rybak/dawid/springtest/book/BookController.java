package pl.rybak.dawid.springtest.book;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PutMapping("/{bookId}")
    public void updateBook(@PathVariable BookId bookId, @RequestBody BookDto book) {

        bookService.update(bookId, book);
    }
}
