import axios from "axios";

const API = axios.create({
    baseURL: "http://localhost:8080/api/improve",
});

export const improveResume = (resumeId) => {
    return API.post(`/${resumeId}`);
};