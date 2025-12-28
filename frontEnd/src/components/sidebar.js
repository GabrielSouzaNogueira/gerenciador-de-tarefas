import React from "react";

function Sidebar() {
  return (
    <div className="sidebar-container">
      <div className="sidebar-header">   
        <h2>Administrador</h2>
    </div>

    <nav className="sidebar-nav">
        <ul>
            <li><link href="#"><li>Início</li></link></li>
           <li><link href="#"><li>Perfil</li></link></li>
            <li><link href="#"><li>Configurações</li></link></li>
        </ul>
    </nav>
    <div className="sidebar-footer">
        <p>© 2025 Gerenciador de Tarefas</p>
    </div>
</div>
  );
}

export default Sidebar;