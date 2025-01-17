package io.callify_spring.MeetingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.callify_spring.MeetingApp.model.Meeting;

import java.util.*;

public interface MeetingRepository extends JpaRepository<Meeting,Long> {
    @Query("SELECT m FROM Meeting m WHERE :userId IN elements(m.attendeeIds)")
    List<Meeting> getAllMeetingsByUser(Long userId);
    
}  

