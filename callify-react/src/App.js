import './App.css';
import SignIn from './components/SignIn';
import SignUp from './components/SignUp';
import { Routes, Route } from 'react-router-dom';
import Home from './components/Home';
import VideoCall from './components/VideoCall';
import Call from './components/Call';

function App() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/signin" element={<SignIn />} />
      <Route path="/signup" element={<SignUp />} />
      <Route path="/call/:id" element={<Call />} />
      {/* other routes */}
    </Routes>
  );
}

export default App;
