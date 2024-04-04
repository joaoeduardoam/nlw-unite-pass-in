package com.joaoeduardoam.passin.dto.attendee;

import lombok.Builder;

@Builder
public record AttendeeBadgeDTO(String name, String email, String checkinUrl, String eventId) {
}
