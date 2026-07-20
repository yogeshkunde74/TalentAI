import { useState } from "react";
import "./InterviewPreparation.css";
import { prepareInterview } from "../../services/interviewService";
import AnalysisCard from "../../components/AnalysisCard/AnalysisCard";

const InterviewPreparation = () => {

    const [resumeId, setResumeId] = useState("");
    const [result, setResult] = useState(null);
    const [loading, setLoading] = useState(false);

    const handlePrepare = async () => {

        if (!resumeId) {
            alert("Please enter Resume ID");
            return;
        }

        try {

            setLoading(true);

            const response = await prepareInterview(resumeId);

            setResult(response.data);

        } catch (error) {

            console.log(error);
            alert("Failed to generate interview questions");

        } finally {

            setLoading(false);

        }

    };

    return (

        <div className="interview-container">

            <h1>Interview Preparation</h1>

            <div className="input-card">

                <input
                    type="number"
                    placeholder="Enter Resume ID"
                    value={resumeId}
                    onChange={(e) => setResumeId(e.target.value)}
                />

                <button
                    onClick={handlePrepare}
                    disabled={loading}
                >
                    {loading ? "Generating..." : "Generate Questions"}
                </button>

            </div>

            {result && (

                <>

                    <div className="analysis-grid">

                        <AnalysisCard
                            title="Technical Questions"
                            items={result.technicalQuestions}
                            isQuestion={true}
                        />

                        <AnalysisCard
                            title="HR Questions"
                            items={result.hrQuestions}
                            isQuestion={true}
                        />

                        <AnalysisCard
                            title="Coding Questions"
                            items={result.codingQuestions}
                            isQuestion={true}
                        />

                    </div>

                    <div className="tips-card">

                        <h2>Overall Tips</h2>

                        <p>{result.overallTips}</p>

                    </div>

                </>

            )}

        </div>

    );

};

export default InterviewPreparation;