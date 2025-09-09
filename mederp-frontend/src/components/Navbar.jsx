import { Link } from "react-router-dom";
import React from "react";

import { useContext } from "react";
import { AuthContext } from "../context/AuthContext";

const Navbar = () => {
  const { token, user, logout } = useContext(AuthContext);

  return (
    <nav style={{ background: "#333", padding: "10px" }}>
      <Link to="/dashboard" style={{ color: "white", marginRight: "10px" }}>Dashboard</Link>
      <Link to="/medicines" style={{ color: "white", marginRight: "10px" }}>Medicines</Link>

      <br></br>
      {token ? (
        <>
          <span style={{ color: "lightgray", marginRight: "10px" }}>
            {user?.email} ({user?.role})
          </span>
          <button onClick={logout} style={{ background: "red", color: "white" }}>Logout</button>
        </>
      ) : (
        <>
            <Link to="/login" style={{ color: "white" }}>Login</Link>
            <Link to="/register" style={{ color: "white" }}>  Register</Link>
        </>
      )}
    </nav>
  );
};

export default Navbar;
