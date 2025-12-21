package by.gabriel;

import by.gabriel.ConexaoDB.ConexaoDB;

public class App{
    public static void main( String[] args ){
        
        ConexaoDB conexaoDB = new ConexaoDB();

        conexaoDB.testarConexao();
    }
}
