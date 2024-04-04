package com.joaoeduardoam.passin.dto.attendee;

import com.joaoeduardoam.passin.domain.attendee.Attendee;

import java.time.LocalDateTime;

public record AttendeeDetailsDTO (String id, String name, String email,
                                 LocalDateTime createdAt,
                                 LocalDateTime checkedInAt){

    public AttendeeDetailsDTO(Attendee attendee, LocalDateTime checkedInAt){

        this(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(), checkedInAt);

    }
}
