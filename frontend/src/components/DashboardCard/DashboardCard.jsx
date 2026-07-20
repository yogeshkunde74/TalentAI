import "./DashboardCard.css";
import { useNavigate } from "react-router-dom";

const DashboardCard = ({ title, description, path }) => {

    const navigate = useNavigate();

    return (
        <div className="card">

            <h2>{title}</h2>

            <p>{description}</p>

            <button onClick={() => navigate(path)}>
                Open
            </button>

        </div>
    );
};

export default DashboardCard;