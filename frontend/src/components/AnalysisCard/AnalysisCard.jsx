import "./AnalysisCard.css";

const AnalysisCard = ({ title, items, isQuestion = false }) => {

    return (
        <div className="analysis-card">

            <h3>{title}</h3>

            {items?.length > 0 ? (

                <ul>

                    {items.map((item, index) => (

                        isQuestion ? (

                            <li key={index} className="question-item">

                                <p className="question">
                                    {item.question}
                                </p>

                                <span className={`difficulty ${item.difficulty.toLowerCase()}`}>
                                    {item.difficulty}
                                </span>

                            </li>

                        ) : (

                            <li key={index}>
                                ✔ {item}
                            </li>

                        )

                    ))}

                </ul>

            ) : (

                <p>No data available</p>

            )}

        </div>
    );

};

export default AnalysisCard;