import { Link } from "react-router-dom";
import React , {useState, useEffect} from "react";
import api from "../api/axios"
import { useContext } from "react";
import { AuthContext } from "../context/AuthContext";

const Dashboard = () => {
  const { user, logout } = useContext(AuthContext);
  const [stats, setStats] = useState({ users: 0, medicines: 0 });

  // useEffect(() => {
  //   api.get("/api/dashboard-stats")
  //     .then(res => setStats(res.data));
  // }, []);


  return (
    <div>
        <h2>Welcome,{user?.firstName} {user?.lastName}</h2>
        {/* <p>Role: {user?.role}</p> */}

        <nav>
            <Link to="/medicines">Add Medicine</Link> |{" "}
            <Link to="/medicines/{id}"> Get A List </Link>{}
            <button onClick={logout}> Logout</button>
        </nav>
        <div>
            <h2>📊 Dashboard</h2>
            <p>Total Users: {stats.users}</p>
            <p>Total Medicines: {stats.medicines}</p>
        </div>
    </div>
    
  );
};

export default Dashboard;
