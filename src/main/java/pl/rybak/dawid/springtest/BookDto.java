package pl.rybak.dawid.springtest;

public class BookDto {
    private final Author author;
    private final Title title;

    public BookDto(Author author, Title title) {
        this.author = author;
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public Title getTitle() {
        return title;
    }
}
