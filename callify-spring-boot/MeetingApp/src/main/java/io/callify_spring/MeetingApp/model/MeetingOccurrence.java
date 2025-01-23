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

    @Column
    private Boolean status; // Mark if this instance is cancelled

    @Column
    private LocalDateTime startTime; // Optional notes or details for this occurrence

    @Column 
    private int duration;

    @Column 
    private String zoomOccurrenceId; // created by Zoom API(s)

    // Getters and setters
}