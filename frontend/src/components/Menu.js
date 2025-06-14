import React, { useEffect, useState } from "react";
import CategoriaList from "./CategoriaList";
import PlatilloList from "./PlatilloList";
import AgregarPlatilloPopup from "./AgregarPlatilloPopup";
import Orden from "./Orden";
import MensajePopup from "./MensajePopup";


export default function Menu({ user }) {
    const [categorias, setCategorias] = useState([]);
    const [catSel, setCatSel] = useState(null);
    const [platillos, setPlatillos] = useState([]);
    const [orden, setOrden] = useState([]);
    const [showAgregar, setShowAgregar] = useState(null);
    const [mensaje, setMensaje] = useState(null);

    // Cargar categorías al inicio
    useEffect(() => {
        fetch("/api/categorias", {
            headers: { Authorization: "Bearer " + user.token }
        })
            .then(res => res.json())
            .then(data => setCategorias(data));
    }, [user.token]);

    // Cargar platillos de la categoría seleccionada
    useEffect(() => {
        if (catSel) {
            fetch(`/api/platillos/${catSel.nombre}`, {
                headers: { Authorization: "Bearer " + user.token }
            })
                .then(res => res.json())
                .then(data => setPlatillos(data));
        } else {
            setPlatillos([]);
        }
    }, [catSel, user.token]);

    // Agrega platillo a la orden
    const agregarAOrden = (platillo, cantidad, tamano) => {
        setOrden(ordenAnt => {
            // Agrupa si ya existe igual platillo y tamaño
            const i = ordenAnt.findIndex(
                d => d.platillo.id === platillo.id && d.tamano === tamano
            );
            if (i !== -1) {
                const nueva = [...ordenAnt];
                nueva[i].cantidad += cantidad;
                return nueva;
            }
            return [...ordenAnt, { platillo, cantidad, tamano }];
        });
    };

    // Comprar la orden
    const comprar = async () => {
        if (orden.length === 0) return;
        try {
            const detalles = orden.map(item => ({
                platilloId: item.platillo.id,
                cantidad: item.cantidad,
                tamano: item.tamano,
            }));
            const res = await fetch("/api/ordenes", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: "Bearer " + user.token,
                },
                body: JSON.stringify({ detalles }),
            });
            if (!res.ok) throw new Error("Error al comprar");
            const data = await res.json();
            setMensaje(`¡Orden procesada! Número de orden: ${data.numero}`);
            setOrden([]);
        } catch (e) {
            setMensaje("No se pudo procesar la orden.");
        }
        setTimeout(() => setMensaje(null), 4000);
    };

    return (
        <div className="menu-grid">
            <CategoriaList
                categorias={categorias}
                catSel={catSel}
                setCatSel={setCatSel}
            />
            <PlatilloList
                platillos={platillos}
                catSel={catSel}
                onAgregar={setShowAgregar}
            />
            <Orden orden={orden} comprar={comprar} />
            {showAgregar && (
                <AgregarPlatilloPopup
                    platillo={showAgregar}
                    onClose={() => setShowAgregar(null)}
                    onAgregar={agregarAOrden}
                />
            )}
            {mensaje && (
                <MensajePopup
                    mensaje={mensaje}
                    tipo={mensaje.startsWith("¡Orden procesada") ? "ok" : "error"}
                    onClose={() => setMensaje(null)}
                />
            )}
        </div>
    );
}
