package io.callify_spring.MeetingApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.callify_spring.MeetingApp.dto.MeetingDTO;
import io.callify_spring.MeetingApp.model.Meeting;
import io.callify_spring.MeetingApp.service.MeetingService;
import io.callify_spring.MeetingApp.service.ZoomService;

import java.util.*;

import io.callify_spring.MeetingApp.model.UserInfo;
import io.callify_spring.MeetingApp.model.ZoomMeeting;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/meetings")
public class MeetingController {
    private MeetingService meetingService;
    private final RestTemplate restTemplate;
    private ZoomService zoomService;
    
    public MeetingController(MeetingService service,RestTemplate restTemplate, ZoomService zoomService) {
        this.meetingService = service;
        this.restTemplate = restTemplate;
        this.zoomService = zoomService;
    }

    @GetMapping
    public List<?> getMeetings(@RequestParam(required = false) Long userId) {
        if (userId != null) {
            // If userId is provided, fetch meetings for that user
            return this.meetingService.getAllMeetingsByUser(userId);
        } else {
            // Otherwise, fetch all meetings
            return this.meetingService.getAllMeetings();
        }
    }

    @GetMapping("/{meetingId}")
    public Meeting getMeetingById(@PathVariable Long meetingId) {
        return this.meetingService.getMeetingById(meetingId);
    }

    @PostMapping
    public ResponseEntity<Long> createMeetingByUser(@RequestBody MeetingDTO meetingDto) {
        if (meetingDto.getRecurrence() == null) {
            throw new IllegalArgumentException("Recurrence details are required.");
        }

        // Make call to Zoom Service 
        ZoomMeeting zoomMeeting = this.zoomService.createZoomMeeting(meetingDto);

        // Forward to the service layer
        Meeting meeting = meetingService.createMeetingByUser(zoomMeeting);

        // Return the meeting ID
        return ResponseEntity.ok(meeting.getId());
    }

    @GetMapping("/{meetingId}/attendees")
    public List<Long> getAllMeetingAttendeesById(@PathVariable Long meetingId) {
        return this.meetingService.getAllMeetingAttendeesById(meetingId);
    }

    @GetMapping("/{meetingId}/attendees/{attendeeId}")
    public UserInfo getAttendeeInfoByMeetingId(@PathVariable Long meetingId, @PathVariable Long attendeeId) {
        // Retrieve the list of attendee IDs for the meeting
        List<Long> attendees = this.meetingService.getAllMeetingAttendeesById(meetingId);

        // Check if the attendeeId is in the list
        if (attendees.contains(attendeeId)) {
            // Build the URL for the user service
            String url = "http://localhost:8080/api/v1/users/" + attendeeId;

            try {
                // Call the user service to fetch UserInfo
                ResponseEntity<UserInfo> response = this.restTemplate.getForEntity(url, UserInfo.class);

                // Extract the UserInfo object from the response body
                return response.getBody();
            } catch (Exception ex) {
                // Handle errors gracefully (e.g., user service unavailable or user not found)
                throw new RuntimeException("Failed to retrieve user information: " + ex.getMessage(), ex);
            }
        } else {
            // If the attendeeId is not in the list, throw an exception or return an appropriate response
            throw new IllegalArgumentException("Attendee ID " + attendeeId + " is not part of meeting ID " + meetingId);
        }
    }

    @GetMapping("/zoom/test")
    public ZoomMeeting createMeetingByZoom(MeetingDTO meetingDto) {
        ZoomMeeting newZoomMeeting = this.zoomService.createZoomMeeting(meetingDto);
        return newZoomMeeting;
    }
}
