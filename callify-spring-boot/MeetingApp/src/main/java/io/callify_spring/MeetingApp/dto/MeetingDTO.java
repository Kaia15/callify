package io.callify_spring.MeetingApp.dto;

import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import io.callify_spring.MeetingApp.model.MeetingType;
import io.callify_spring.MeetingApp.model.RecurrenceType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MeetingDTO {

    private Long id;
    private String joinUrl;
    private Long registrantId;
    private LocalDateTime createdAt;
    private MeetingType meetingType;
    private String topic;
    private int duration;
    private String passcode;
    private RecurrenceDTO recurrence;
    private List<Long> attendeesIds;

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

    // Nested RecurrenceDTO Class
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

    // Convert MeetingDTO to JSONObject
    public JSONObject convertToJson() {
        JSONObject json = new JSONObject();

        // Top-level fields
        json.put("id", this.getId());
        json.put("joinUrl", this.getJoinUrl());
        json.put("registrantId", this.getRegistrantId());
        json.put("createdAt", this.getCreatedAt() != null 
            ? this.getCreatedAt().format(DateTimeFormatter.ISO_DATE_TIME) 
            : null);
        json.put("meetingType", this.getMeetingType() != null 
            ? this.getMeetingType().name() 
            : null);
        json.put("topic", this.getTopic());
        json.put("duration", this.getDuration());
        json.put("passcode", this.getPasscode());

        // Recurrence
        if (this.getRecurrence() != null) {
            JSONObject recurrenceJson = new JSONObject();
            MeetingDTO.RecurrenceDTO recurrenceDTO = this.getRecurrence();

            recurrenceJson.put("type", recurrenceDTO.getType() != null ? recurrenceDTO.getType().name() : null);
            recurrenceJson.put("repeatInterval", recurrenceDTO.getRepeatInterval());
            recurrenceJson.put("startDateTime", recurrenceDTO.getStartDateTime() != null 
                ? recurrenceDTO.getStartDateTime().format(DateTimeFormatter.ISO_DATE_TIME) 
                : null);
            recurrenceJson.put("endDateTime", recurrenceDTO.getEndDateTime() != null 
                ? recurrenceDTO.getEndDateTime().format(DateTimeFormatter.ISO_DATE_TIME) 
                : null);
            recurrenceJson.put("endTimes", recurrenceDTO.getEndTimes());
            recurrenceJson.put("weeklyDays", recurrenceDTO.getWeeklyDays());
            recurrenceJson.put("monthlyDay", recurrenceDTO.getMonthlyDay());
            recurrenceJson.put("monthlyWeek", recurrenceDTO.getMonthlyWeek());
            recurrenceJson.put("monthlyWeekDay", recurrenceDTO.getMonthlyWeekDay());

            json.put("recurrence", recurrenceJson);
        }

        // Attendees
        json.put("attendeesIds", this.getAttendeesIds() != null ? new JSONArray(this.getAttendeesIds()) : new JSONArray());

        return json;
    }
}
