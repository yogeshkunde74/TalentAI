import { useState } from "react";
import "./ResumeImprovement.css";
import { improveResume } from "../../services/resumeImproveService";

const ResumeImprovement = () => {

    const [resumeId, setResumeId] = useState("");
    const [result, setResult] = useState(null);
    const [loading, setLoading] = useState(false);

    const handleImprove = async () => {

        if (!resumeId) {
            alert("Please enter Resume ID");
            return;
        }

        try {

            setLoading(true);

            const response = await improveResume(resumeId);

            setResult(response.data);

        } catch (error) {

            console.log(error);
            alert("Failed to improve resume");

        } finally {

            setLoading(false);

        }

    };

    return (

        <div className="improve-container">

            <h1>Resume Improvement</h1>

            <div className="input-card">

                <input
                    type="number"
                    placeholder="Enter Resume ID"
                    value={resumeId}
                    onChange={(e) => setResumeId(e.target.value)}
                />

                <button onClick={handleImprove} disabled={loading}>
                    {loading ? "Generating..." : "Improve Resume"}
                </button>

            </div>

            {result && (

                <>

                    <div className="score-card">

                        <h2>Improved ATS Score</h2>

                        <div className="score">
                            {result.improvedScore}
                        </div>

                    </div>

                    <div className="result-card">

                        <h2>Professional Summary</h2>
                        <p>{result.professionalSummary}</p>

                    </div>

                    <div className="result-card">

                        <h2>Improved Skills</h2>
                        <pre>{result.improvedSkills}</pre>

                    </div>

                    <div className="result-card">

                        <h2>Improved Projects</h2>
                        <pre>{result.improvedProjects}</pre>

                    </div>

                    <div className="result-card">

                        <h2>ATS Keywords</h2>
                        <pre>{result.atsKeywords}</pre>

                    </div>

                    <div className="result-card">

                        <h2>Final Suggestions</h2>
                        <pre>{result.finalSuggestions}</pre>

                    </div>

                </>

            )}

        </div>

    );

};

export default ResumeImprovement;