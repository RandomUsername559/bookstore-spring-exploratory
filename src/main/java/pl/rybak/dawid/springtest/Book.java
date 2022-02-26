package pl.rybak.dawid.springtest;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "Library")
public class Book implements Serializable {

    //    @Id
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
    private Title title;
    @Embedded
    private Author author;

    public Book() {
    }

    public Book(Title name, Author author) {
        this.title = name;
        this.author = author;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public BookId getId() {
        return BookId.of(id);
    }
}
