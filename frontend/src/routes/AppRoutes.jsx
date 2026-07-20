import { Routes, Route } from "react-router-dom";
import Login from "../pages/Login/Login";
import Dashboard from "../pages/Dashboard/Dashboard";
import ResumeAnalysis from "../pages/ResumeAnalysis/ResumeAnalysis";
import JobMatch from "../pages/JobMatch/JobMatch";
import ResumeImprovement from "../pages/ResumeImprove/ResumeImprovement";
import InterviewPreparation from "../pages/InterviewPreparation/InterviewPreparation";

export default function AppRoutes() {
  return (
    <Routes>
      <Route path="/" element={<Login />} />

      <Route path="/dashboard" element={<Dashboard />} />

      <Route path="/resume-analysis" element={<ResumeAnalysis />} />

      <Route path="/job-match" element={<JobMatch />} />

      <Route path="/resume-improvement" element={<ResumeImprovement />} />

      <Route path="/interview-preparation" element={<InterviewPreparation />} />

      
    </Routes>
  );
}
