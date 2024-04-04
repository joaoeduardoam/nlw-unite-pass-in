package com.joaoeduardoam.passin.repositories;

import com.joaoeduardoam.passin.domain.attendee.Attendee;
import com.joaoeduardoam.passin.domain.checkin.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CheckinRepository extends JpaRepository<CheckIn, Integer> {
    Optional<CheckIn> findByAttendeeId(String attendeeId);
}
