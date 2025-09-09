// src/api/axios.js
import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8081", // ✅ backend URL
  headers: {
    "Content-Type": "application/json",
  },
});

// ✅ Attach token automatically if available
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");
    if (token) {
      config.headers["Authorization"] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

export default api;
