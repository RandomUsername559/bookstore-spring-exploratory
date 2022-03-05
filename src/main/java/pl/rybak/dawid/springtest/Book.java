package pl.rybak.dawid.springtest;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "Library")
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

    private Book() {
    }

    public Book(Author author, Title title, List<Chapter> chapters) {
        this.author = author;
        this.title = title;
        this.chapters = new HashSet<>(chapters);
        this.chapters.forEach(chapter -> chapter.setBook(this));
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

}
