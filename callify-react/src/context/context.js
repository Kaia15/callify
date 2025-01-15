import { createContext, useState } from "react";

const AppContext = createContext(null);

const AppProvider = ({ children }) => {
    const [isSignedIn, setIsSignedIn] = useState(false);
    const [user, setUser] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [allMeetings, setAllMeetings] = useState([]);
    const [meeting, setMeeting] = useState(null);

    return (
        <AppContext.Provider value={{ isSignedIn, user, isLoaded, allMeetings, meeting, setIsSignedIn, setIsLoaded, setUser, setAllMeetings, setMeeting }}>
            {children}
        </AppContext.Provider>
    );
}

export { AppContext, AppProvider };