package io.callify_spring.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Meeting {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column
    private String joinUrl;

    @Column
    private Long registrantId;

    @Column
    private LocalDateTime startTime;

    @Column
    private MeetingType meetingType;

    @Column
    private String topic;

    @Column
    private int duration;

    @Column
    private String passcode; // auto-generate

}
