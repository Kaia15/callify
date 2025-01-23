package io.callify_spring.MeetingApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.callify_spring.MeetingApp.model.Meeting;

import java.util.*;

public interface MeetingRepository extends JpaRepository<Meeting,Long> {
    @Query("SELECT m.id " +
       "FROM Meeting m " +
       "WHERE :userId IN elements(m.attendeeIds) " +
       "   OR m.registrantId = :userId")
    List<Long> getAllMeetingsByUser(@Param("userId") Long userId);

    
}  

