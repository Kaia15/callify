package io.callify_spring.MeetingApp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import io.callify_spring.MeetingApp.dto.MeetingDTO;
import io.callify_spring.MeetingApp.dto.MeetingDTO.RecurrenceDTO;

@Entity
@Table(name = "meetings")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String joinUrl;

    @Column
    private Long registrantId;

    @Column
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column
    private MeetingType meetingType;

    @Column
    private String topic;

    @Column
    private int duration;

    @Column
    private String passcode;

    @Embedded
    private Recurrence recurrence;

    @ElementCollection
    @CollectionTable(name = "meeting_attendee_ids", joinColumns = @JoinColumn(name = "meeting_id"))
    @Column(name = "attendee_id")
    private List<Long> attendeeIds;

    // Constructors
    public Meeting() {}

    public Meeting(Long userId, String topic, int duration) {
        this.registrantId = userId;
        this.topic = topic;
        this.duration = duration;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getJoinUrl() {
        return joinUrl;
    }

    public void setJoinUrl(String joinUrl) {
        this.joinUrl = joinUrl;
    }

    public Long getRegistrantId() {
        return registrantId;
    }

    public void setRegistrantId(Long registrantId) {
        this.registrantId = registrantId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public MeetingType getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(MeetingType meetingType) {
        this.meetingType = meetingType;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }

    public Recurrence getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(Recurrence recurrence) {
        this.recurrence = recurrence;
    }

    public List<Long> getAttendeeIds() {
        return attendeeIds;
    }

    public void setAttendeeIds(List<Long> attendeeIds) {
        this.attendeeIds = attendeeIds;
    }

    // Method to populate meeting from DTOs
    public void setMeetingFromDTO(RecurrenceDTO recurrenceFromDto, LocalDateTime startTime, RecurrenceType recurrenceType) {
        if (recurrenceFromDto != null) {
            Recurrence recurrence = new Recurrence();
            recurrence.setType(recurrenceType);
            recurrence.setRepeatInterval(recurrenceFromDto.getRepeatInterval());
            recurrence.setStartDateTime(startTime);
            recurrence.setEndDateTime(recurrenceFromDto.getEndDateTime());
            recurrence.setEndTimes(recurrenceFromDto.getEndTimes());
            recurrence.setWeeklyDays(recurrenceFromDto.getWeeklyDays());
            recurrence.setMonthlyDay(recurrenceFromDto.getMonthlyDay());
            recurrence.setMonthlyWeek(recurrenceFromDto.getMonthlyWeek());
            recurrence.setMonthlyWeekDay(recurrenceFromDto.getMonthlyWeekDay());

            this.setRecurrence(recurrence);
        }
    }

    public void updateMeetingFromDTO(MeetingDTO meetingDTO, RecurrenceDTO recurrenceDTO) {
        if (meetingDTO != null) {
            if (meetingDTO.getTopic() != null) {
                this.setTopic(meetingDTO.getTopic());
            }

            if (meetingDTO.getDuration() > 0) {
                this.setDuration(meetingDTO.getDuration());
            }

            if (meetingDTO.getMeetingType() != null) {
                this.setMeetingType(meetingDTO.getMeetingType());
            }

            if (meetingDTO.getPasscode() != null) {
                this.setPasscode(meetingDTO.getPasscode());
            }
        }

        if (recurrenceDTO != null) {
            Recurrence recurrence = this.getRecurrence();
            if (recurrence == null) {
                recurrence = new Recurrence();
                this.setRecurrence(recurrence);
            }

            if (recurrenceDTO.getRepeatInterval() != null) {
                recurrence.setRepeatInterval(recurrenceDTO.getRepeatInterval());
            }

            if (recurrenceDTO.getStartDateTime() != null) {
                recurrence.setStartDateTime(recurrenceDTO.getStartDateTime());
            }

            if (recurrenceDTO.getEndDateTime() != null) {
                recurrence.setEndDateTime(recurrenceDTO.getEndDateTime());
            }

            if (recurrenceDTO.getEndTimes() != null) {
                recurrence.setEndTimes(recurrenceDTO.getEndTimes());
            }

            if (recurrenceDTO.getWeeklyDays() != null) {
                recurrence.setWeeklyDays(recurrenceDTO.getWeeklyDays());
            }

            if (recurrenceDTO.getMonthlyDay() != null) {
                recurrence.setMonthlyDay(recurrenceDTO.getMonthlyDay());
            }

            if (recurrenceDTO.getMonthlyWeek() != null) {
                recurrence.setMonthlyWeek(recurrenceDTO.getMonthlyWeek());
            }

            if (recurrenceDTO.getMonthlyWeekDay() != null) {
                recurrence.setMonthlyWeekDay(recurrenceDTO.getMonthlyWeekDay());
            }

            if (recurrenceDTO.getType() != null) {
                recurrence.setType(recurrenceDTO.getType());
            }
        }
    }

    // Embedded Recurrence Class
    @Embeddable
    public static class Recurrence {

        @Enumerated(EnumType.STRING)
        @Column
        private RecurrenceType type;

        @Column
        private Integer repeatInterval;

        @Column
        private LocalDateTime startDateTime;

        @Column
        private LocalDateTime endDateTime;

        @Column
        private Integer endTimes;

        @ElementCollection
        @CollectionTable(name = "recurrence_weekly_days", joinColumns = @JoinColumn(name = "meeting_id"))
        @Column(name = "day")
        private List<Integer> weeklyDays; // List of integers representing days of the week (1-7)

        @Column
        private Integer monthlyDay;

        @Column
        private Integer monthlyWeek;

        @Column
        private Integer monthlyWeekDay;

        // Getters and Setters
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
}
