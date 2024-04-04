package com.joaoeduardoam.passin.dto.event;

import com.joaoeduardoam.passin.domain.event.Event;

public record EventDetailDTO(String id, String title, String details,
                             String slug, Integer maximumAttendees,
                             Integer numberOfAttendees) {

    public EventDetailDTO(Event event, Integer numberOfAttendees){

        this(event.getId(), event.getTitle(), event.getDetails(),
                event.getSlug(), event.getMaximumAttendees(),
                numberOfAttendees);

    }
}
