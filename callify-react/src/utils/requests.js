// zoom-api.js (Frontend Only)

import CryptoJS from 'crypto-js';
import { ZoomMtg } from "@zoom/meetingsdk";

// Zoom API credentials (should ideally be stored securely)
const API_KEY = 'ShwzewRtT4Rs9OyeViU9w';
const API_SECRET = 't8N0q74L0B2nXBknDNvR547WoUyTgkia';

// Function to generate JWT token manually
const generateJwtToken = () => {
    const header = {
        alg: 'HS256',
        typ: 'JWT'
    };

    const payload = {
        iss: API_KEY,
        exp: Math.floor(Date.now() / 1000) + 3600, // Token expires in 1 hour
    };

    const base64UrlEncode = (obj) => {
        return btoa(JSON.stringify(obj)).replace(/\+/g, '-').replace(/\//g, '_').replace(/=+$/, '');
    };

    const base64UrlDecode = (str) => {
        return JSON.parse(atob(str.replace(/-/g, '+').replace(/_/g, '/')));
    };

    const encode = (obj) => {
        return base64UrlEncode(obj);
    };

    const headerStr = encode(header);
    const payloadStr = encode(payload);

    const signature = CryptoJS.HmacSHA256(`${headerStr}.${payloadStr}`, API_SECRET).toString(CryptoJS.enc.Base64);
    const signatureStr = base64UrlEncode(signature);

    return `${headerStr}.${payloadStr}.${signatureStr}`;
};

// Generate a Zoom SDK signature using crypto-js
const generateSignature = (meetingNumber) => {
    const timestamp = new Date().getTime() - 30000; // Time in milliseconds
    const msg = `${API_KEY}${meetingNumber}${timestamp}${API_SECRET}`;
    const hash = CryptoJS.HmacSHA256(msg, API_SECRET).toString(CryptoJS.enc.Base64);
    return CryptoJS.enc.Base64.stringify(CryptoJS.enc.Utf8.parse(`${API_KEY}.${meetingNumber}.${timestamp}.${hash}`));
};

// Create a meeting using Zoom API
export const createMeeting = async () => {
    try {
        const token = generateJwtToken(); // Generate JWT token

        const response = await fetch('https://api.zoom.us/v2/users/me/meetings', {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                topic: 'Test Meeting',
                type: 1, // Instant meeting
                duration: 30, // Duration in minutes
                timezone: 'America/Los_Angeles',
                password: '123456' // Optional: Set a meeting password
            })
        });

        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error creating meeting:', error);
        throw error;
    }
};

// Join a meeting using Zoom SDK
export const joinMeeting = (meetingNumber, userName, password) => {
    const signature = generateSignature(meetingNumber);

    ZoomMtg.preLoadWasm();
    ZoomMtg.prepareWebSDK();

    ZoomMtg.init({
        leaveUrl: 'http://www.zoom.us',
        isSupportAV: true,
        success: () => {
            ZoomMtg.join({
                meetingNumber,
                userName,
                signature,
                apiKey: API_KEY,
                passWord: password,
                success: (res) => {
                    console.log('Join meeting success');
                },
                error: (res) => {
                    console.error('Error joining meeting:', res);
                },
            });
        },
        error: (res) => {
            console.error('Error initializing Zoom SDK:', res);
        },
    });
};
