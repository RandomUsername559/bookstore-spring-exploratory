package pl.rybak.dawid.springtest;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "publishers")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String publisherName;

    @ManyToMany(
            mappedBy = "publishers",
            fetch = FetchType.EAGER
    )
    private Set<Book> publishedBooks;

    private Publisher() {
    }

    public Publisher(String publisherName, Set<Book> publishedBooks) {
        this.publisherName = publisherName;
        this.publishedBooks = publishedBooks;
    }

    public Long getId() {
        return id;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public Set<Book> getPublishedBooks() {
        return publishedBooks;
    }

    public void addBook(Book book) {
        publishedBooks.add(book);
    }

    @Override
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", publisherName='" + publisherName + '\'' +
                ", publishedBooks=" + publishedBooks +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return Objects.equals(id, publisher.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
