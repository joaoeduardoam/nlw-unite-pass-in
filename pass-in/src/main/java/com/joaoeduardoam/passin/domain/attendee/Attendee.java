package com.joaoeduardoam.passin.domain.attendee;


import com.joaoeduardoam.passin.domain.event.Event;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "attendees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attendee {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

//    public Attendee(String name, String email, Event event, LocalDateTime createdAt) {
//
//        this.name = name;
//        this.email = email;
//        this.event = event;
//        this.createdAt = createdAt;
//
//    }
}




















