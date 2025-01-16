package io.callify_spring.MeetingApp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MeetingOccurrence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Meeting meeting;

    @Column(nullable = false)
    private LocalDateTime occurrenceTime;

    @Column
    private Boolean cancelled; // Mark if this instance is cancelled

    @Column
    private String notes; // Optional notes or details for this occurrence

    // Getters and setters
}