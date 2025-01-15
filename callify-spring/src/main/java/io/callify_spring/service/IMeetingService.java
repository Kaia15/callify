package io.callify_spring.service;

import java.util.*;
import io.callify_spring.model.Meeting;
import io.callify_spring.model.User;

public interface IMeetingService {
    public List<Meeting> getAllMeetings();
    public List<Meeting> gettAllMeetingsByUser(Long userId);
    public Meeting getMeetingById(Long meetingId);
    public Meeting createMeetingByUser(Long userId); // should we create a base meeting then extend this class to fit all meeting types?
    public Meeting updateMeeting(Long meetingId);
    public void deleteMeetingByUser(Long userId);
    public List<User> getAllMeetingRegistrantsById(Long meetingId);
    public void deleteMeetingRegistrant(Long registrantId);
}
