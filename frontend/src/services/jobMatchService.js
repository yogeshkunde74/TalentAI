import axios from "axios";

const API = axios.create({
    baseURL: "http://localhost:8080/api/job",
});

export const matchJob = (data) => {
    return API.post("/match", data);
};