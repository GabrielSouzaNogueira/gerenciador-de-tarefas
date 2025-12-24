package by.gabriel.ConexaoDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;

public class ConexaoDB {


    public static final String url = "jdbc:mariadb://localhost/gerenciador";
    public static final String user = "root";
    public static final String senha = "root";

    public Connection conectar() throws SQLException{
        return DriverManager.getConnection(url,user,senha);

    }

    public static void testarConexao() {

        try(Connection conn = new ConexaoDB().conectar()) {
            System.out.println("Conectado ao banco com sucesso!!");
        }
        catch(SQLDataException e ) {

            System.out.println("Algo aconteceu com a conexão do banco! " + e.getMessage());
            e.getStackTrace();

        }
        catch(Exception e ) {
            
            System.out.println("Algo aconteceu na parte sistemica: " + e.getMessage());
            e.getStackTrace();
        }
    }
}
