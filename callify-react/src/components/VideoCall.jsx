import React from 'react';

import { ZoomMtg } from '@zoom/meetingsdk';
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
} from "../utils/constants";

ZoomMtg.preLoadWasm();
ZoomMtg.prepareWebSDK();

function VideoCall() {
    function getSignature(e) {
        e.preventDefault();

        fetch(authEndpoint, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                meetingNumber: meetingNumber,
                role: role
            })
        }).then(res => res.json())
            .then(response => {
                startMeeting(response.signature)
            }).catch(error => {
                console.error(error)
            })
    }

    function startMeeting(signature) {
        document.getElementById('zmmtg-root').style.display = 'block'

        ZoomMtg.init({
            leaveUrl: leaveUrl,
            patchJsMedia: true,
            leaveOnPageUnload: true,
            success: (success) => {
                console.log(success)

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
                        console.log(success)
                    },
                    error: (error) => {
                        console.log(error)
                    }
                })

            },
            error: (error) => {
                console.log(error)
            }
        })
    }

    return (
        <div className="VideoCall">
            <main>
                <h1>Zoom Meeting SDK Sample React</h1>

                <button onClick={getSignature}>Join Meeting</button>
            </main>
        </div>
    );
}

export default VideoCall;
