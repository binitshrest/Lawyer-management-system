package org.binitshrestha.userservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="tbl_appointment")
@Getter @Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lawyer_id", nullable = false)
    private Lawyer lawyer;

    private LocalDateTime scheduledDate;
    private LocalDateTime scheduledTime;
    private Integer durationMinutes;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
}
