package pl.rybak.dawid.springtest;

import javax.persistence.*;


@Entity
@Table(name = "book_chapter")


public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

}
