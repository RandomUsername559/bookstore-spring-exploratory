package pl.rybak.dawid.springtest.book;

import java.util.Set;

public class BookDto {
    private final Author author;
    private final Title title;
    private final Set<ChapterDto> chapterDtoList;

    public BookDto(Author author, Title title, Set<ChapterDto> chapterDtoSet) {
        this.author = author;
        this.title = title;
        this.chapterDtoList = chapterDtoSet;
    }

    public Author getAuthor() {
        return author;
    }

    public Title getTitle() {
        return title;
    }

    public Set<ChapterDto> getChapterDtoSet() {
        return chapterDtoList;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "author=" + author +
                ", title=" + title +
                '}';
    }
}
