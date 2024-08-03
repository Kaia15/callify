import React, { useEffect, useState } from "react";
import {
    Call,
    CallControls,
    StreamCall,
    StreamTheme,
    StreamVideo,
    SpeakerLayout,
    StreamVideoClient
} from "@stream-io/video-react-sdk";
import "@stream-io/video-react-sdk/dist/css/styles.css";
import { useNavigate } from "react-router-dom";
import { useVideoCall } from "../hooks/useVideoCall";
// import "./styles.css";

// Helper function to generate a random user ID and save it in local storage
const getOrCreateUserId = () => {
    let userId = localStorage.getItem("userId");
    if (!userId) {
        userId = "csb-user-" + Math.random().toString(16).substring(2);
        localStorage.setItem("userId", userId);
    }
    return userId;
};

const user_id = getOrCreateUserId();
const user = { id: user_id };

const apiKey = "mmhfdzb5evj2";
const tokenProvider = async () => {
    const { token } = await fetch(
        "https://pronto.getstream.io/api/auth/create-token?" +
        new URLSearchParams({
            api_key: apiKey,
            user_id: user_id
        })
        // `https://pronto.getstream.io/api/auth/create-token?api_key=${apiKey}&user_id=${user.id}`
    ).then((res) => res.json());
    return token;
};

export default function VideoCall({callId}) {
    const [client, setClient] = useState(null);
    const [call, setCall] = useState(null);
    // const {callId} = useVideoCall();
    
    const navigate = useNavigate();

    // useEffect(() => {
    //     // Fetch the call ID from the backend
    //     const postCall = async () => {
    //         // TO-DO: call backend server to generate "Call" object that has Call ID
    //         // Temporary:
    //         const data = {_id: 'csb-' + Math.random().toString(16).substring(2)};
    //         setCallId(data._id);
    //     };

    //     postCall();
    // }, []);

    useEffect(() => {
        if (!callId) return;

        const myClient = new StreamVideoClient({ apiKey, user, tokenProvider });
        setClient(myClient);
        return () => {
            myClient.disconnectUser();
            setClient(null);
        };
    }, [callId]);

    useEffect(() => {
        if (!client || !callId) return;
        const myCall = client.call("default", callId);
        myCall.join({ create: true }).catch((err) => {
            console.error("Failed to join the call", err);
        });

        setCall(myCall);

        // return () => {
        //     setCall(null);
        //     myCall.leave().catch((err) => {
        //         // TO-DO: before leaving meeting, call backend to update "Call" object with updated duration,endTime, & participants.
        //         navigate("/");
        //         console.error("Failed to leave the call", err);
        //     });
        // };
    }, [client, callId]);

    console.log(call?.participants);

    if (!client || !call) return null;

    return (
        <StreamVideo client={client}>
            <StreamTheme className="my-theme-overrides">
                <StreamCall call={call}>
                    <SpeakerLayout />
                    <CallControls onLeave={() => navigate('/')} />
                </StreamCall>
            </StreamTheme>
        </StreamVideo>
    );
}
