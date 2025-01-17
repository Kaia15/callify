package io.callify_spring.UserApp.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.callify_spring.UserApp.dto.UserDTO;
import io.callify_spring.UserApp.model.User;
import io.callify_spring.UserApp.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.MediaType; // For setting content type (e.g., application/json)
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserService userService;
    private final RestTemplate restTemplate;

    public UserController(UserService service, RestTemplate restTemplate) {
        this.userService = service;
        this.restTemplate = restTemplate;
    }

    // public UserController(UserService service) {
    //     this.userService = service;
    // }

    @GetMapping // get all users with & without pagination
    public List<User> getAllUsers(
        @RequestParam(required = false) Integer offset,
        @RequestParam(required = false) Integer pageNum) {
        if (offset != null && pageNum != null) {
            return userService.getUsersByPage(offset, pageNum);
        }
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@Valid @RequestBody UserDTO userDto) {
        return this.userService.createUser(userDto.getEmail(),userDto.getFirstName(),userDto.getLastName(),userDto.getPassword());
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return this.userService.getUserById(userId);
    }

    @PatchMapping("/{userId}")
    public User modifyUserById(@PathVariable Long userId, @Valid @RequestBody UserDTO userDto) {
        System.out.println(userId);
        return this.userService.updateUser(userId, userDto);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserById(@PathVariable Long userId) {
        this.userService.deleteUserById(userId);;
    }

    // @PostMapping("/{userId}/meetings")
    // public Long createMeetingByUser(@PathVariable Long userId) {
    //     // TO-DO: make request to meetingService
    //     String url = "http://localhost:8081/api/v1/meetings";

    //     // mock request body - need to replace 123 w. userId
    //     String requestBody = "{\n" +
    //         "  \"id\": 123,\n" +
    //         "  \"joinUrl\": \"https://meeting.com/join/12345\",\n" +
    //         "  \"createdAt\": \"2025-01-15T10:00:00\",\n" +
    //         "  \"meetingType\": \"SCHEDULED\",\n" +
    //         "  \"duration\": 60,\n" +
    //         "  \"passcode\": \"abc123\",\n" +
    //         "  \"recurrence\": {\n" +
    //         "    \"type\": \"DAILY\",\n" +
    //         "    \"repeatInterval\": 1,\n" +
    //         "    \"startDateTime\": \"2025-01-15T10:00:00\",\n" +
    //         "    \"endDateTime\": \"2025-02-15T10:00:00\",\n" +
    //         "    \"endTimes\": 10,\n" +
    //         "    \"weeklyDays\": [1, 3, 5],\n" +
    //         "    \"monthlyDay\": 15,\n" +
    //         "    \"monthlyWeek\": 2,\n" +
    //         "    \"monthlyWeekDay\": 3\n" +
    //         "  },\n" +
    //         "  \"attendeesIds\": [234, 345, 456, 567]\n" +
    //         "}";


    //     // FIX: mismatch type, correct type is Meeting
    //     ResponseEntity<Long> response = this.restTemplate.postForEntity(url, requestBody, Long.class);
    //     Long responseId = response.getBody();
    //     return responseId;
    // }
    
    @PostMapping("/{userId}/meetings")
    public Long createMeetingByUser(@PathVariable Long userId) {
        // Hard-coded request body with userId dynamically replaced
        String requestBody = "{\n" +
            "  \"joinUrl\": \"https://meeting.com/join/12345\",\n" +
            "  \"registrantId\": " + userId + ",\n" +
            "  \"createdAt\": \"" + LocalDateTime.now() + "\",\n" +
            "  \"meetingType\": \"SCHEDULED\",\n" +
            "  \"topic\": \"Team Meeting\",\n" +
            "  \"duration\": 60,\n" +
            "  \"passcode\": \"abc123\",\n" +
            "  \"recurrence\": {\n" +
            "    \"type\": \"DAILY\",\n" +
            "    \"repeatInterval\": 1,\n" +
            "    \"startDateTime\": \"" + LocalDateTime.now() + "\",\n" +
            "    \"endDateTime\": \"" + LocalDateTime.now().plusDays(30) + "\",\n" +
            "    \"endTimes\": 10,\n" +
            "    \"weeklyDays\": [1, 3, 5],\n" +
            "    \"monthlyDay\": 15,\n" +
            "    \"monthlyWeek\": 2,\n" +
            "    \"monthlyWeekDay\": 3\n" +
            "  },\n" +
            "  \"attendeesIds\": [1]\n"+
            "}";

        // Set the headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the HttpEntity with the hard-coded JSON body
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        // Send the POST request
        String url = "http://localhost:8081/api/v1/meetings";
        ResponseEntity<Long> response = this.restTemplate.postForEntity(url, entity, Long.class);

        // Return the response ID
        return response.getBody();
    }

    @GetMapping("/{userId}/meetings")
    public List<Long> getMeetingsByUser(@PathVariable Long userId) {
        String url = "http://localhost:8081/api/v1/meetings?userId=" + userId;

        // Use exchange with a ParameterizedTypeReference to specify the type
        ResponseEntity<List<Long>> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Long>>() {}
        );

        // Return the List<Long> directly from the response body
        return response.getBody();
    }

}

