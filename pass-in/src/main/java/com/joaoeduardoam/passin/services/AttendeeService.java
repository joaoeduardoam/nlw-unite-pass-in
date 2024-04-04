package com.joaoeduardoam.passin.services;

import com.joaoeduardoam.passin.domain.attendee.Attendee;
import com.joaoeduardoam.passin.domain.attendee.exception.AttendeeAlreadyExistException;
import com.joaoeduardoam.passin.domain.attendee.exception.AttendeeNotFoundException;
import com.joaoeduardoam.passin.domain.checkin.CheckIn;
import com.joaoeduardoam.passin.domain.event.Event;
import com.joaoeduardoam.passin.domain.event.exception.EventNotFoundException;
import com.joaoeduardoam.passin.dto.attendee.AttendeeBadgeDTO;
import com.joaoeduardoam.passin.dto.attendee.AttendeeDetailsDTO;
import com.joaoeduardoam.passin.dto.attendee.AttendeesListResponseDTO;
import com.joaoeduardoam.passin.repositories.AttendeeRepository;
import com.joaoeduardoam.passin.repositories.CheckinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;
    private final CheckInService checkInService;

    public List<Attendee> getAllAttendeesFromEvent(String eventId) {
        return attendeeRepository.findByEventId(eventId);
    }


    public List<AttendeeDetailsDTO> getEventAttendees(String eventId){

        List<Attendee> attendees = getAllAttendeesFromEvent(eventId);

        List<AttendeeDetailsDTO> attendeeDetailsList = attendees.stream().map(attendee -> {

            Optional<CheckIn> checkIn = checkInService.getCheckIn(attendee.getId());

            //LocalDateTime checkedInAt = checkIn.isPresent() ? checkIn.get().getCreatedAt() : null;
            LocalDateTime checkedInAt = checkIn.<LocalDateTime>map(CheckIn::getCreatedAt).orElse(null);

            return new AttendeeDetailsDTO(attendee,checkedInAt);

            }).toList();

        return  attendeeDetailsList;

    }


    public AttendeeBadgeDTO getAttendeeBadge(String attendeeId, UriComponentsBuilder uriBuilder){

        Attendee attendee = getAttendeeById(attendeeId);

        var uri = uriBuilder.path("attendees/{attendeeId}/check-in").buildAndExpand(attendeeId).toUri().toString();

        return AttendeeBadgeDTO.builder()
                .name(attendee.getName())
                .email(attendee.getEmail())
                .checkinUrl(uri)
                .eventId(attendee.getId())
                .build();


    }

    public void checkinAttendee(String attendeeId) {

        Attendee attendee = getAttendeeById(attendeeId);

        checkInService.registerCheckIn(attendee);


    }


    public void verifyAttendeeSubscription(String email, String eventId){
        Optional<Attendee> maybeAttendee = attendeeRepository.findByEventIdAndEmail(eventId,email);
        if(maybeAttendee.isPresent()) throw new AttendeeAlreadyExistException("Attendee is already registered!");
    }

    public Attendee registerAttendee(Attendee newAttendee){
        attendeeRepository.save(newAttendee);
        return newAttendee;
    }

    private Attendee getAttendeeById(String attendeeId){
        return attendeeRepository.findById(attendeeId).orElseThrow(() -> new AttendeeNotFoundException(
                "Attendee not found with ID: "+attendeeId));
    }



}













