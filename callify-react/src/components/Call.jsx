import { useParams } from "react-router-dom";
import VideoCall from "./VideoCall";

export default function Call() {
    const { id } = useParams();
  return <VideoCall callId={id} />;
}