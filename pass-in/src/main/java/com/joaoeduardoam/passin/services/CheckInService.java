package com.joaoeduardoam.passin.services;

import com.joaoeduardoam.passin.domain.attendee.Attendee;
import com.joaoeduardoam.passin.domain.checkin.CheckIn;
import com.joaoeduardoam.passin.domain.checkin.exception.CheckInAlreadyExistsException;
import com.joaoeduardoam.passin.repositories.CheckinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckInService {

    private final CheckinRepository checkinRepository;

    public void registerCheckIn (Attendee attendee){

        verifyCheckInExists(attendee.getId());

        CheckIn newCheckIn = CheckIn.builder()
                .attendee(attendee)
                .createdAt(LocalDateTime.now())
                .build();

        checkinRepository.save(newCheckIn);

    }



    private void verifyCheckInExists(String attendeeId){

        Optional<CheckIn> maybeCheckin = getCheckIn(attendeeId);

        if (maybeCheckin.isPresent()) throw new CheckInAlreadyExistsException("Attendee already checked in!!");

    }


    public Optional<CheckIn> getCheckIn(String attendeeId) {

        return  checkinRepository.findByAttendeeId(attendeeId);

    }
}
