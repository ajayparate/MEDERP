import React from "react";

const Footer = () => {
  return (
    <footer style={{ background: "#f1f1f1", padding: "10px", textAlign: "center", marginTop: "20px" }}>
      <p>© {new Date().getFullYear()} MED-ERP | Built to Support The Healthcare</p>
    </footer>
  );
};

export default Footer;
