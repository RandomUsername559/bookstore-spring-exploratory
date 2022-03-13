package pl.rybak.dawid.bookstorespringexploratory.publisher;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.rybak.dawid.bookstorespringexploratory.AbstractTestIT;


public class PublisherServiceTestIT extends AbstractTestIT {
    @Autowired
    private PublisherService publisherService;

    @Test
    void shouldCreatePublisher() {
        //GIVEN
        PublisherDto publisherDto = new PublisherDto("Insignia");
        Long publisherId = publisherService.save(publisherDto);

        //WHEN
        publisherService.save(publisherDto);

        //THEN
        Publisher actualPublisher = publisherService.find(publisherId);
        Publisher expectedPublisherDto = new Publisher("Insignia");
        assertAreEqual(actualPublisher, expectedPublisherDto);
    }

    @Test
    void shouldFindWantedElementInDatabase() {
        //GIVEN
        PublisherDto publisherDto = new PublisherDto("Insignia");
        Long publisherId = publisherService.save(publisherDto);

        //WHEN
        Publisher actualPublisher = publisherService.find(publisherId);

        //THEN
        Publisher expectedPublisherDto = new Publisher("Insignia");
        assertAreEqual(actualPublisher, expectedPublisherDto);

    }

    @Test
    void shouldFindAllElementsInDatabase() {
        //GIVEN
        PublisherDto publisherDtoOne = new PublisherDto("Insignia");
        PublisherDto publisherDtoTwo = new PublisherDto("Insignia");
        PublisherDto publisherDtoThree = new PublisherDto("Insignia");


        //WHEN
        publisherService.save(publisherDtoOne);
        publisherService.save(publisherDtoTwo);
        publisherService.save(publisherDtoThree);
        publisherService.findALl();

        //THEN
        Assertions.assertThat(publisherService.findALl()).hasSize(3);
    }

    @Test
    void shouldUpdateRecordInDatabase() {

        //GIVEN
        PublisherDto publisherDto = new PublisherDto("Wrong name");
        PublisherDto updatedPublisherDto = new PublisherDto("Insignia");
        Long publisherId = publisherService.save(publisherDto);

        //WHEN
        publisherService.update(publisherId, updatedPublisherDto);

        //THEN
        Publisher actualPublisher = publisherService.find(publisherId);
        Publisher expectedPublisher = new Publisher("Insignia");
        Assertions.assertThat(actualPublisher.getId()).isNotNull();
        assertAreEqual(actualPublisher, expectedPublisher);
    }


    @Test
    void shouldDecreaseSizeOfTotalBooksByDeletingBook() {

        //GIVEN
        PublisherDto publisherDto = new PublisherDto("Insignia");
        Long publisherId = publisherService.save(publisherDto);

        //WHEN
        publisherService.delete(publisherId);

        //THEN
        Assertions.assertThat(publisherService.findALl()).isEmpty();
    }

    private void assertAreEqual(Publisher actualBook, Publisher expectedBook) {
        Assertions.assertThat(actualBook)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .ignoringCollectionOrder()
                .isEqualTo(expectedBook);
    }
}
