import { AppContext } from "../context/context";
import { useContext } from "react";

export function useUser() {
    const {isSignedIn,setIsSigned,user,setUser,isLoaded,setIsLoaded} = useContext(AppContext);
    return {user,isSignedIn}
    
}