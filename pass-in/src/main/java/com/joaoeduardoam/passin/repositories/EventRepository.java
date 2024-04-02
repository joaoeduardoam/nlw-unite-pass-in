package com.joaoeduardoam.passin.repositories;

import com.joaoeduardoam.passin.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> {
}
