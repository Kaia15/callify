import { useContext,useState } from "react";
import { useUser } from "./useUser";
import { AppContext } from "../context/context";

export function useVideoCall() {
    const {user,setUser} = useUser(); // get User ID
    // const {meeting,setMeeting} = useContext(AppContext);
    const [callId, setCallId] = useState("");

    const generateCallID = async function () {
        // TO-DO: send request to backend to get ID
        // Temporary: generate random string
        const callId = "csb-" + Math.random().toString(16).substring(2);
        setCallId(callId);
    }

    return {callId,generateCallID};
}