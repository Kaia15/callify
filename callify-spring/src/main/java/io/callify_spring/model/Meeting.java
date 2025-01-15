package io.callify_spring.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;

public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String joinUrl;

    @Column(nullable = false)
    private Long registrantId;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private MeetingType meetingType; // {BASIC, INSTANT, RECURRING_NO_FIXED_TIME, RECURRING_FIXED_TIME}

    @Column(nullable = false)
    private String topic;

    @Column(nullable = false)
    private int duration;

    @Column(nullable = false)
    private String passcode; // Auto-generate or user-provided

    @Embedded // make Recurrence not treated as a separate entity
    private Recurrence recurrence;

    // make a nested class but independent of outer class
    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MeetingOccurrence> occurrences;

    // Getters and setters

    public static class Recurrence {

        @Column(nullable = false)
        private RecurrenceType type; // DAILY, WEEKLY, MONTHLY, NONE

        @Column
        private Integer repeatInterval; // Number of intervals between recurrences (e.g., every 2 weeks)

        @Column
        private LocalDateTime startDateTime; // Start date-time for the recurrence

        @Column
        private LocalDateTime endDateTime; // End date-time for the recurrence

        @Column
        private Integer endTimes; // Maximum number of occurrences

        @ElementCollection
        private List<Integer> weeklyDays; // Days of the week for recurrence (1-7 for Sunday-Saturday)

        @Column
        private Integer monthlyDay; // Specific day of the month

        @Column
        private Integer monthlyWeek; // Week of the month (1-4 or -1 for last)

        @Column
        private Integer monthlyWeekDay; // Day of the week for monthly recurrence (1-7)
    }

    // Getters and setters
}
