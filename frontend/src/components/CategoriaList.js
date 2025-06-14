import React from "react";

export default function CategoriaList({ categorias, catSel, setCatSel }) {
    return (
        <aside className="categorias">
            <h3>Categorías</h3>
            <ul>
                {categorias.map(cat => (
                    <li
                        key={cat.id}
                        className={catSel && cat.id === catSel.id ? "cat-sel" : ""}
                        onClick={() => setCatSel(cat)}
                    >
                        {cat.nombre}
                    </li>
                ))}
            </ul>
        </aside>
    );
}
