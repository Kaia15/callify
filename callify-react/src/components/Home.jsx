import { useState } from "react";
import { useUser } from "../hooks/useUser";
import { useNavigate } from "react-router-dom";
import { getSignature } from "../utils/requests";

export default function Home() {
    const { user, isSignedIn } = useUser();
    const navigate = useNavigate();
    // const { callId, generateCallID } = useVideoCall();
    const [menuOpen, setMenuOpen] = useState(false);
    const [signature,setSignature] = useState(null);
    const [callId, setCallId] = useState();
    const defaultEmail = "ong_t1@denison.edu";

    const toggleMenu = () => {
        setMenuOpen(!menuOpen);
    };

    // const createSignature = async function () {
    //     const response = await getSignature(defaultEmail);
    //     setSignature(response);
    //     setCallId(response?.meetingNumber);
    // }

    // console.log(signature);

    return (<div>
        {/* {(isSignedIn && user) ? <div></div> : <SignIn />} */}
        <div className="flex flex-col md:flex-row">
            <div className="w-full md:w-64 h-screen bg-white shadow-lg md:block hidden">
                <div className="flex items-center justify-center h-24">
                    <img src="https://static.vecteezy.com/system/resources/previews/019/493/293/original/zoom-logo-zoom-icon-zoom-symbol-free-free-vector.jpg"
                        className="w-24 h-24" alt="Logo" />
                </div>
                <nav className="mt-0">
                    <a href="#" className="flex items-center px-6 py-2 text-gray-900 bg-gray-200">
                        <span className="mx-3">Home</span>
                    </a>
                    <a href="#" className="flex items-center px-6 py-2 text-gray-600 hover:bg-gray-200">
                        <span className="mx-3">Upcoming</span>
                    </a>
                    <a href="#" className="flex items-center px-6 py-2 text-gray-600 hover:bg-gray-200">
                        <span className="mx-3">Previous</span>
                    </a>
                </nav>
            </div>
            <div className="flex-1 p-12">
                <button className="md:hidden bg-gray-500 text-white p-2 rounded-lg" onClick={toggleMenu}>
                    Menu
                </button>
                <div className="flex items-center justify-between">
                    <div className="text-lg font-semibold">Upcoming Meeting at: 12:30 PM</div>
                    <div className="text-4xl font-bold">04:55 PM</div>
                </div>
                <div className={`md:hidden ${menuOpen ? 'block' : 'hidden'}`}>
                    <nav className="mt-4 bg-white shadow-lg">
                        <a href="#" className="block px-6 py-2 text-gray-900 bg-gray-200">
                            <span className="mx-3">Home</span>
                        </a>
                        <a href="#" className="block px-6 py-2 text-gray-600 hover:bg-gray-200">
                            <span className="mx-3">Upcoming</span>
                        </a>
                        <a href="#" className="block px-6 py-2 text-gray-600 hover:bg-gray-200">
                            <span className="mx-3">Previous</span>
                        </a>
                    </nav>
                </div>
                <div className="text-lg text-gray-600 mb-6">Saturday, August 3, 2024</div>
                <div className="w-full h-80 bg-blue-100 rounded-lg mb-6"></div>
                <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
                    <button className="bg-red-500 text-white p-6 rounded-lg shadow-lg flex items-center justify-center"
                        onClick={() => {}}>
                        <span className="text-xl font-semibold">New Meeting</span>
                    </button>
                    <div className="bg-blue-500 text-white p-6 rounded-lg shadow-lg flex items-center justify-center">
                        <span className="text-xl font-semibold">Join Meeting</span>
                    </div>
                    <div className="bg-purple-500 text-white p-6 rounded-lg shadow-lg flex items-center justify-center">
                        <span className="text-xl font-semibold">Schedule Meeting</span>
                    </div>
                </div>
            </div>
        </div>

    </div>)
}