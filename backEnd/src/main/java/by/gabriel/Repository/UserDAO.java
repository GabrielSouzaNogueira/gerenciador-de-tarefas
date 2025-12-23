package by.gabriel.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    //Cadastro de usuarios no banco
    public boolean cadastroUser(Usuario usuario) throws SQLException{
        String sql = "INSERT INTO usuario (nome, senha) VALUES (?, ?)"; //Consulta SQL

        //Realizo a conexao
        try(Connection conn = new ConexaoDB().conectar()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            //insiro os valores reais substituindo os placeholders(?)
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());
        
            //Caso tenha criado com sucesso: Retorno as linhas afetadas
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        }
        catch(SQLException e){

            //Caso tenha algum erro de banco eu trato aqui
            throw new IllegalStateException("Algo deu errado na inserção dos dados no banco!: " + e.getMessage());
        }

    }
}
    
