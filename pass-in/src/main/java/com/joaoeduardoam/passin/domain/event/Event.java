package com.joaoeduardoam.passin.domain.event;

import com.joaoeduardoam.passin.dto.event.EventRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    @NotNull
    private String title;

    @NotNull
    private String details;

    @NotNull
    @Column(unique = true)
    private String slug;

    @NotNull
    @Column(name = "maximum_attendees")
    private Integer maximumAttendees;

//    public Event(EventRequestDTO dto, String slug) {
//        setTitle(dto.title());
//        setDetails(dto.details());
//        setMaximumAttendees(dto.maximumAttendees());
//        setSlug(slug);
//    }
}
