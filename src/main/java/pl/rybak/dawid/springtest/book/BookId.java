package pl.rybak.dawid.springtest.book;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookId implements Serializable {

    @Column(name = "id")
    private Long bookId;

    //In order to satisfy Hibernate we need an empty constructor


    public BookId() {
//        this.bookId =null;
    }

    public BookId(Long id) {
        bookId = id ;
    }

    static BookId of (Long id){
        return new BookId(id);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookId bookId = (BookId) o;
        return Objects.equals(this.bookId, bookId.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId);
    }

    public BookId getId() {
        return BookId.of(bookId);
    }

    public Long getAsLong() {
        return bookId;
    }
}
