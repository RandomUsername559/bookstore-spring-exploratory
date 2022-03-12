package pl.rybak.dawid.springtest.book;

public class ChapterDto {
    private final String chapterName;
    private final int page;

    public ChapterDto(String chapterName, int page) {
        this.chapterName = chapterName;
        this.page = page;
    }


    public String getChapterName() {
        return chapterName;
    }

    public int getPage() {
        return page;
    }

    @Override
    public String toString() {
        return "ChapterDto{" +
                "chapterName='" + chapterName + '\'' +
                ", page=" + page +
                '}';
    }
}
