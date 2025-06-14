import React from "react";

export default function MensajePopup({ mensaje, tipo, onClose }) {
    return (
        <div className="popup-overlay">
            <div className="popup mensaje-popup">
                <button className="close-btn" onClick={onClose}>✖</button>
                <h2>{tipo === "ok" ? "✅ ¡Orden Realizada!" : "❌ Error al procesar la orden"}</h2>
                <p>{mensaje}</p>
            </div>
        </div>
    );
}
