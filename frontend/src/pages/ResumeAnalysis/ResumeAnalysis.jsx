import { useState } from "react";
import { analyzeResume } from "../../services/resumeService";
import "./ResumeAnalysis.css";
import AnalysisCard from "../../components/AnalysisCard/AnalysisCard";

const ResumeAnalysis = () => {
  const [file, setFile] = useState(null);
  const [analysis, setAnalysis] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleUpload = async () => {
    if (!file) {
      alert("Please select a PDF");
      return;
    }

    const formData = new FormData();
    formData.append("file", file);

    try {
      setLoading(true);

      const response = await analyzeResume(formData);

      setAnalysis(response.data);

      setLoading(false);
    } catch (error) {
      console.log(error);
      alert("Upload Failed");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ padding: "40px" }}>
      <h1>Resume Analysis</h1>

      <input
        type="file"
        accept=".pdf"
        onChange={(e) => setFile(e.target.files[0])}
      />
      {file && (
        <p>
          <strong>Selected File:</strong> {file.name}
        </p>
      )}

      <br />
      <br />

      <button className="upload-btn" onClick={handleUpload} disabled={loading}>
        {loading ? "Analyzing Resume..." : "Analyze Resume"}
      </button>

      {analysis && (
        <div style={{ marginTop: "30px" }}>
          <h2>Analysis Result</h2>

          <div className="result-card">
            <h2>Resume Analysis</h2>

            <div className="score-grid">
              <div className="score-box">
                <h3>Overall</h3>
                <h2>{analysis.resumeScore.overallScore}</h2>
              </div>

              <div className="score-box">
                <h3>Technical</h3>
                <h2>{analysis.resumeScore.technicalScore}</h2>
              </div>

              <div className="score-box">
                <h3>Communication</h3>
                <h2>{analysis.resumeScore.communicationScore}</h2>
              </div>

              <div className="score-box">
                <h3>Experience</h3>
                <h2>{analysis.resumeScore.experienceScore}</h2>
              </div>
            </div>

            <div className="analysis-grid">
              <AnalysisCard title="Skills" items={analysis.skills} />

              <AnalysisCard
                title="Missing Skills"
                items={analysis.missingSkills}
              />

              <AnalysisCard title="Strengths" items={analysis.strengths} />

              <AnalysisCard title="Weaknesses" items={analysis.weaknesses} />
            </div>
            <AnalysisCard
              title="Interview Questions"
              items={analysis.interviewQuestions}
              isQuestion={true}
            />
          </div>
        </div>
      )}
    </div>
  );
};

export default ResumeAnalysis;
