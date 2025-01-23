package io.callify_spring.MeetingApp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "meetings")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String joinUrl;

    @Column
    private String startUrl;

    @Column
    private String hostId;

    @Column
    private String hostEmail;

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

    @Column
    private Long registrantId;

    public Long getRegistrantId() {
        return registrantId;
    }

    public void setRegistrantId(Long registrantId) {
        this.registrantId = registrantId;
    }


    @Embedded
    private Recurrence recurrence;

    @ElementCollection
    @CollectionTable(name = "meeting_attendee_ids", joinColumns = @JoinColumn(name = "meeting_id"))
    @Column(name = "attendee_id")
    private List<Long> attendeeIds;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "meeting_id")
    private List<Occurrence> occurrences = new ArrayList<>();

    // Constructors
    public Meeting() {}

    public Meeting(Long userId, String topic, int duration) {
        this.hostId = String.valueOf(userId); // Using userId as hostId
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

    public String getStartUrl() {
        return startUrl;
    }

    public void setStartUrl(String startUrl) {
        this.startUrl = startUrl;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getHostEmail() {
        return hostEmail;
    }

    public void setHostEmail(String hostEmail) {
        this.hostEmail = hostEmail;
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

    public List<Occurrence> getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(List<Occurrence> occurrences) {
        this.occurrences = occurrences;
    }

    // Method to set Meeting from ZoomMeeting
    public void setMeetingFromZoomMeeting(ZoomMeeting zoomMeeting) {
        if (zoomMeeting != null) {
            // this.setId(zoomMeeting.getId());
            this.setTopic(zoomMeeting.getTopic());
            this.setDuration(30);
            this.setPasscode(zoomMeeting.getPassword());
            this.setJoinUrl(zoomMeeting.getJoin_url());
            this.setStartUrl(zoomMeeting.getStart_url());
            this.setHostId(zoomMeeting.getHost_id());
            this.setHostEmail(zoomMeeting.getHost_email());
            this.setCreatedAt(zoomMeeting.getCreated_at() != null
                ? LocalDateTime.parse(zoomMeeting.getCreated_at()) : LocalDateTime.now());
            this.setMeetingType(zoomMeeting.getType() == 8 ? 
                MeetingType.RECURRING_FIXED_TIME : MeetingType.SCHEDULED);

            // Handle recurrence
            if (zoomMeeting.getRecurrence() != null) {
                Recurrence recurrence = new Recurrence();

                int rType = zoomMeeting.getRecurrence().getType();
                if (rType == 3)
                    recurrence.setType(RecurrenceType.MONTHLY);
                else if (rType == 2)
                    recurrence.setType(RecurrenceType.WEEKLY);
                else if (rType == 1)
                    recurrence.setType(RecurrenceType.DAILY);
                else 
                    recurrence.setType(RecurrenceType.NONE);

                recurrence.setRepeatInterval(zoomMeeting.getRecurrence().getRepeat_interval());
                recurrence.setMonthlyDay(zoomMeeting.getRecurrence().getMonthly_day());
                recurrence.setEndDateTime(zoomMeeting.getRecurrence().getEnd_date_time() != null
                    ? LocalDateTime.parse(zoomMeeting.getRecurrence().getEnd_date_time()) : null);
                this.setRecurrence(recurrence);
            }

            // Handle occurrences
            if (zoomMeeting.getOccurrences() != null) {
                List<Occurrence> occurrenceList = new ArrayList<>();
                for (ZoomMeeting.Occurrence zoomOccurrence : zoomMeeting.getOccurrences()) {
                    Occurrence occurrence = new Occurrence();
                    occurrence.setOccurrenceId(zoomOccurrence.getOccurrence_id());
                    occurrence.setStartTime(LocalDateTime.parse(zoomOccurrence.getStart_time()));
                    occurrence.setDuration(zoomOccurrence.getDuration());
                    occurrence.setStatus(zoomOccurrence.getStatus());
                    occurrenceList.add(occurrence);
                }
                this.setOccurrences(occurrenceList);
            }
        }
    }

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


    // Embedded Occurrence Class
    @Entity
    @Table(name = "occurrences")
    public static class Occurrence {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @Column
        private String occurrenceId;

        @Column
        private LocalDateTime startTime;

        @Column
        private int duration;

        @Column
        private String status;

        // Getters and Setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getOccurrenceId() {
            return occurrenceId;
        }

        public void setOccurrenceId(String occurrenceId) {
            this.occurrenceId = occurrenceId;
        }

        public LocalDateTime getStartTime() {
            return startTime;
        }

        public void setStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
