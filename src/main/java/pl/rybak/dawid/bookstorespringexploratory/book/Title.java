package pl.rybak.dawid.bookstorespringexploratory.book;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Title {
    private final String title;

    //In order to satisfy Hibernate we need an empty constructor
    private Title() {
        this.title = null;
    }

    private Title(String title) {
        this.title = title;
    }

    static Title of(String title) {
        return new Title(title);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Title title1 = (Title) o;
        return Objects.equals(title, title1.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return title;
    }
}
