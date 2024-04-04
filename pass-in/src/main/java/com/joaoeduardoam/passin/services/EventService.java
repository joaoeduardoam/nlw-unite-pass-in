package com.joaoeduardoam.passin.services;

import com.joaoeduardoam.passin.domain.event.Event;
import com.joaoeduardoam.passin.domain.event.EventNotFoundException;
import com.joaoeduardoam.passin.dto.event.EventDetailDTO;
import com.joaoeduardoam.passin.dto.event.EventIdDTO;
import com.joaoeduardoam.passin.dto.event.EventRequestDTO;
import com.joaoeduardoam.passin.repositories.AttendeeRepository;
import com.joaoeduardoam.passin.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;

    public EventDetailDTO getEventDetail(String eventId){

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException(
                        "Event not found with ID:"+eventId));

        Integer numberOfAttendees = attendeeRepository.countByEventId(eventId);

        return new EventDetailDTO(event, numberOfAttendees);

    }


    public EventIdDTO createEvent(EventRequestDTO dto){

        Event newEvent = new Event(dto, createSlug(dto.title()));
        eventRepository.save(newEvent);
        return new EventIdDTO(newEvent.getId());

    }


    private String createSlug(String text){

        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD) //separates accents from characters
                .replaceAll("\\p{InCOMBINING_DIACRITICAL_MARKS}","") //replaces accents to empty string
                .replaceAll("^\\w\\s", "") // replaces non-alphanumeric characters to empty string
                .replaceAll("\\s+", "-")    // replaces spaces to hyphens
                .toLowerCase();

        return normalized;
    }



}
