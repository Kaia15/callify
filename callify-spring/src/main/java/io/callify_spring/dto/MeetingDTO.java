package io.callify_spring.dto;

import io.callify_spring.model.Meeting;
import io.callify_spring.model.MeetingType;
import java.util.*;
import java.time.LocalDateTime;
import io.callify_spring.model.RecurrenceType;

public class MeetingDTO {

    private Long id;
    private String joinUrl;
    private Long registrantId; // This can be used to store the user ID when creating a meeting
    private LocalDateTime createdAt;
    private MeetingType meetingType; // This will be set to SCHEDULED by default
    private String topic;
    private int duration;
    private String passcode; // Auto-generate or user-provided
    private RecurrenceDTO recurrence;
    private List<Long> attendeesIds; // This can store a list of user IDs for attendees

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public RecurrenceDTO getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(RecurrenceDTO recurrence) {
        this.recurrence = recurrence;
    }

    public List<Long> getAttendeesIds() {
        return attendeesIds;
    }

    public void setAttendeesIds(List<Long> attendeesIds) {
        this.attendeesIds = attendeesIds;
    }

    // RecurrenceDTO nested class

    public static class RecurrenceDTO {
        private RecurrenceType type;
        private Integer repeatInterval;
        private LocalDateTime startDateTime;
        private LocalDateTime endDateTime;
        private Integer endTimes;
        private List<Integer> weeklyDays;
        private Integer monthlyDay;
        private Integer monthlyWeek;
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
