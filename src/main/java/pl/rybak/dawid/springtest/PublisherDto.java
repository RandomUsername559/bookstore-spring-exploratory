package pl.rybak.dawid.springtest;

public class PublisherDto {
    private final String publisherName;

    public PublisherDto(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    @Override
    public String toString() {
        return "PublisherDto{" +
                "publisherName='" + publisherName + '\'' +
                '}';
    }
}
