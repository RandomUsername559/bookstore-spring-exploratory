package pl.rybak.dawid.springtest.book;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "book_chapter")
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence-generator")
    @SequenceGenerator(
            name = "sequence-generator",
            sequenceName = "chapter_sequence",
            allocationSize = 10
    )
    private Long id;
    private String chapterName;
    private int page;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    private Book book;

    private Chapter() {
    }

    public Chapter(String chapterName, int page) {
        this.chapterName = chapterName;
        this.page = page;
    }

    public long getId() {
        return id;
    }

    public String getChapterName() {
        return chapterName;
    }

    public int getPage() {
        return page;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", chapterName='" + chapterName + '\'' +
                ", page=" + page +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chapter chapter = (Chapter) o;
        return Objects.equals(id, chapter.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
