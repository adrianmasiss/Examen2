import React from "react";

export default function Orden({ orden, comprar }) {
    const total = orden.reduce((acc, item) => {
        const precio = item.tamano === "Grande"
            ? Math.round(item.platillo.precio * 1.2)
            : item.platillo.precio;
        return acc + precio * item.cantidad;
    }, 0);

    return (
        <aside className="orden">
            <h3>Tu orden</h3>
            {orden.length === 0 ? (
                <div className="orden-vacia">No hay platillos aún.</div>
            ) : (
                <ul>
                    {orden.map((item, i) => (
                        <li key={i}>
                            <strong>{item.platillo.nombre}</strong> x{item.cantidad}
                            <span>({item.tamano})</span>
                        </li>
                    ))}
                </ul>
            )}
            <div className="orden-total">Total: ₡{total}</div>
            <button className="comprar-btn" onClick={comprar} disabled={orden.length === 0}>
                Comprar
            </button>
        </aside>
    );
}
