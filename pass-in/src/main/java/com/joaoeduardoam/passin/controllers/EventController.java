package com.joaoeduardoam.passin.controllers;

import com.joaoeduardoam.passin.dto.attendee.AttendeeDetailsDTO;
import com.joaoeduardoam.passin.dto.event.EventDetailDTO;
import com.joaoeduardoam.passin.dto.event.EventIdDTO;
import com.joaoeduardoam.passin.dto.event.EventRequestDTO;
import com.joaoeduardoam.passin.services.AttendeeService;
import com.joaoeduardoam.passin.services.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    private final AttendeeService attendeeService;

    @GetMapping("/{eventId}")
    public ResponseEntity<EventDetailDTO> getEvent(@PathVariable String eventId){
        EventDetailDTO event= eventService.getEventDetail(eventId);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO dto,
                                                UriComponentsBuilder uriBuilder){
        EventIdDTO eventIdDTO= eventService.createEvent(dto);
        var uri = uriBuilder.path("events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();
        return ResponseEntity.created(uri).body(eventIdDTO);
    }

    @GetMapping("/{eventId}/attendees")
    public ResponseEntity<List<AttendeeDetailsDTO>> getEventAttendees(@PathVariable String eventId){
        List<AttendeeDetailsDTO> attendees = attendeeService.getEventAttendees(eventId);
        return ResponseEntity.ok(attendees);
    }

}
