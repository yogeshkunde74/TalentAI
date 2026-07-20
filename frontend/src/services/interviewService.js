import axios from "axios";

const API = axios.create({
    baseURL: "http://localhost:8080/api/interview",
});

export const prepareInterview = (resumeId) => {
    return API.post(`/prepare?resumeId=${resumeId}`);
};