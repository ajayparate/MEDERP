// src/pages/Profile.jsx
import { useContext, useState } from "react";
import { AuthContext } from "../context/AuthContext";

const Profile = () => {
  const { user } = useContext(AuthContext);
  const [profile, setProfile] = useState(user);

  return (
    <div>
      <h2>👤 My Profile</h2>
      <p>Name: {profile.firstName} {profile.lastName}</p>
      <p>Email: {profile.email}</p>
      <p>Username: {profile.username}</p>
      <p>Role: {profile.role}</p>
      
    </div>
  );
};

export default Profile;
