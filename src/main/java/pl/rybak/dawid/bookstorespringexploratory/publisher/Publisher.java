package pl.rybak.dawid.bookstorespringexploratory.publisher;

import pl.rybak.dawid.bookstorespringexploratory.book.Book;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
    @SequenceGenerator(
            name = "sequence-generator",
            sequenceName = "publisher_sequence",
            allocationSize = 10
    )
    private Long id;
    private String publisherName;

    @ManyToMany(
            mappedBy = "publishers",
            fetch = FetchType.EAGER
    )
    private Set<Book> publishedBooks = new HashSet<>();

    private Publisher() {
    }

    public Publisher(String publisherName) {
        this.publisherName = publisherName;
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

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
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
