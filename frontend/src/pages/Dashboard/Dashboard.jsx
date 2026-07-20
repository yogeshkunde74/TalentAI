import "./Dashboard.css";
import DashboardCard from "../../components/DashboardCard/DashboardCard";
const Dashboard = () => {
  return (
    <div className="dashboard">
      <h1>Welcome to TalentAI 🚀</h1>

      <p>AI Powered Resume Screening and Recruitment System</p>

      <div className="cards">
        <DashboardCard
          title="Resume Analysis"
          description="Analyze your resume using AI"
          path="/resume-analysis"
        />

        <DashboardCard
          title="Job Match"
          description="Find matching jobs"
          path="/job-match"
        />

        <DashboardCard
          title="Resume Improvement"
          description="Improve your resume with AI"
          path="/resume-improvement"
        />

        <DashboardCard
          title="Interview Preparation"
          description="Practice interview questions"
          path="/interview-preparation"
        />
      </div>
    </div>
  );
};

export default Dashboard;
