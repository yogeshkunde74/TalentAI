import axios from "axios";

const API = axios.create({
    baseURL: "http://localhost:8080/api/resume",
});

export const analyzeResume = (formData) =>
    API.post("/upload", formData, {
        headers: {
            "Content-Type": "multipart/form-data",
        },
    });