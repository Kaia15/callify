import { useSignIn } from "../hooks/useSignIn";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { PencilIcon } from '@heroicons/react/outline';

export default function SignIn() {
    const { email, password, setEmail, setPassword, isSignedIn, signIn, user,verificationCode,setVerificationCode } = useSignIn();
    const navigate = useNavigate();
    const [credentialInfo, setCredentialInfo] = useState(false);
    // const [currentUser, setCurrentUser] = useState(null);
    const [sentCode, setSentCode] = useState(false);
    const [checked, setChecked] = useState({
        option1: false,
        option2: false,
    });

    const handleCheckboxChange = (option) => {
        setChecked({
            option1: option === 'option1',
            option2: option === 'option2',
        });
    };

    useEffect(() => {
        if (isSignedIn && user) navigate('/');
    }, [navigate, isSignedIn, user])

    return (
        <div class="min-h-screen bg-gray-100 text-gray-900 flex justify-center">
            <div class="max-w-screen-xl m-0 sm:m-10 bg-white shadow sm:rounded-lg flex justify-center flex-1">
                <div class="lg:w-1/2 xl:w-5/12 p-6 sm:p-12">
                    <div>
                        <img src="https://static.vecteezy.com/system/resources/previews/019/493/293/original/zoom-logo-zoom-icon-zoom-symbol-free-free-vector.jpg"
                            class="w-24 h-24 mx-auto" />
                    </div>
                    <div class="mt-12 flex flex-col items-center">
                        <h1 class="text-2xl xl:text-3xl font-extrabold">
                            Sign In
                        </h1>
                        <div class="w-full flex-1 mt-8">
                            <div class="flex flex-row items-center">
                                <button
                                    class="w-full max-w-xs font-bold shadow-sm rounded-lg py-3 bg-blue-100 text-gray-800 flex items-center justify-center transition-all duration-300 ease-in-out focus:outline-none hover:shadow focus:shadow-sm focus:shadow-outline mx-2">
                                    <div class="bg-white p-2 rounded-full">
                                        <svg class="w-4" viewBox="0 0 533.5 544.3">
                                            <path
                                                d="M533.5 278.4c0-18.5-1.5-37.1-4.7-55.3H272.1v104.8h147c-6.1 33.8-25.7 63.7-54.4 82.7v68h87.7c51.5-47.4 81.1-117.4 81.1-200.2z"
                                                fill="#4285f4" />
                                            <path
                                                d="M272.1 544.3c73.4 0 135.3-24.1 180.4-65.7l-87.7-68c-24.4 16.6-55.9 26-92.6 26-71 0-131.2-47.9-152.8-112.3H28.9v70.1c46.2 91.9 140.3 149.9 243.2 149.9z"
                                                fill="#34a853" />
                                            <path
                                                d="M119.3 324.3c-11.4-33.8-11.4-70.4 0-104.2V150H28.9c-38.6 76.9-38.6 167.5 0 244.4l90.4-70.1z"
                                                fill="#fbbc04" />
                                            <path
                                                d="M272.1 107.7c38.8-.6 76.3 14 104.4 40.8l77.7-77.7C405 24.6 339.7-.8 272.1 0 169.2 0 75.1 58 28.9 150l90.4 70.1c21.5-64.5 81.8-112.4 152.8-112.4z"
                                                fill="#ea4335" />
                                        </svg>
                                    </div>
                                    {/* <span class="ml-4">
                                        Sign Up with Google
                                    </span> */}
                                </button>

                                <button
                                    class="w-full max-w-xs font-bold shadow-sm rounded-lg py-3 bg-blue-100 text-gray-800 flex items-center justify-center transition-all duration-300 ease-in-out focus:outline-none hover:shadow focus:shadow-sm focus:shadow-outline mx-2">
                                    <div class="bg-white p-1 rounded-full">
                                        <svg class="w-6" viewBox="0 0 32 32">
                                            <path fill-rule="evenodd"
                                                d="M16 4C9.371 4 4 9.371 4 16c0 5.3 3.438 9.8 8.207 11.387.602.11.82-.258.82-.578 0-.286-.011-1.04-.015-2.04-3.34.723-4.043-1.609-4.043-1.609-.547-1.387-1.332-1.758-1.332-1.758-1.09-.742.082-.726.082-.726 1.203.086 1.836 1.234 1.836 1.234 1.07 1.836 2.808 1.305 3.492 1 .11-.777.422-1.305.762-1.605-2.664-.301-5.465-1.332-5.465-5.93 0-1.313.469-2.383 1.234-3.223-.121-.3-.535-1.523.117-3.175 0 0 1.008-.32 3.301 1.23A11.487 11.487 0 0116 9.805c1.02.004 2.047.136 3.004.402 2.293-1.55 3.297-1.23 3.297-1.23.656 1.652.246 2.875.12 3.175.77.84 1.231 1.91 1.231 3.223 0 4.61-2.804 5.621-5.476 5.922.43.367.812 1.101.812 2.219 0 1.605-.011 2.898-.011 3.293 0 .32.214.695.824.578C24.566 25.797 28 21.3 28 16c0-6.629-5.371-12-12-12z" />
                                        </svg>
                                    </div>
                                    {/* <span class="ml-4">
                                        Sign Up with GitHub
                                    </span> */}
                                </button>
                            </div>

                            {!credentialInfo && <div class="my-12 border-b text-center">
                                <div
                                    class="leading-none px-2 inline-block text-sm text-gray-600 tracking-wide font-medium bg-white transform translate-y-1/2">
                                    Or continue with e-mail
                                </div>
                            </div>}

                            <div class="mx-auto max-w-xs">
                                {(!credentialInfo) ?
                                    <input
                                        class="w-full px-8 py-4 rounded-lg font-medium bg-gray-100 border border-gray-200 placeholder-gray-500 text-sm focus:outline-none focus:border-gray-400 focus:bg-white"
                                        type="email" placeholder="Email" value={email}
                                        onChange={(e) => setEmail(e.target.value)} />
                                    :
                                    <div class="mt-5 flex flex-col items-start">
                                        <div className="my-4 flex flex-row font-bold"> {email} 
                                        <span className="ml-2">
                                            <button className="flex items-center"
                                            onClick={() => {
                                                setCredentialInfo(false);
                                                setSentCode(false);
                                            }}>
                                            <PencilIcon className="h-5 w-5 text-blue-500 mx-4" /> {/* Adjust size and color as needed */}
                                            </button>
                                        </span>
                                        </div>
                                        {credentialInfo && 
                                        <div class="mb-6 border-b text-center">
                                            <div
                                                class="text-lg text-blue-500 tracking-wide font-medium bg-white transform translate-y-1/2">
                                                Choose credentials methods
                                            </div>
                                        </div>}
                                        <label className="">
                                            <input
                                                className="mr-4 mt-2"
                                                type="checkbox"
                                                checked={checked.option1}
                                                onChange={() => handleCheckboxChange('option1')}
                                            />
                                            Enter your password
                                        </label>
                                        {checked.option1 && 
                                        <input
                                        class="w-full px-8 py-4 rounded-lg font-medium bg-gray-100 border border-gray-200 placeholder-gray-500 text-sm focus:outline-none focus:border-gray-400 focus:bg-white mt-3 mb-2"
                                        type="password" placeholder="Password" value={password}
                                        onChange={(e) => setPassword(e.target.value)}/>
                                        }
                                        <label className="">
                                            <input
                                                className="mr-4 mt-2"
                                                type="checkbox"
                                                checked={checked.option2}
                                                onChange={() => handleCheckboxChange('option2')}
                                            />
                                            Send verification code
                                        </label>
                                        {checked.option2 && 
                                        <div class="w-full">
                                            <button
                                                class="mt-5 tracking-wide font-semibold text-blue-500 bg-gray-100 w-full py-4 rounded-lg hover:bg-gray-300 transition-all duration-300 ease-in-out flex items-center justify-center focus:shadow-outline focus:outline-none"
                                                onClick={() => {
                                                    setSentCode(true);
                                                }}>
                                                {(!sentCode) ? "Send code" : "Resend code"}
                                            </button>
                                            <input
                                            class="w-full px-8 py-4 rounded-lg font-medium bg-gray-100 border border-gray-200 placeholder-gray-500 text-sm focus:outline-none focus:border-gray-400 focus:bg-white mt-3"
                                            type="text" placeholder="Code" value={verificationCode}
                                            onChange={(e) => setVerificationCode(e.target.value)}/>
                                        </div>}
                                    </div>
                                }
                                {(!credentialInfo) ? <button
                                    class="mt-5 tracking-wide font-semibold bg-blue-500 text-gray-100 w-full py-4 rounded-lg hover:bg-indigo-700 transition-all duration-300 ease-in-out flex items-center justify-center focus:shadow-outline focus:outline-none"
                                    onClick={() => setCredentialInfo(true)}>
                                    <span class="ml-3">
                                        Continue
                                    </span>
                                </button>: <button
                                    class="mt-5 tracking-wide font-semibold bg-blue-500 text-gray-100 w-full py-4 rounded-lg hover:bg-indigo-700 transition-all duration-300 ease-in-out flex items-center justify-center focus:shadow-outline focus:outline-none"
                                    onClick={signIn}>
                                    <span class="ml-3">
                                        Continue
                                    </span>
                                </button>}
                                <button
                                    class="mt-5 tracking-wide font-semibold text-blue-500 bg-gray-100 w-full py-4 rounded-lg hover:bg-gray-300 transition-all duration-300 ease-in-out flex items-center justify-center focus:shadow-outline focus:outline-none"
                                    onClick={() => navigate('/signup')}>
                                    <span class="ml-3">
                                        Don't have one? Create account
                                    </span>
                                </button>
                                <p class="mt-6 text-xs text-gray-600 text-center">
                                    I agree to abide by templatana's
                                    <a href="#" class="border-b border-gray-500 border-dotted">
                                        Terms of Service
                                    </a>
                                    and its
                                    <a href="#" class="border-b border-gray-500 border-dotted">
                                        Privacy Policy
                                    </a>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="flex-1 bg-blue-100 text-center hidden lg:flex">
                    <div class="m-12 xl:m-16 w-full bg-contain bg-center bg-no-repeat bg-[url('https://file-paa.zoom.us/1UfmadjzSOiDknZKy1Md4w/MS4yLsR5ExvSc1kQdZoMO7cL6l86hmQJuvLwytZsNaJEQ1tQ/53244c92-ddc8-4d5b-a94c-f6ab21f91b55.png')]">
                    </div>
                </div>
            </div>
        </div>)
}