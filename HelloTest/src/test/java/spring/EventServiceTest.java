package spring;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.controller.dto.EventDTO;
import spring.domain.City;
import spring.domain.Event;
import spring.repository.CityRepository;
import spring.repository.EventRepository;
import spring.service.EventService;

@SpringBootTest

public class EventServiceTest {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CityRepository cityRepository;

    @Test
    public void testAddEvent() {
        EventDTO eventDTOin = new EventDTO(null, DtoClassesTest.NAME, DtoClassesTest.CITY);
        EventDTO eventDTOout = eventService.add(eventDTOin);

        Assertions.assertNotNull(eventDTOout.getId());
        Assertions.assertEquals(eventDTOin.getName(), eventDTOout.getName());
        Assertions.assertEquals(eventDTOin.getCity(), eventDTOout.getCity());

        Event event = eventRepository.findById(eventDTOout.getId()).orElse(null);
        City city = cityRepository.findByName(DtoClassesTest.CITY);

        Assertions.assertNotNull(event);
        Assertions.assertEquals(DtoClassesTest.NAME, event.getName());
        Assertions.assertEquals(event.getCity().getCityId(), city.getCityId());

        Assertions.assertNotNull(city);
        Assertions.assertEquals(DtoClassesTest.CITY, city.getName());
    }

    @Test
    public void testUpdateEvent() {
        EventDTO eventDTOin = new EventDTO(null, DtoClassesTest.NAME, DtoClassesTest.CITY);
        EventDTO addedEvent = eventService.add(eventDTOin);

        addedEvent.setName(DtoClassesTest.NAME + "_changed");
        addedEvent.setCity(DtoClassesTest.CITY + "_changed");
        EventDTO updatedEvent = eventService.update(addedEvent);


        Assertions.assertEquals(addedEvent.getId(), updatedEvent.getId());
        Assertions.assertEquals(DtoClassesTest.NAME + "_changed", updatedEvent.getName());
        Assertions.assertEquals(DtoClassesTest.CITY + "_changed", updatedEvent.getCity());
    }

    @Test
    public void testDeleteEvent() {
        EventDTO eventDTOin = new EventDTO(null, DtoClassesTest.NAME, DtoClassesTest.CITY);
        EventDTO addedEvent = eventService.add(eventDTOin);

        EventDTO deletedEvent = eventService.delete(addedEvent.getId());

        Assertions.assertEquals(addedEvent.getId(), deletedEvent.getId());
        Assertions.assertFalse(eventRepository.existsById(addedEvent.getId()));
    }
}
