package com.joaoeduardoam.passin.domain.checkin;

import com.joaoeduardoam.passin.domain.attendee.Attendee;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "check_ins")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToOne
    @NotNull
    @JoinColumn(name = "attendee_id")
    private Attendee attendee;
}
