package by.gabriel;

import static spark.Spark.*;
import by.gabriel.ConexaoDB.ConexaoDB;
import by.gabriel.Controller.UserController.UserController;

public class App{
    public static void main( String[] args ){
        
        port(4567);
    
        new UserController();

    }
}
