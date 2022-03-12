package pl.rybak.dawid.springtest;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "book")
public class Book {

    //        @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
//    @SequenceGenerator(
//            name = "sequence-generator",
//            sequenceName = "book_sequence",
//            allocationSize = 10,
//            initialValue = 444
//    )
//    @Convert(converter = IdConverter.class)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
    @SequenceGenerator(
            name = "sequence-generator",
            sequenceName = "book_sequence",
            allocationSize = 10
    )
    private Long id;

    @Embedded
    private Author author;

    @Embedded
    private Title title;

    @OneToMany(
            mappedBy = "book",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Chapter> chapters = new HashSet<>();

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "book_publisher",
            joinColumns = @JoinColumn(name = "publisher_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Publisher> publishers = new HashSet<>();

    private Book() {
    }

    public Book(Author author, Title title, Set<Chapter> chapters, Set<Publisher> publishers) {
        this.author = author;
        this.title = title;
        this.chapters = chapters;
        this.chapters.forEach(chapter -> chapter.setBook(this));
        this.publishers = publishers;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public BookId getBookId() {
        return BookId.of(id);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author=" + author +
                ", title=" + title +
                ", chapters=" + chapters +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
