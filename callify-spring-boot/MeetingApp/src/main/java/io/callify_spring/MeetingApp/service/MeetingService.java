package io.callify_spring.MeetingApp.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.callify_spring.MeetingApp.model.Meeting;
import io.callify_spring.MeetingApp.model.ZoomMeeting;
import io.callify_spring.MeetingApp.repository.MeetingRepository;

@Service
public class MeetingService implements IMeetingService {

    @Autowired
    private MeetingRepository meetingRepository;

    public MeetingService(MeetingRepository repository) {
        this.meetingRepository = repository;
    }

    public List<Meeting> getAllMeetings() {
        return this.meetingRepository.findAll();
    };

    // user can be both registrant or attendee
    public List<Long> getAllMeetingsByUser(Long userId) {
        return this.meetingRepository.getAllMeetingsByUser(userId);
    }; 
    
    public Meeting getMeetingById(Long meetingId) {
        return this.meetingRepository.findById(meetingId) .orElseThrow(() -> new RuntimeException("Meeting not found with id: " + meetingId));
    };

    // might include registrant id and all remaining attendees' ids
    public List<Long> getAllMeetingAttendeesById(Long meetingId) {
        Meeting foundMeeting = this.getMeetingById(meetingId);
        return foundMeeting.getAttendeeIds();
    }; 

    // in this case, userId is equal to registrantId
    public Meeting createMeetingByUser(ZoomMeeting zoomMeeting) {
        System.out.println("processing meeting...");

        Meeting newMeeting = new Meeting();
        newMeeting.setMeetingFromZoomMeeting(zoomMeeting);

        this.meetingRepository.save(newMeeting);

        return newMeeting;

    }; 

    // fix the meeting time, topic, etc
    // public Meeting updateMeeting(MeetingDTO meetingDto) {
    //     Meeting foundMeeting = this.getMeetingById(meetingDto.getId());

    //     RecurrenceDTO recurrenceFromDto = meetingDto.getRecurrence();

    //     if (recurrenceFromDto == null) {
    //         throw new IllegalArgumentException("Recurrence details are required.");
    //     }
        
    //     foundMeeting.updateMeetingFromDTO(meetingDto, recurrenceFromDto);

    //     return foundMeeting;
        
    // };

    public void deleteMeetingByUser(Long userId) {

    };

    // add new attendees to existing created meeting
    public void addNewAttendees(Long meetingId, Long userId) {
        // make request to user service
    }

    // public void deleteMeetingRegistrant(Long registrantId) {

    // };

}
