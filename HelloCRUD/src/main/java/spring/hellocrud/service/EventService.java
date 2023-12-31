package spring.hellocrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.hellocrud.domain.Event;
import spring.hellocrud.repository.EventRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public void add(Event event) {
        eventRepository.save(event);
    }

    public Event findById(Integer id) {
        return eventRepository.findById(id);
    }

    public void update(Integer id, Event event) {
        eventRepository.save(id, event);
    }

    public void remove(Integer id) {
        eventRepository.remove(id);
    }
}