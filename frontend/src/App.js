import React, { useState } from "react";
import Inicio from "./components/Inicio";
import LoginPopup from "./components/LoginPopup";
import Menu from "./components/Menu";
import "./styles/main.css";

function App() {
  const [user, setUser] = useState(null); // {nombre, token}
  const [showLogin, setShowLogin] = useState(false);

  const handleLogout = () => setUser(null);

  return (
      <div className="app-container">
        <header className="main-header">
          <img src="/logo.png" alt="Restaurante" className="logo" />
          <nav>
            <a href="#" onClick={() => setShowLogin(false)}>
              Inicio
            </a>
            {user && (
                <a href="#" onClick={() => setShowLogin(false)}>
                  Menú
                </a>
            )}
          </nav>
          <div className="user-info">
            {user ? (
                <>
                  <span>👤 {user.nombre}</span>
                  <button className="logout-btn" onClick={handleLogout}>
                    Salir
                  </button>
                </>
            ) : (
                <button className="login-btn" onClick={() => setShowLogin(true)}>
                  Ingresar
                </button>
            )}
          </div>
        </header>
        <main>
          {!user && !showLogin && <Inicio />}
          {!user && showLogin && (
              <LoginPopup
                  setUser={setUser}
                  onClose={() => setShowLogin(false)}
              />
          )}
          {user && <Menu user={user} />}
        </main>
        <footer className="main-footer">
          <span>Restaurante SPA — Práctica Examen Parcial 2</span>
        </footer>
      </div>
  );
}

export default App;
