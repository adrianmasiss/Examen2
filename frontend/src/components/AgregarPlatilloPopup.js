import React, { useState } from "react";

export default function AgregarPlatilloPopup({ platillo, onClose, onAgregar }) {
    const [cantidad, setCantidad] = useState(1);
    const [tamano, setTamano] = useState("Regular");

    const handleAgregar = () => {
        onAgregar(platillo, cantidad, tamano);
        onClose();
    };

    return (
        <div className="popup-overlay">
            <div className="popup">
                <button className="close-btn" onClick={onClose}>✖</button>
                <h2>{platillo.nombre}</h2>
                <p>{platillo.descripcion}</p>
                <label>
                    Cantidad:
                    <input
                        type="number"
                        min="1"
                        value={cantidad}
                        onChange={e => setCantidad(Number(e.target.value))}
                        style={{ width: 50 }}
                    />
                </label>
                <label>
                    Tamaño:
                    <select value={tamano} onChange={e => setTamano(e.target.value)}>
                        <option>Regular</option>
                        <option>Grande</option>
                    </select>
                </label>
                <button className="submit-btn" onClick={handleAgregar}>
                    Agregar a la orden
                </button>
            </div>
        </div>
    );
}
