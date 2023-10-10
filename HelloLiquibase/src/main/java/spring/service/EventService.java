package spring.service;

import ch.qos.logback.core.joran.event.EndEvent;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.controller.dto.EventDTO;
import spring.domain.City;
import spring.domain.Event;
import spring.repository.CityRepository;
import spring.repository.EventRepository;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class EventService {

   //static final Logger log = LoggerFactory.getLogger(EventService.class);

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CityService cityService;

    public List<EventDTO> findAll() {
        List<Event> events = eventRepository.findAll();
        List<EventDTO> result = new ArrayList<>();
        //events.forEach(e -> result.add(EventDTO.getInstance(e)));
        for (Event e : events) {
            result.add(EventDTO.getInstance(e));
        }
        log.error("Error...");
        log.warn("Warning...");
        log.info("Reading {} records from Event table.", result.size());
        log.debug("Debug...");
        return result;
    }

    public EventDTO findById(Integer id) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event != null) {
            EventDTO eventDTO = EventDTO.getInstance(event);
            log.info("Reading {} from Event table.", eventDTO);
            return eventDTO;
        }
        log.error("Not found event, eventId={} from Event table.", id);
        return null;
    }


    public EventDTO add(EventDTO e) {
        City city= cityService.findByName(e.getCity());

        Event event = new Event(null, e.getName(), city);
        event = eventRepository.save(event);
        log.info("Added new event: {}", event);
        return EventDTO.getInstance(event);
    }


    public EventDTO update(EventDTO e) {
      Event event = eventRepository.findById(e.getId()).orElse(null);

        if (event!= null) {
           City city= cityService.findByName(e.getCity());

            event.setName(e.getName());
            event.setCity(city);
            event = eventRepository.save(event);
            log.info("Updated event with ID {}: {}", e.getId(), event);
            return EventDTO.getInstance(event);
        }
        log.error("Failed to update - Event not found with ID {}", e.getId());
        return null;
    }

    public EventDTO delete(Integer id) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event != null) {
            eventRepository.delete(event);
            log.info("Deleted event with ID {}: {}", id, event);
            return EventDTO.getInstance(event);
        }
        log.error("Failed to delete - Event not found with ID {}", id);
        return null;
    }
}