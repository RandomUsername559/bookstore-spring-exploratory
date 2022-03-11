package pl.rybak.dawid.springtest;

import java.util.Set;

public class BookDto {
    private final Author author;
    private final Title title;
    private final Set<ChapterDto> chapterDtoList;
    private final Set<PublisherDto> publisherDtoSet;

    public BookDto(Author author, Title title, Set<ChapterDto> chapterDtoSet, Set<PublisherDto> publisherDtoSet) {
        this.author = author;
        this.title = title;
        this.chapterDtoList = chapterDtoSet;
        this.publisherDtoSet = publisherDtoSet;
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

    public Set<PublisherDto> getPublisherDtoSet() {
        return publisherDtoSet;
    }


    @Override
    public String toString() {
        return "BookDto{" +
                "author=" + author +
                ", title=" + title +
                '}';
    }
}
