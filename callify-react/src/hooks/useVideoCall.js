import { useUser } from "./useUser";
import {
    authEndpoint,
    sdkKey,
    meetingNumber,
    passWord,
    role,
    userName,
    userEmail,
    registrantToken,
    zakToken,
    leaveUrl
} from "../utils/constants.js";
import { ZoomMtg } from '@zoom/meetingsdk';

export function useVideoCall() {
    const { user, setUser } = useUser(); // get User ID

    function getSignature() {
        return fetch(authEndpoint, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                meetingNumber: meetingNumber,
                role: role
            })
        }).then(res => res.json())
            .then(response => response.signature)
            .catch(error => {
                console.error(error);
                throw error;
            });
    }

    function startMeeting(signature) {
        document.getElementById('zmmtg-root').style.display = 'block'

        ZoomMtg.init({
            leaveUrl: leaveUrl,
            patchJsMedia: true,
            leaveOnPageUnload: true,
            success: (success) => {
                console.log(success);

                ZoomMtg.join({
                    signature: signature,
                    sdkKey: sdkKey,
                    meetingNumber: meetingNumber,
                    passWord: passWord,
                    userName: userName,
                    userEmail: userEmail,
                    tk: registrantToken,
                    zak: zakToken,
                    success: (success) => {
                        console.log(success);
                    },
                    error: (error) => {
                        console.log(error);
                    }
                });
            },
            error: (error) => {
                console.log(error);
            }
        });
    }

    return { getSignature, authEndpoint, startMeeting };
}
