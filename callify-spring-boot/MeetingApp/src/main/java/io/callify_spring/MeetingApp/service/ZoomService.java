package io.callify_spring.MeetingApp.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.callify_spring.MeetingApp.model.ZoomMeeting;
import java.util.*;

// @Service
// public class ZoomService {

//     private static final String CLIENT_ID = "r9WkuSjxQUb8_KkOnnfLQ";
//     private static final String CLIENT_SECRET = "O7BicaHjA2p8JerAFxLWtFCDvlkDVurI";
//     private static final String TOKEN_URL = "https://zoom.us/oauth/token";
//     private static final String ZOOM_API_URL = "https://api.zoom.us/v2/users/me/meetings";

//     private final RestTemplate restTemplate;

//     public ZoomService(RestTemplate restTemplate) {
//         this.restTemplate = restTemplate;
//     }

//     // Method to get an Access Token
//     public String getAccessToken() {
//         try {
//             // Encode Client ID and Client Secret
//             String credentials = CLIENT_ID + ":" + CLIENT_SECRET;
//             String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

//             // Set headers
//             HttpHeaders headers = new HttpHeaders();
//             headers.set("Authorization", "Basic " + encodedCredentials);
//             headers.set("Content-Type", "application/x-www-form-urlencoded");

//             // Set request body
//             HttpEntity<String> request = new HttpEntity<>("grant_type=client_credentials", headers);

//             // Make POST request
//             ResponseEntity<String> response = restTemplate.exchange(
//                     TOKEN_URL,
//                     HttpMethod.POST,
//                     request,
//                     String.class
//             );

//             // Extract and return the access token
//             JSONObject responseJson = new JSONObject(response.getBody());
//             return responseJson.getString("access_token");
//         } catch (Exception e) {
//             throw new RuntimeException("Failed to get access token", e);
//         }
//     }

//     // Method to Create a Zoom Meeting
//     public JSONObject createZoomMeeting() {
//         try {
//             // Fetch Access Token
//             String accessToken = getAccessToken();
//             System.out.println(accessToken);

//             // Meeting details
//             JSONObject meetingDetails = new JSONObject();
//             meetingDetails.put("topic", "Test Meeting");
//             meetingDetails.put("type", 1); // Instant meeting
//             meetingDetails.put("duration", 30); // Duration in minutes
//             meetingDetails.put("timezone", "America/Los_Angeles");
//             meetingDetails.put("password", "123456"); // Optional: Set a meeting password

//             // Set headers
//             HttpHeaders headers = new HttpHeaders();
//             headers.set("Authorization", "Bearer " + accessToken);
//             headers.set("Content-Type", "application/json");

//             // Create HTTP entity with meeting details
//             HttpEntity<String> requestEntity = new HttpEntity<>(meetingDetails.toString(), headers);

//             // Make POST request to create a meeting
//             ResponseEntity<String> response = restTemplate.exchange(
//                     ZOOM_API_URL,            // URL
//                     HttpMethod.POST,         // HTTP Method
//                     requestEntity,           // Request Entity
//                     String.class             // Response Type
//             );

//             // Parse and return the response
//             return new JSONObject(response.getBody());
//         } catch (Exception e) {
//             throw new RuntimeException("Error creating Zoom meeting", e);
//         }
//     }
// }
@Service
public class ZoomService {

    private static final String CLIENT_ID = "r9WkuSjxQUb8_KkOnnfLQ";
    private static final String CLIENT_SECRET = "O7BicaHjA2p8JerAFxLWtFCDvlkDVurI";
    private static final String ACCOUNT_ID = "PWu3V64-R6u6OuapyAWGuA";
    private static final String TOKEN_URL = "https://zoom.us/oauth/token?grant_type=account_credentials&account_id=";
    private static final String ZOOM_API_URL = "https://api.zoom.us/v2/users/baotranongtran@gmail.com/meetings";

    private final RestTemplate restTemplate;

    public ZoomService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Method to get Access Token
    public String getAccessToken() {
        try {
            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.setBasicAuth(CLIENT_ID, CLIENT_SECRET); // Automatically encodes credentials in Base64
            headers.set("Content-Type", "application/x-www-form-urlencoded");

            // Make POST request
            ResponseEntity<String> response = restTemplate.exchange(
                    TOKEN_URL + ACCOUNT_ID,
                    HttpMethod.POST,
                    new HttpEntity<>(headers),
                    String.class
            );

            // Parse response to get the access token
            JSONObject responseJson = new JSONObject(response.getBody());
            return responseJson.getString("access_token");
        } catch (Exception e) {
            throw new RuntimeException("Failed to get access token", e);
        }
    }

    // Method to Create a Zoom Meeting
    public ZoomMeeting createZoomMeeting() {
        try {
            // Fetch Access Token
            String accessToken = getAccessToken();

            // Meeting details
            JSONObject meetingDetails = new JSONObject();
            meetingDetails.put("topic", "Test Meeting");
            meetingDetails.put("type", 1); // Instant meeting
            meetingDetails.put("duration", 30); // Duration in minutes
            meetingDetails.put("timezone", "America/Los_Angeles");
            meetingDetails.put("password", "123456"); // Optional: Set a meeting password

            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + accessToken);
            headers.set("Content-Type", "application/json");

            // Create HTTP entity with meeting details
            HttpEntity<String> requestEntity = new HttpEntity<>(meetingDetails.toString(), headers);

            // Make POST request to create a meeting
            ResponseEntity<String> response = restTemplate.exchange(
                ZOOM_API_URL,
                HttpMethod.POST,
                requestEntity,
                String.class
            );

            // Log the response
            // System.out.println("Response Status: " + response.getStatusCode());
            // System.out.println("Response Body: " + response.getBody());

            // Use ObjectMapper to map JSON to ZoomMeeting object
            ObjectMapper objectMapper = new ObjectMapper();
            ZoomMeeting meeting = objectMapper.readValue(response.getBody(), ZoomMeeting.class);
            return meeting;

        } catch (Exception e) {
            throw new RuntimeException("Error creating Zoom meeting", e);
        }
    }
}
