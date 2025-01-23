package io.callify_spring.MeetingApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.callify_spring.MeetingApp.dto.MeetingDTO;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ZoomMeeting {
    private String uuid;
    private long id;
    private String host_id;
    private String host_email;
    private String topic;
    private int type;
    private String status;
    private String timezone;
    private String created_at;
    private String start_url;
    private String join_url;
    private String password;

    @JsonProperty("encrypted_password")
    private String encryptedPassword;

    private List<Occurrence> occurrences;
    private Settings settings;
    private Recurrence recurrence;

    // Getters and setters...

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHost_id() {
        return host_id;
    }

    public void setHost_id(String host_id) {
        this.host_id = host_id;
    }

    public String getHost_email() {
        return host_email;
    }

    public void setHost_email(String host_email) {
        this.host_email = host_email;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getStart_url() {
        return start_url;
    }

    public void setStart_url(String start_url) {
        this.start_url = start_url; // for host to start the meeting
    }

    public String getJoin_url() {
        return join_url; // for meeting attendees to start the meeting
    }

    public void setJoin_url(String join_url) {
        this.join_url = join_url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public List<Occurrence> getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(List<Occurrence> occurrences) {
        this.occurrences = occurrences;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Recurrence getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(Recurrence recurrence) {
        this.recurrence = recurrence;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Occurrence {
        private String occurrence_id;
        private String start_time;
        private int duration;
        private String status;

        // Getters and setters...

        public String getOccurrence_id() {
            return occurrence_id;
        }

        public void setOccurrence_id(String occurrence_id) {
            this.occurrence_id = occurrence_id;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
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

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Settings {
        private boolean host_video;
        private boolean participant_video;
        private boolean cn_meeting;
        private boolean in_meeting;
        private boolean join_before_host;
        private int jbh_time;
        private boolean mute_upon_entry;
        private String audio;
        private String auto_recording;

        // Additional fields from JSON response
        private boolean waiting_room;
        private boolean registrants_email_notification;

        // Getters and setters...

        public boolean isHost_video() {
            return host_video;
        }

        public void setHost_video(boolean host_video) {
            this.host_video = host_video;
        }

        public boolean isParticipant_video() {
            return participant_video;
        }

        public void setParticipant_video(boolean participant_video) {
            this.participant_video = participant_video;
        }

        public boolean isCn_meeting() {
            return cn_meeting;
        }

        public void setCn_meeting(boolean cn_meeting) {
            this.cn_meeting = cn_meeting;
        }

        public boolean isIn_meeting() {
            return in_meeting;
        }

        public void setIn_meeting(boolean in_meeting) {
            this.in_meeting = in_meeting;
        }

        public boolean isJoin_before_host() {
            return join_before_host;
        }

        public void setJoin_before_host(boolean join_before_host) {
            this.join_before_host = join_before_host;
        }

        public int getJbh_time() {
            return jbh_time;
        }

        public void setJbh_time(int jbh_time) {
            this.jbh_time = jbh_time;
        }

        public boolean isMute_upon_entry() {
            return mute_upon_entry;
        }

        public void setMute_upon_entry(boolean mute_upon_entry) {
            this.mute_upon_entry = mute_upon_entry;
        }

        public String getAudio() {
            return audio;
        }

        public void setAudio(String audio) {
            this.audio = audio;
        }

        public String getAuto_recording() {
            return auto_recording;
        }

        public void setAuto_recording(String auto_recording) {
            this.auto_recording = auto_recording;
        }

        public boolean isWaiting_room() {
            return waiting_room;
        }

        public void setWaiting_room(boolean waiting_room) {
            this.waiting_room = waiting_room;
        }

        public boolean isRegistrants_email_notification() {
            return registrants_email_notification;
        }

        public void setRegistrants_email_notification(boolean registrants_email_notification) {
            this.registrants_email_notification = registrants_email_notification;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Recurrence {
        private int type;
        private int repeat_interval;
        private int monthly_day;
        private String end_date_time;

        // Getters and setters...

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getRepeat_interval() {
            return repeat_interval;
        }

        public void setRepeat_interval(int repeat_interval) {
            this.repeat_interval = repeat_interval;
        }

        public int getMonthly_day() {
            return monthly_day;
        }

        public void setMonthly_day(int monthly_day) {
            this.monthly_day = monthly_day;
        }

        public String getEnd_date_time() {
            return end_date_time;
        }

        public void setEnd_date_time(String end_date_time) {
            this.end_date_time = end_date_time;
        }
    }

    public void setZoomMeetingFromDTO(MeetingDTO meetingDto) {
        // Set basic properties
        this.setId(meetingDto.getId() != null ? meetingDto.getId() : 0L);
        this.setTopic(meetingDto.getTopic());
        // this.setDuration(meetingDto.getDuration());
        this.setPassword(meetingDto.getPasscode());
        this.setJoin_url(meetingDto.getJoinUrl());
        this.setCreated_at(meetingDto.getCreatedAt() != null ? meetingDto.getCreatedAt().toString() : null);
    
        // Determine meeting type
        if (meetingDto.getMeetingType() != null) {
            this.setType(meetingDto.getMeetingType() == MeetingType.SCHEDULED ? 2 : 1); // 2 = Scheduled, 1 = Instant
        }
    
        // Set recurrence if applicable
        if (meetingDto.getRecurrence() != null) {
            MeetingDTO.RecurrenceDTO recurrenceDto = meetingDto.getRecurrence();
            Recurrence recurrence = new Recurrence();
            recurrence.setType(recurrenceDto.getType() != null ? recurrenceDto.getType().ordinal() : 0);
            recurrence.setRepeat_interval(recurrenceDto.getRepeatInterval() != null ? recurrenceDto.getRepeatInterval() : 0);
            recurrence.setMonthly_day(recurrenceDto.getMonthlyDay() != null ? recurrenceDto.getMonthlyDay() : 0);
            recurrence.setEnd_date_time(recurrenceDto.getEndDateTime() != null ? recurrenceDto.getEndDateTime().toString() : null);
            this.setRecurrence(recurrence);
        }
    
        // Populate settings if needed
        Settings settings = new Settings();
        settings.setMute_upon_entry(true); // Default to mute upon entry
        settings.setWaiting_room(true);   // Default to enable waiting room
        settings.setHost_video(true);    // Default to host video enabled
        settings.setParticipant_video(true); // Default to participant video enabled
        this.setSettings(settings);
    }
    
}
