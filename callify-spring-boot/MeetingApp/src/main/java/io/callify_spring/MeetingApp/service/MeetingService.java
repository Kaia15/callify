package io.callify_spring.MeetingApp.service;

import java.util.*;

import io.callify_spring.MeetingApp.dto.MeetingDTO;
import io.callify_spring.MeetingApp.dto.MeetingDTO.RecurrenceDTO;
import io.callify_spring.MeetingApp.model.Meeting;
import io.callify_spring.MeetingApp.model.MeetingType;
import io.callify_spring.MeetingApp.model.RecurrenceType;
import io.callify_spring.MeetingApp.repository.MeetingRepository;

import java.time.LocalDateTime;

public class MeetingService implements IMeetingService {
    private MeetingRepository meetingRepository;

    public MeetingService(MeetingRepository repository) {
        this.meetingRepository = repository;
    }

    public List<Meeting> getAllMeetings() {
        return this.meetingRepository.findAll();
    };

    // user can be both registrant or attendee
    public List<Meeting> getAllMeetingsByUser(Long userId) {
        return this.meetingRepository.getAllMeetingsByUser(userId);
    }; 
    
    public Meeting getMeetingById(Long meetingId) {
        return this.meetingRepository.findById(meetingId) .orElseThrow(() -> new RuntimeException("Meeting not found with id: " + meetingId));
    };

    // might include registrant id and all remaining attendees' ids
    public List<Long> getAllMeetingAttendeesById(Long meetingId) {
        Meeting foundMeeting = this.getMeetingById(meetingId);
        return foundMeeting.getAttendees();
    }; 

    // in this case, userId is equal to registrantId
    public Meeting createMeetingByUser(MeetingDTO meetingDto) {
        if (meetingDto.getRegistrantId() == null || meetingDto.getTopic() == null) {
            throw new IllegalArgumentException("Registrant ID and Topic are required.");
        }
        
        Meeting newMeeting = new Meeting(meetingDto.getRegistrantId(), meetingDto.getTopic(), meetingDto.getDuration());
        RecurrenceDTO recurrenceFromDto = meetingDto.getRecurrence();
        
        if (recurrenceFromDto == null) {
            throw new IllegalArgumentException("Recurrence details are required.");
        }

        RecurrenceType RType = recurrenceFromDto.getType();
        LocalDateTime STime = recurrenceFromDto.getStartDateTime();
        LocalDateTime now = LocalDateTime.now();

        // create Meeting Object from MeetingDTO
        newMeeting.setMeetingFromDTO(recurrenceFromDto, STime, RType);
        
        // TO-DO: set Meeting Type based on recurrence
        // RecurrenceType Null => Case 1: startDateTime == now, Case 2: startDateTime > now
        if (RType == RecurrenceType.NONE) {
            if (STime == now) 
                newMeeting.setMeetingType(MeetingType.INSTANT);
            else 
                newMeeting.setMeetingType(MeetingType.SCHEDULED);
        } else {
            newMeeting.setMeetingType(MeetingType.RECURRING_FIXED_TIME); // only change if occurrences happen
        }

        return newMeeting;

    }; 

    // fix the meeting time, topic, etc
    public Meeting updateMeeting(MeetingDTO meetingDto) {
        Meeting foundMeeting = this.getMeetingById(meetingDto.getId());
        // TO-DO: set topic and meeting time (3 cases)
        return foundMeeting;
    };

    public void deleteMeetingByUser(Long userId) {

    };

    // add new attendees to existing created meeting
    public void addNewAttendees(Long meetingId, Long userId) {
        // make request to user service
    }

    // public void deleteMeetingRegistrant(Long registrantId) {

    // };

}
