package io.callify_spring.MeetingApp.model;

import java.time.LocalDateTime;
import java.util.List;

import io.callify_spring.MeetingApp.dto.MeetingDTO.RecurrenceDTO;
import jakarta.persistence.*;

// for now, one meeting only maps with one registrant
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String joinUrl; // auto-generate

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

    // Many-to-many relationship with attendees
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "meeting_attendees", 
        joinColumns = @JoinColumn(name = "meeting_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<Long> attendeeIds; // Store user IDs instead of User entities


    // Getters and setters

    public Meeting(Long userId, String topic, int duration) {
        this.registrantId = userId;
        this.topic = topic;
        this.duration = duration;
    }

    public List<Long> getAttendees() {
        return this.attendeeIds;
    }

    public void setRecurrence(Recurrence recurrence) {
        this.recurrence = recurrence;
    }

    public void setMeetingType(MeetingType meetingType) {
        this.meetingType = meetingType;
    }

    public void setMeetingFromDTO(RecurrenceDTO recurrenceFromDto, LocalDateTime STime, RecurrenceType RType) {
        if (recurrenceFromDto != null) {
            Meeting.Recurrence recurrence = new Meeting.Recurrence();
            recurrence.setType(RType);
            recurrence.setRepeatInterval(recurrenceFromDto.getRepeatInterval());
            recurrence.setStartDateTime(STime);
            recurrence.setEndDateTime(recurrenceFromDto.getEndDateTime());
            recurrence.setEndTimes(recurrenceFromDto.getEndTimes());
            recurrence.setWeeklyDays(recurrenceFromDto.getWeeklyDays());
            recurrence.setMonthlyDay(recurrenceFromDto.getMonthlyDay());
            recurrence.setMonthlyWeek(recurrenceFromDto.getMonthlyWeek());
            recurrence.setMonthlyWeekDay(recurrenceFromDto.getMonthlyWeekDay());
    
            this.setRecurrence(recurrence);
        }
    }

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

        // Getters and Setters for Recurrence class
        public RecurrenceType getType() {
            return type;
        }

        public void setType(RecurrenceType type) {
            this.type = type;
        }

        public Integer getRepeatInterval() {
            return repeatInterval;
        }

        public void setRepeatInterval(Integer repeatInterval) {
            this.repeatInterval = repeatInterval;
        }

        public LocalDateTime getStartDateTime() {
            return startDateTime;
        }

        public void setStartDateTime(LocalDateTime startDateTime) {
            this.startDateTime = startDateTime;
        }

        public LocalDateTime getEndDateTime() {
            return endDateTime;
        }

        public void setEndDateTime(LocalDateTime endDateTime) {
            this.endDateTime = endDateTime;
        }

        public Integer getEndTimes() {
            return endTimes;
        }

        public void setEndTimes(Integer endTimes) {
            this.endTimes = endTimes;
        }

        public List<Integer> getWeeklyDays() {
            return weeklyDays;
        }

        public void setWeeklyDays(List<Integer> weeklyDays) {
            this.weeklyDays = weeklyDays;
        }

        public Integer getMonthlyDay() {
            return monthlyDay;
        }

        public void setMonthlyDay(Integer monthlyDay) {
            this.monthlyDay = monthlyDay;
        }

        public Integer getMonthlyWeek() {
            return monthlyWeek;
        }

        public void setMonthlyWeek(Integer monthlyWeek) {
            this.monthlyWeek = monthlyWeek;
        }

        public Integer getMonthlyWeekDay() {
            return monthlyWeekDay;
        }

        public void setMonthlyWeekDay(Integer monthlyWeekDay) {
            this.monthlyWeekDay = monthlyWeekDay;
        }
    }

    // Getters and setters
    
}
