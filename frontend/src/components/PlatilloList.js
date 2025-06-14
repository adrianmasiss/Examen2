import React from "react";

export default function PlatilloList({ platillos, catSel, onAgregar }) {
    return (
        <section className="platillos">
            {catSel ? (
                <>
                    <h2>{catSel.nombre}</h2>
                    {platillos.length === 0 && <div>No hay platillos en esta categoría.</div>}
                    <div className="platillos-list">
                        {platillos.map(p => (
                            <div className="platillo-card" key={p.id}>
                                <div className="platillo-info">
                                    <h4>{p.nombre}</h4>
                                    <p>{p.descripcion}</p>
                                    <div className="precios">
                                        <span>Regular: ₡{p.precio}</span>
                                        <span>Grande: ₡{Math.round(p.precio * 1.2)}</span>
                                    </div>
                                </div>
                                <button className="add-btn" onClick={() => onAgregar(p)}>
                                    <img src="/add.png" alt="Agregar" />
                                </button>
                            </div>
                        ))}
                    </div>
                </>
            ) : (
                <div className="platillos-placeholder">
                    <img src="/Menu.png" alt="Seleccione una categoría" />
                    <p>Seleccione una categoría para ver el menú.</p>
                </div>
            )}
        </section>
    );
}
