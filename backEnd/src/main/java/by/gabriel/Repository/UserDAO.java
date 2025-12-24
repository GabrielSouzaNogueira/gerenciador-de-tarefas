package by.gabriel.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.gabriel.ConexaoDB.ConexaoDB;
import by.gabriel.Model.Usuario;
import by.gabriel.Model.Status.UserStatus;

public class UserDAO {

    // Método responsável por buscar o usuário no banco para login
    public Usuario realizarLogin(Usuario usuario) throws SQLException {
        
        // Consulta SQL trazendo os campos necessários (nome, senha, status, etc.)
        String sql = "SELECT userId, nome, senha, status, dataCriacao FROM usuario WHERE nome = ? AND senha = ?";

        // Abre conexão com o banco e prepara a consulta
        try (Connection conn = new ConexaoDB().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Define os parâmetros da consulta (nome e senha informados pelo usuário)
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());

            System.out.println("Executando login para: " + usuario.getNome());

            // Executa a consulta
            ResultSet rs = stmt.executeQuery();

            // Se encontrou um registro
            if (rs.next()) {
                Usuario encontrado = new Usuario();

                // Preenche os dados do objeto Usuario com os valores do banco
                encontrado.setUserId(rs.getInt("userId"));
                encontrado.setNome(rs.getString("nome"));
                encontrado.setSenha(rs.getString("senha"));

                // Converte o campo ENUM do banco (String) para o Enum do Java
                String statusStr = rs.getString("status");
                encontrado.setStatus(UserStatus.valueOf(statusStr));

                // Converte a data de criação para LocalDateTime
                encontrado.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime());

                // Retorna o objeto Usuario completo
                return encontrado;
            }
        }
        // Se não encontrou nenhum usuário, retorna null
        return null;
    }

    // Cadastro de usuários no banco
    public Usuario cadastroUser(Usuario usuario) {
        // SQL para inserir nome e senha (status é definido pelo banco como default)
        String sql = "INSERT INTO usuario (nome, senha) VALUES (?, ?)";

        // Cria conexão e PreparedStatement pedindo retorno da chave primária gerada
        try (Connection conn = new ConexaoDB().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Define os valores dos parâmetros
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());

            // Executa o INSERT
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                // Recupera o ID gerado automaticamente
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idGerado = rs.getInt(1);
                        usuario.setUserId(idGerado);

                        // Define status padrão no objeto (mesmo que o banco já tenha feito isso)
                        usuario.setStatus(UserStatus.ATIVO);
                    }
                }
                // Retorna o usuário já com ID e status
                return usuario;
            } else {
                return null; // Nenhuma linha inserida
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Lança exceção genérica para ser tratada no Service
            throw new IllegalStateException("Erro ao inserir usuário no banco: " + e.getMessage());
        }
    }
}
    
