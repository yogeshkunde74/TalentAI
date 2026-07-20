import axios from "axios";

const API = axios.create({
    baseURL: "http://localhost:8080/api/auth",
});

export const loginUser = (data) => {
    return API.post("/login", data);
};

export const registerUser = (data) => {
    return API.post("/register", data);
};