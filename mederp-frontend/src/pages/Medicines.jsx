import React from "react";
import { useState, useContext, useEffect } from "react";
import api from "../api/axios";
import { AuthContext } from "../context/AuthContext";

const Medicines = () => {
  const { token } = useContext(AuthContext);

  const [medicine, setMedicine] = useState({
    name: "",
    manufacturer: "",
    category: "",
    quantity: "",
    price: "",
    expiryDate: "",
    manufacturingDate: "",
    available: false
  });

  const [medicines, setMedicines] = useState([]);
  const [message, setMessage] = useState("");
  const [search, setSearch] = useState("");

  // Pagination state
  const [page, setPage] = useState(1);
  const pageSize = 5;

  const handleChange = (e) => {
    const { name, value } = e.target;
    setMedicine({ ...medicine, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await api.post("/api/medicines", medicine);
      setMessage(`✅ Medicine created: ${res.data.data.name}`);
      setMedicines([...medicines, res.data.data]);
      setMedicine({
        name: "",
        manufacturer: "",
        category: "",
        quantity: "",
        price: "",
        expiryDate: "",
        manufacturingDate: "",
        available: true
      });
    } catch (err) {
      console.error(err);
      setMessage("❌ Failed to create medicine");
    }
  };

  const fetchMedicines = async () => {
    try {
      const res = await api.get("/api/medicines");
      setMedicines(res.data);
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    fetchMedicines();
  }, []);

  // 🔎 Filtered & paginated data
  const filtered = medicines.filter((m) =>
    m.name.toLowerCase().includes(search.toLowerCase())
  );
  const totalPages = Math.ceil(filtered.length / pageSize);
  const paginated = filtered.slice((page - 1) * pageSize, page * pageSize);

  return (
    <div>
      <h2>Medicines</h2>
      <button>Search</button>

      <input
        type="text"
        placeholder="🔍 Search Medicine"
        value={search}
        onChange={(e) => {
          setSearch(e.target.value);
          setPage(1); // reset to first page when searching
        }}
      />

      <h2>Create Medicine</h2>
      <div typeof="container">
      <form onSubmit={handleSubmit}>
        <input type="text" name="name" placeholder="Medicine Name" value={medicine.name} onChange={handleChange} required />
        <br />
        <input type="text" name="manufacturer" placeholder="Manufacturer" value={medicine.manufacturer} onChange={handleChange} required />
        <br />
        <select
          name="category"
          value={medicine.category}
          onChange={handleChange}
          required
        >
          <option value="">-- Select Category --</option>
          <option value="TABLET">TABLET</option>
          <option value="CAPSULE">CAPSULE</option>
          <option value="SYRUP">SYRUP</option>
          <option value="INHALER">INHALER</option>
          <option value="SUSPENSION">SUSPENSION</option>
          <option value="DROP">DROP</option>
          <option value="INJECTION">INJECTION</option>
          <option value="OINTMENT">OINTMENT</option>
          <option value="OTHERS">OTHERS</option>
        </select>
        <br />
        <input type="number" name="quantity" placeholder="Quantity" value={medicine.quantity} onChange={handleChange} required />
        <br />
        <input type="number" name="price" placeholder="Price" value={medicine.price} onChange={handleChange} required />
        <br />
        <input type="date" name="expiryDate" value={medicine.expiryDate} onChange={handleChange} required />
        <br />
        <input type="date" name="manufacturingDate" value={medicine.manufacturingDate} onChange={handleChange} required />
        <br />
        <select name="available" value={medicine.available} onChange={handleChange} required>
          <option value="">-- Select Availability --</option>
          <option value={true}>Yes</option>
          <option value={false}>No</option>
        </select>
        <br />
        <button type="submit">Add Medicine</button>
      </form>
      </div>

      {message && <p>{message}</p>}

      <h3>📋 Medicines List</h3>
      <table border="1" cellPadding="5">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Manufacturer</th>
            <th>Category</th>
            <th>Quantity</th>
            <th>Price (₹)</th>
            <th>Expiry Date</th>
            <th>Manufacturing Date</th>
            <th>Available</th>
          </tr>
        </thead>
        <tbody>
          {paginated.length > 0 ? (
            paginated.map((med) => (
              <tr key={med.id}>
                <td>{med.id}</td>
                <td>{med.name}</td>
                <td>{med.manufacturer}</td>
                <td>{med.category}</td>
                <td>{med.quantity}</td>
                <td>{med.price}</td>
                <td>{med.expiryDate}</td>
                <td>{med.manufacturingDate}</td>
                <td>{med.available ? "Yes" : "No"}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="9">No medicines found</td>
            </tr>
          )}
        </tbody>
      </table>

      {/* Pagination controls */}
      <div style={{ marginTop: "10px" }}>
        <button disabled={page === 1} onClick={() => setPage((p) => p - 1)}>
          ⬅ Prev
        </button>
        <span style={{ margin: "0 10px" }}>
          Page {page} of {totalPages || 1}
        </span>
        <button disabled={page === totalPages} onClick={() => setPage((p) => p + 1)}>
          Next ➡
        </button>
      </div>
    </div>
  );
};

export default Medicines;
