import axios from "axios";
import qs from "qs";
import { Buffer } from 'buffer'; // Still need this for base64 encoding/decoding
import HmacSHA256 from 'crypto-js/hmac-sha256';
import Base64 from 'crypto-js/enc-base64';

const clientId = "bUyNiWeqRqWWeHEGBYLHDA";
const clientSecret = "1ehefwuORblEE5HfetfMKC3XoqjRzquJ";

const getAccessToken = async function () {
    const tokenUrl = "https://zoom.us/oauth/token";
    const auth = Buffer.from(`${clientId}:${clientSecret}`).toString("base64");

    try {
        const response = await axios.post(
            tokenUrl,
            qs.stringify({
                grant_type: "client_credentials",
            }),
            {
                headers: {
                    Authorization: `Basic ${auth}`,
                    "Content-Type": "application/x-www-form-urlencoded",
                },
            }
        );
        return { accessToken: response.data.access_token };
    } catch (error) {
        console.error("Error fetching access token:", error);
        throw error;
    }
};

const createMeeting = async function (payload) {
    const { accessToken, userId } = payload;

    try {
        const response = await axios.post(
            `https://api.zoom.us/v2/users/${userId}/meetings`,
            {
                topic: 'Test Meeting',
                type: 1, // Instant meeting
            },
            {
                headers: {
                    'Authorization': `Bearer ${accessToken}`,
                    'Content-Type': 'application/json',
                },
            }
        );

        return { meetingNumber: response.data.id };
    } catch (error) {
        console.error('Error creating meeting:', error);
        throw error;
    }
};

const retrieveUser = async function (payload) {
    const { email, accessToken } = payload;
    try {
        const response = await axios.get(`https://api.zoom.us/v2/users/${email}`, {
            headers: {
                'Authorization': `Bearer ${accessToken}`,
            },
        });

        return response.data;
    } catch (error) {
        console.error('Error fetching user info:', error);
        throw error;
    }
};

// Helper function to generate a Zoom signature
const generateSignature = (meetingNumber, apiKey, apiSecret) => {
    const timestamp = new Date().getTime() - 30000; // Time in milliseconds
    const msg = Buffer.from(apiKey + meetingNumber + timestamp + apiSecret).toString('base64');
    const hash = HmacSHA256(msg, apiSecret);
    const hashInBase64 = Base64.stringify(hash);
    return Buffer.from(`${apiKey}.${meetingNumber}.${timestamp}.${hashInBase64}`).toString('base64');
};

export const getSignature = async (email) => {
    try {
        // Step 1: Get Access Token
        const { accessToken } = await getAccessToken();

        // Step 2: Retrieve User ID
        const user = await retrieveUser({ email, accessToken });
        const userId = user.id;

        // Step 3: Create Meeting
        const { meetingNumber } = await createMeeting({ accessToken, userId });

        // Step 4: Generate Signature
        const apiKey = clientId;
        const apiSecret = clientSecret;
        const signature = generateSignature(meetingNumber, apiKey, apiSecret);

        return {
            meetingNumber,
            signature,
            userId,
        };
    } catch (error) {
        console.error('Error in getSignature:', error);
        throw error;
    }
};
