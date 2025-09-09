import { useContext } from "react";
import React from "react";
import { Navigate } from "react-router-dom";
import { AuthContext } from "../context/AuthContext";

const ProtectedRoute = ({ children, roles }) => {
  const { user, token } = useContext(AuthContext);

  // Not logged in
  if (!token) {
    return <Navigate to="/login" replace />;
  }

  // Role restricted
  if (roles && !roles.includes(user?.role)) {
    return <Navigate to="/unauthorized" replace />;
  }

  return children;
};

export default ProtectedRoute;
