package com.joaoeduardoam.passin.repositories;

import com.joaoeduardoam.passin.domain.attendee.Attendee;
import com.joaoeduardoam.passin.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeeRepository extends JpaRepository<Attendee, String> {
}
