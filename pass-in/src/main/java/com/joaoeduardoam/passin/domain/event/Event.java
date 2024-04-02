package com.joaoeduardoam.passin.domain.event;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

}
