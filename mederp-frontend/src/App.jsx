import { Routes, Route } from "react-router-dom";
import React from "react";
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
// import Login from "./pages/Login";
// import Register from "./pages/Register";
import Dashboard from "./pages/Dashboard";
import Medicines from "./pages/Medicines";
import MedicineDetails from "./pages/MedicineDetails";
import Users from "./pages/Users";
import NotFound from "./pages/NotFound";
import ProtectedRoute from "./routes/ProtectedRoute";
import RegistrationForm from "./pages/RegistrationForm";
import LoginForm from "./pages/LoginForm";

function App() {
  return (
    <>
      <Navbar />
      <Routes>
        <Route path="/" element={<Dashboard />} />
        {/* <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} /> */}
        
        <Route path="/login" element= {<LoginForm/>}/>

        <Route path="/register" element={<RegistrationForm/>}/>
        {/* Protected Routes */}
        <Route path="/dashboard" element={
          <Dashboard />
        } />
        <Route path="/medicines" element={
          <ProtectedRoute><Medicines /></ProtectedRoute>
        } />
        <Route path="/medicines/:id" element={
          <ProtectedRoute><MedicineDetails /></ProtectedRoute>
        } />
        <Route path="/users" element={
          <ProtectedRoute roles={["ADMIN"]}><Users /></ProtectedRoute>
        } />

        <Route path="*" element={<NotFound />} />
      </Routes>
      <Footer />
    </>
  );
}

export default App;
