package io.callify_spring.MeetingApp.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.json.JSONObject;

@Service
public class ZoomService {

    private static final String API_KEY = "ShwzewRtT4Rs9OyeViU9w"; // Replace with your Zoom API Key
    private static final String API_SECRET = "t8N0q74L0B2nXBknDNvR547WoUyTgkia"; // Replace with your Zoom API Secret
    private static final String ZOOM_API_URL = "https://api.zoom.us/v2/users/me/meetings";

    private final RestTemplate restTemplate;

    public ZoomService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Helper to Base64 URL encode a string
    private static String base64UrlEncode(byte[] bytes) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    // Helper to generate HMAC SHA256 signature
    private static String hmacSha256(String data, String secret) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
        return Base64.getEncoder().encodeToString(mac.doFinal(data.getBytes(StandardCharsets.UTF_8)));
    }

    // Generate JWT token manually
    public static String generateJwtToken() {
        try {
            // Create header and payload
            JSONObject header = new JSONObject();
            header.put("alg", "HS256");
            header.put("typ", "JWT");

            JSONObject payload = new JSONObject();
            payload.put("iss", API_KEY);
            payload.put("exp", (System.currentTimeMillis() / 1000) + 3600); // Token valid for 1 hour

            // Encode header and payload
            String encodedHeader = base64UrlEncode(header.toString().getBytes(StandardCharsets.UTF_8));
            String encodedPayload = base64UrlEncode(payload.toString().getBytes(StandardCharsets.UTF_8));

            // Create signature
            String signatureInput = encodedHeader + "." + encodedPayload;
            String signature = hmacSha256(signatureInput, API_SECRET);

            // Return full JWT token
            return encodedHeader + "." + encodedPayload + "." + base64UrlEncode(signature.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Error generating JWT token", e);
        }
    }

    // Create a Zoom meeting
    public JSONObject createZoomMeeting() {
        try {
            // Generate JWT token
            String jwtToken = generateJwtToken();
            System.out.println("Generated JWT Token: " + jwtToken);

            // Create request body as a JSONObject
            JSONObject meetingDetails = new JSONObject();
            meetingDetails.put("topic", "Test Meeting");
            meetingDetails.put("type", 1); // Instant meeting
            meetingDetails.put("duration", 30); // Duration in minutes
            meetingDetails.put("timezone", "America/Los_Angeles");
            meetingDetails.put("password", "123456"); // Optional: Set a meeting password

            // Set up headers
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + jwtToken);
            headers.set("Content-Type", "application/json");

            // Wrap the request body and headers into an HttpEntity
            HttpEntity<String> requestEntity = new HttpEntity<>(meetingDetails.toString(), headers);

            // Make the POST request
            ResponseEntity<String> response = restTemplate.exchange(
                    ZOOM_API_URL,            // URL
                    HttpMethod.POST,         // HTTP Method
                    requestEntity,           // Request Entity
                    String.class             // Response Type
            );

            // Parse the response body to JSONObject and return
            return new JSONObject(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating Zoom meeting", e);
        }
    }
}
