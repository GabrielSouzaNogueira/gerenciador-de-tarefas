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

        try (Connection conn = new ConexaoDB().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSenha());

            System.out.println("Executando login para: " + usuario.getNome());

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true se encontrou, false se não
        }
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
    
