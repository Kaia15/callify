import { useContext } from "react";
import { AppContext } from "../context/context";
import { useState,useEffect } from "react";

export function useSignUp() {
    const {isSignedIn, setIsSignedIn, user, setUser} = useContext(AppContext);
    const [email, setEmail] = useState("");
    const [password,setPassword] = useState("");
    const signUp = async function () {
        // TO-DO: send request to backend services
    }

    useEffect(() => {}, [isSignedIn]);

    // console.log(email);
    // console.log(password);

    return {email,password,signUp,isSignedIn,setEmail,setPassword,user}
}