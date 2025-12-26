package by.gabriel.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import by.gabriel.ConexaoDB.ConexaoDB;
import by.gabriel.Model.Movimentacao.MovUser;
import by.gabriel.Model.Status.StatusMovUser;

public class MovUserDAO {

    // Método para registrar uma movimentação de usuário no banco
    public MovUser addMovimentacaoUser(int userId,StatusMovUser statusMovUser) throws SQLException {

        String sql = "INSERT INTO movUser (userId, dataMoviment, statusMovUser) VALUES (?, ?, ?)";

        //PreparedStatement pedindo retorno da chave primária gerada
        try (Connection conn = new ConexaoDB().conectar();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, userId);
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now())); // Data/hora atual
            stmt.setString(3, statusMovUser.name());  // Enum Java convertido para String (pq estou inserindo)

            // Executa o INSERT e retorna quantas linhas foram afetadas
            int linhasAfetadas = stmt.executeUpdate();

            // Se pelo menos 1 linha foi inserida no banco...
            if (linhasAfetadas > 0) {

                // Pede ao banco as chaves primárias geradas automaticamente (movimentUserId)
                try (ResultSet rs = stmt.getGeneratedKeys()) {

                    // Se existe uma chave gerada (rs.next() avança para o primeiro registro)
                    if (rs.next()) {

                        // Recupera o valor da primeira coluna (o ID gerado)
                        int idGerado = rs.getInt(1);

                        // Cria e retorna um objeto MovUser já com PK, FK e data/hora
                        return new MovUser(idGerado, userId, LocalDateTime.now(), statusMovUser);
                    }
                }
            }

            // Se não inseriu nada, retorna null
            return null;

        } catch (SQLException e) {
            // Se der erro de banco, lança exceção para ser tratada no Service
            throw new IllegalStateException("Erro ao registrar movimentação: " + e.getMessage(), e);
        }
    }
    
}
