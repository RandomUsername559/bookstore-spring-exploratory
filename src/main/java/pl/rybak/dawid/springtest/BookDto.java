package pl.rybak.dawid.springtest;

import java.util.List;

public class BookDto {
    private final Author author;
    private final Title title;
    private final List<ChapterDto> chapterDtoList;

    public BookDto(Author author, Title title, List<ChapterDto> chapterDtoList) {
        this.author = author;
        this.title = title;
        this.chapterDtoList = chapterDtoList;
    }

    public Author getAuthor() {
        return author;
    }

    public Title getTitle() {
        return title;
    }

    public List<ChapterDto> getChapterDtoList() {
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
