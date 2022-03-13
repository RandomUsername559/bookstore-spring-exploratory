package pl.rybak.dawid.bookstorespringexploratory.book;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Author {
    private final String author;

    //In order to satisfy Hibernate we need an empty constructor


    private Author() {
        this.author=null;
    }

    private Author(String author) {
        this.author = author;
    }

    static Author of (String author){
        return new Author(author);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author1 = (Author) o;
        return Objects.equals(author, author1.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author);
    }

    @Override
    public String toString() {
        return "Author{" +
                "author='" + author + '\'' +
                '}';
    }
}
