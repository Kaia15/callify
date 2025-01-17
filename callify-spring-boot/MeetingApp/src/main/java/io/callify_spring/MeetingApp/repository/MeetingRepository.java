package io.callify_spring.MeetingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.callify_spring.MeetingApp.model.Meeting;

import java.util.*;

public interface MeetingRepository extends JpaRepository<Meeting,Long> {
    @Query("SELECT m.id \n" + //
                "FROM Meeting m \n" + //
                "WHERE :userId IN elements(m.attendeeIds) \n" + //
                "   OR m.registrantId = :userId")
    List<Long> getAllMeetingsByUser(Long userId);
    
}  

