import { useState } from "react";
import "./JobMatch.css";
import { matchJob } from "../../services/jobMatchService";
import AnalysisCard from "../../components/AnalysisCard/AnalysisCard";

const JobMatch = () => {

    const [resumeId, setResumeId] = useState("");
    const [jobDescription, setJobDescription] = useState("");
    const [result, setResult] = useState(null);
    const [loading, setLoading] = useState(false);

    const handleMatch = async () => {

        if (!resumeId || !jobDescription) {
            alert("Please enter Resume ID and Job Description");
            return;
        }

        try {

            setLoading(true);

            const response = await matchJob({

                resumeId: Number(resumeId),
                jobDescription

            });

            setResult(response.data);

        } catch (error) {

            console.log(error);
            alert("Job Match Failed");

        } finally {

            setLoading(false);

        }

    };

    return (

        <div className="job-container">

            <h1>Job Match</h1>

            <div className="input-card">

                <input
                    type="number"
                    placeholder="Resume ID"
                    value={resumeId}
                    onChange={(e) => setResumeId(e.target.value)}
                />

                <textarea

                    rows="8"

                    placeholder="Paste Job Description Here..."

                    value={jobDescription}

                    onChange={(e) => setJobDescription(e.target.value)}

                />

                <button
                    onClick={handleMatch}
                    disabled={loading}
                >

                    {loading ? "Matching..." : "Match Resume"}

                </button>

            </div>

            {result && (

                <>

                    <div className="score-card">

                        <h2>Match Score</h2>

                        <div className="score">

                            {result.matchScore}%

                        </div>

                    </div>

                    <div className="analysis-grid">

                        <AnalysisCard

                            title="Matched Skills"

                            items={result.matchedSkills}

                        />

                        <AnalysisCard

                            title="Missing Skills"

                            items={result.missingSkills}

                        />

                    </div>

                    <div className="summary-card">

                        <h2>Summary</h2>

                        <p>{result.summary}</p>

                    </div>

                    <div className="summary-card">

                        <h2>Recommendation</h2>

                        <p>{result.recommendation}</p>

                    </div>

                </>

            )}

        </div>

    );

};

export default JobMatch;