package io.callify_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.callify_spring.model.Meeting;
import java.util.*;

public interface MeetingRepository extends JpaRepository<Meeting,Long> {
    @Query("SELECT m FROM Meeting m JOIN m.attendees a WHERE a.id = :userId")
    List<Meeting> getAllMeetingsByUser(Long userId);
    
}  

