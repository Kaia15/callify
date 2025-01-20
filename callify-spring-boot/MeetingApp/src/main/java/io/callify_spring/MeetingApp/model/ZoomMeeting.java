package io.callify_spring.MeetingApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    // Nested class for settings
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Settings {
        private boolean host_video;
        private boolean participant_video;
        private boolean cn_meeting;
        private boolean in_meeting;
        private boolean join_before_host;
        private int jbh_time;
        private boolean mute_upon_entry;

        // Add more fields as needed
        private String audio;
        private String auto_recording;

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
    }

    private Settings settings;

    // Getters and setters for all fields...
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
        this.start_url = start_url;
    }

    public String getJoin_url() {
        return join_url;
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

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}
