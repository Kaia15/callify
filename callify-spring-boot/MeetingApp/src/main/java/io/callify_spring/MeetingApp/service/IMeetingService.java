package io.callify_spring.MeetingApp.service;

import java.util.*;

import io.callify_spring.MeetingApp.dto.MeetingDTO;
import io.callify_spring.MeetingApp.model.Meeting;

public interface IMeetingService {
    public List<Meeting> getAllMeetings();
    public List<Meeting> getAllMeetingsByUser(Long userId); // user can be both registrant or attendee
    public Meeting getMeetingById(Long meetingId);
    public Meeting createMeetingByUser(MeetingDTO meetingDto); // in this case, userId is equal to registrantId
    public Meeting updateMeeting(MeetingDTO meetingDTO);
    public void deleteMeetingByUser(Long userId);
    public List<Long> getAllMeetingAttendeesById(Long meetingId); // might include registrant id and all remaining attendees
    public void addNewAttendees(Long meetingId, Long userId);
    // public void deleteMeetingRegistrant(Long registrantId);
}
