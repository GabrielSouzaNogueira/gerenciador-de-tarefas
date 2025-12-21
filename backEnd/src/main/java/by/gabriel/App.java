package by.gabriel;

import static spark.Spark.*;

import by.gabriel.Configuration.CorsFilter;
import by.gabriel.Controller.UserController.UserController;

public class App{
    public static void main( String[] args ){
        
        port(4567);
    
        CorsFilter.enableCORS(); //Habilita o CORS
        new UserController(); //Instancia o controller

    }
}
