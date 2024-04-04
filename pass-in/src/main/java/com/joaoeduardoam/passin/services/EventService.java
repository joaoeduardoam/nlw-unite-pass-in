package com.joaoeduardoam.passin.services;

import com.joaoeduardoam.passin.domain.attendee.Attendee;
import com.joaoeduardoam.passin.domain.event.Event;
import com.joaoeduardoam.passin.domain.event.exception.EventFullException;
import com.joaoeduardoam.passin.domain.event.exception.EventNotFoundException;
import com.joaoeduardoam.passin.dto.attendee.AttendeeIdDTO;
import com.joaoeduardoam.passin.dto.attendee.AttendeeRequestDTO;
import com.joaoeduardoam.passin.dto.event.EventDetailDTO;
import com.joaoeduardoam.passin.dto.event.EventIdDTO;
import com.joaoeduardoam.passin.dto.event.EventRequestDTO;
import com.joaoeduardoam.passin.repositories.AttendeeRepository;
import com.joaoeduardoam.passin.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;
    private final AttendeeService attendeeService;

    public EventDetailDTO getEventDetail(String eventId){

        Event event = getEventById(eventId);

        Integer numberOfAttendees = attendeeRepository.countByEventId(eventId);

        return new EventDetailDTO(event, numberOfAttendees);

    }


    public EventIdDTO createEvent(EventRequestDTO dto){

        //Event newEvent = new Event(dto, createSlug(dto.title()));
        Event newEvent = Event.builder()
                .title(dto.title())
                .details(dto.details())
                .maximumAttendees(dto.maximumAttendees())
                .slug(createSlug(dto.title()))
                .build();

        eventRepository.save(newEvent);
        return new EventIdDTO(newEvent.getId());

    }

    public AttendeeIdDTO registerAttendeeOnEvent(String eventId, AttendeeRequestDTO dto){

        attendeeService.verifyAttendeeSubscription(dto.email(), eventId);
        System.out.println("1111111111");
        Event event = getEventById(eventId);
        System.out.println("22222222222"+event.getId());
        Integer numberOfAttendees = attendeeRepository.countByEventId(eventId);
        System.out.println("33333333333"+numberOfAttendees);
        if (event.getMaximumAttendees() <= numberOfAttendees) throw new EventFullException("The Event is Full!");

        //Attendee newAttendee = new Attendee(dto.name(), dto.email(), event, LocalDateTime.now());
        Attendee newAttendee = Attendee.builder()
                .name(dto.name())
                .email(dto.email())
                .event(event)
                .createdAt(LocalDateTime.now())
                .build();
        System.out.println("44444444444444"+newAttendee.getName());
        attendeeService.registerAttendee(newAttendee);

        return new AttendeeIdDTO(newAttendee.getId());

    }

    private Event getEventById(String eventId){
        return eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(
                "Event not found with ID:"+eventId));
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
