// src/context/ToastContext.jsx
import { createContext, useState } from "react";

export const ToastContext = createContext();

export const ToastProvider = ({ children }) => {
  const [toast, setToast] = useState(null);

  const showToast = (message, type = "info") => {
    setToast({ message, type });
    setTimeout(() => setToast(null), 3000);
  };

  return (
    <ToastContext.Provider value={{ showToast }}>
      {children}
      {toast && (
        <div style={{
          position: "fixed", top: "10px", right: "10px",
          padding: "10px", background: typeColor(toast.type), color: "#fff"
        }}>
          {toast.message}
        </div>
      )}
    </ToastContext.Provider>
  );
};

const typeColor = (type) => {
  switch (type) {
    case "success": return "green";
    case "error": return "red";
    case "warning": return "orange";
    default: return "blue";
  }
};
