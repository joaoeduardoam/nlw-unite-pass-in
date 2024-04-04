package com.joaoeduardoam.passin.controllers;

import com.joaoeduardoam.passin.domain.attendee.Attendee;
import com.joaoeduardoam.passin.dto.attendee.AttendeeBadgeDTO;
import com.joaoeduardoam.passin.services.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/attendees")
@RequiredArgsConstructor
public class AttendeeController {

    private final AttendeeService attendeeService;

    @GetMapping("/{attendeeId}/badge")
    public ResponseEntity<AttendeeBadgeDTO> getAttendeeBadge(@PathVariable String attendeeId, UriComponentsBuilder uriBuilder){
        AttendeeBadgeDTO attendeeBadgeDTO = attendeeService.getAttendeeBadge(attendeeId, uriBuilder);
        return ResponseEntity.ok(attendeeBadgeDTO);
    }

    @PostMapping("/{attendeeId}/checkin")
    public ResponseEntity registerCheckIn(@PathVariable String attendeeId, UriComponentsBuilder uriBuilder){
        attendeeService.checkinAttendee(attendeeId);
        var uri = uriBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(attendeeId).toUri();
        return ResponseEntity.created(uri).build();
    }



}
