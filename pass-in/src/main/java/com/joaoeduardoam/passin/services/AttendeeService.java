package com.joaoeduardoam.passin.services;

import com.joaoeduardoam.passin.domain.attendee.Attendee;
import com.joaoeduardoam.passin.domain.checkin.CheckIn;
import com.joaoeduardoam.passin.dto.attendee.AttendeeDetailsDTO;
import com.joaoeduardoam.passin.dto.attendee.AttendeesListResponseDTO;
import com.joaoeduardoam.passin.repositories.AttendeeRepository;
import com.joaoeduardoam.passin.repositories.CheckinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {

    private final AttendeeRepository attendeeRepository;
    private final CheckinRepository checkinRepository;

    public List<Attendee> getAllAttendeesFromEvent(String eventId) {
        return attendeeRepository.findByEventId(eventId);
    }


    public List<AttendeeDetailsDTO> getEventAttendees(String eventId){

        List<Attendee> attendees = getAllAttendeesFromEvent(eventId);

        List<AttendeeDetailsDTO> attendeeDetailsList = attendees.stream().map(attendee -> {

            Optional<CheckIn> checkIn = checkinRepository.findByAttendeeId(attendee.getId());

            //LocalDateTime checkedInAt = checkIn.isPresent() ? checkIn.get().getCreatedAt() : null;
            LocalDateTime checkedInAt = checkIn.<LocalDateTime>map(CheckIn::getCreatedAt).orElse(null);

            return new AttendeeDetailsDTO(attendee,checkedInAt);

            }).toList();

        return  attendeeDetailsList;

    }
}
