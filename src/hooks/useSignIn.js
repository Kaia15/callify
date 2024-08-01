import { useContext } from "react";
import { AppContext } from "../context/context";
import { useState } from "react";

export function useSignIn() {
    const {isSignedIn, setIsSignedIn, user, setUser} = useContext(AppContext);
    const [email, setEmail] = useState("");
    const [password,setPassword] = useState("");
    const [verificationCode, setVerificationCode] = useState("");
    const signIn = async function () {
        // TO-DO: send request to backend
    }

    return {email,password,signIn,isSignedIn,setEmail,setPassword,user,verificationCode,setVerificationCode}
}