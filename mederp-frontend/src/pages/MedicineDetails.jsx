import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from "../api/axios";

const MedicineDetails = () => {
  const { id } = useParams();
  const [medicine, setMedicine] = useState(null);

  useEffect(() => {
    api.get(`/api/medicine/${id}`)
      .then(res => setMedicine(res.data))
      .catch(() => setMedicine(null));
  }, [id]);

  if (!medicine) return <p>Loading medicine...</p>;

  return (
    <div>
      <h2>{medicine.name}</h2>
      <p>Manufacturer: {medicine.manufacturer}</p>
      <p>Category: {medicine.category}</p>
      <p>Quantity: {medicine.quantity}</p>
      <p>Price: ₹{medicine.price}</p>
      <p>Expiry Date: {medicine.expiryDate}</p>
      <p>Manufacturing Date: {medicine.manufacturingDate}</p>
      <p>Available: {medicine.available ? "✅ Yes" : "❌ No"}</p>
    </div>
  );
};

export default MedicineDetails;
