import { useContext, useEffect } from "react";
import { AppContext } from "../context/context";
import SignIn from "./SignIn";
import { useVideoCall } from "../hooks/useVideoCall";
import { useUser } from "../hooks/useUser";
import { useNavigate } from "react-router-dom";

export default function Home() {
    const { user, isSignedIn } = useUser();
    const navigate = useNavigate();
    const {callId,generateCallID} = useVideoCall();

    return (<div>
        {/* {(isSignedIn && user) ? <div></div> : <SignIn />} */}
        <div class="flex">
            <div class="w-64 h-screen bg-white shadow-lg">
                <div class="flex items-center justify-center h-24">
                    {/* <span class="text-2xl font-semibold"> CALLIFY </span> */}
                    <img src="https://static.vecteezy.com/system/resources/previews/019/493/293/original/zoom-logo-zoom-icon-zoom-symbol-free-free-vector.jpg"
                        class="w-24 h-24" />
                </div>
                <nav class="mt-0">
                    <a href="#" class="flex items-center px-6 py-2 text-gray-900 bg-gray-200">
                        <span class="mx-3">Home</span>
                    </a>
                    <a href="#" class="flex items-center px-6 py-2 text-gray-600 hover:bg-gray-200">
                        <span class="mx-3">Upcoming</span>
                    </a>
                    <a href="#" class="flex items-center px-6 py-2 text-gray-600 hover:bg-gray-200">
                        <span class="mx-3">Previous</span>
                    </a>
                    {/* <a href="#" class="flex items-center px-6 py-2 text-gray-600 hover:bg-gray-200">
                        <span class="mx-3">Recordings</span>
                    </a>
                    <a href="#" class="flex items-center px-6 py-2 text-gray-600 hover:bg-gray-200">
                        <span class="mx-3">Personal Room</span>
                    </a> */}
                </nav>
            </div>
            <div class="flex-1 p-12">
                <div class="flex items-center justify-between">
                    <div class="text-lg font-semibold">Upcoming Meeting at: 12:30 PM</div>
                    <div class="text-4xl font-bold">04:55 PM</div>
                </div>
                <div class="text-lg text-gray-600 mb-6">Saturday, August 3, 2024</div>
                <div class="w-full h-80 bg-blue-100 rounded-lg mb-6">
                    {/* <img src='https://file-paa.zoom.us/1UfmadjzSOiDknZKy1Md4w/MS4yLsR5ExvSc1kQdZoMO7cL6l86hmQJuvLwytZsNaJEQ1tQ/53244c92-ddc8-4d5b-a94c-f6ab21f91b55.png' alt="Background" class="w-1/2 h-full object-cover rounded-lg shadow-lg" /> */}
                </div>
                <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
                    <button class="bg-red-500 text-white p-6 rounded-lg shadow-lg flex items-center justify-center"
                    onClick={async() => {
                        await generateCallID();
                        if (callId) navigate(`/call/${callId}`);
                    }}>
                        <span class="text-xl font-semibold">New Meeting</span>
                    </button>
                    <div class="bg-blue-500 text-white p-6 rounded-lg shadow-lg flex items-center justify-center">
                        <span class="text-xl font-semibold">Join Meeting</span>
                    </div>
                    <div class="bg-purple-500 text-white p-6 rounded-lg shadow-lg flex items-center justify-center">
                        <span class="text-xl font-semibold">Schedule Meeting</span>
                    </div>
                    {/* <div class="bg-yellow-500 text-white p-6 rounded-lg shadow-lg flex items-center justify-center">
                        <span class="text-xl font-semibold">View Recordings</span>
                    </div> */}
                </div>
            </div>
        </div>
    </div>)
}