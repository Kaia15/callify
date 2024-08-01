import { useContext, useEffect } from "react";
import { AppContext } from "../context/context";
import SignIn from "./SignIn";

export default function Home() {
    const {user,isSignedIn} = useContext(AppContext);

    return (<div>
        {(isSignedIn && user) ? <div></div> : <SignIn />}
    </div>)
}