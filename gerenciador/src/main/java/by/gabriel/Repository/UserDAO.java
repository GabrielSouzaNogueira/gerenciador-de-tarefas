package by.gabriel.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.gabriel.ConexaoDB.ConexaoDB;
import by.gabriel.Model.Usuario;

public class UserDAO {

    public boolean realizarLogin(Usuario usuario) throws SQLException {
        String sql = "SELECT nome, senha FROM usuario WHERE nome = ? AND senha = ?";

        if (usuario.getNome() == null || usuario.getNome().trim().isBlank()) {
            throw new IllegalArgumentException("Nome de usuário está incorreto ou vazio!");
        }

        if (usuario.getSenha() == null || usuario.getSenha().trim().isBlank()) {
            throw new IllegalArgumentException("A senha está incorreta ou vazia!");
        }

        try (Connection conn = new ConexaoDB().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());

            System.out.println("Executando login para: " + usuario.getNome());

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true se encontrou, false se não
        }
    }
}
    
