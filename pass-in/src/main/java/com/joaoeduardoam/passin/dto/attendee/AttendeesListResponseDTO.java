package com.joaoeduardoam.passin.dto.attendee;

import lombok.Getter;

import java.util.List;


public record AttendeesListResponseDTO(List<AttendeeDetailsDTO> attendees) {

}
