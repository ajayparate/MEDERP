// src/pages/LoginForm.jsx
import React, { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../api/axios";
import { AuthContext } from "../context/AuthContext";

function LoginForm() {
  const [formData, setFormData] = useState({ email: "", password: "" });
  const [errors, setErrors] = useState({});
  const { login } = useContext(AuthContext);
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
  e.preventDefault();
  const newErrors = {};
  if (!formData.email.trim()) newErrors.email = "Email is mandatory.";
  if (!formData.password.trim()) newErrors.password = "Password is mandatory.";
  

  if (Object.keys(newErrors).length > 0) {
    setErrors(newErrors);
    return;
  }

  try {
    const res = await api.post("/api/auth/login", formData);
    const { token, firstName, lastName, email, role } = res.data.data;

    login(token, { firstName, lastName, email, role });
    navigate("/dashboard");
    

    navigate("/dashboard");
  } catch (err) {
    console.error("Login error:", err);
    setErrors({ api: "Invalid credentials. Please try again." });
  }
};


  return (
    <div className="min-h-screen flex items-center justify-center bg-slate-100 p-4">
      <div className="bg-white p-8 rounded-xl shadow-lg w-full max-w-sm">
        <h2 className="text-center text-3xl font-extrabold text-gray-900 mb-6">
          Log In to Your Account
        </h2>
        {errors.api && (
          <p className="mb-4 text-red-600 text-center">{errors.api}</p>
        )}
        <form onSubmit={handleSubmit} className="space-y-6">
          <div>
            <label className="block text-sm font-medium text-gray-700">
              Email
            </label>
            <input
              name="email"
              type="email"
              value={formData.email}
              onChange={handleChange}
              className={`mt-1 block w-full border rounded-md px-3 py-2 ${
                errors.email ? "border-red-500" : "border-gray-300"
              }`}
            />
            {errors.email && (
              <p className="text-red-600 text-sm">{errors.email}</p>
            )}
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700">
              Password
            </label>
            <input
              name="password"
              type="password"
              value={formData.password}
              onChange={handleChange}
              className={`mt-1 block w-full border rounded-md px-3 py-2 ${
                errors.password ? "border-red-500" : "border-gray-300"
              }`}
            />
            {errors.password && (
              <p className="text-red-600 text-sm">{errors.password}</p>
            )}
          </div>

          <button
            type="submit"
            className="w-full bg-blue-600 text-white py-2 rounded-md hover:bg-blue-700"
          >
            Log In
          </button>
        </form>
      </div>
    </div>
  );
}

export default LoginForm;
