package com.joaoeduardoam.passin.repositories;

import com.joaoeduardoam.passin.domain.attendee.Attendee;
import com.joaoeduardoam.passin.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttendeeRepository extends JpaRepository<Attendee, String> {

    List<Attendee> findByEventId(String eventId);

    Optional<Attendee> findByEventIdAndEmail(String eventId, String email);

    Integer countByEventId(String eventId);
}
