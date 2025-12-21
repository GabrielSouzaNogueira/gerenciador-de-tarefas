package by.gabriel.Controller.UserController;

import com.google.gson.Gson;
import static spark.Spark.*;
import by.gabriel.Controller.Resposta;
import by.gabriel.Model.Usuario;
import by.gabriel.Repository.UserDAO;
import by.gabriel.Services.UserService;

public class UserController {

    private UserService userService;
    private Gson gson;

    public UserController() {
        this.userService = new UserService(new UserDAO());
        this.gson = new Gson();
        configurarRotas();

    }

    private void configurarRotas() {
        
        post("/login", (req, res) -> {
            
            try {

                Usuario usuario = gson.fromJson(req.body(), Usuario.class);

                // Chama o service
                String resultado = userService.realizarLogin(usuario);
                boolean sucesso = resultado.equals("Login realizado com sucesso!");

                res.type("application/json");

                if (sucesso) {
                    res.status(200); // OK
                } else {
                    res.status(401); // Unauthorized (credenciais inválidas)
                }

                return gson.toJson(new Resposta(sucesso, resultado));

            } catch (IllegalArgumentException e) {
                
                res.status(400); // Bad Request (dados inválidos)
                res.type("application/json");
                return gson.toJson(new Resposta(false, e.getMessage()));

            } catch (Exception e) {
                
                e.printStackTrace();
                res.status(500); // Internal Server Error
                res.type("application/json");
                return gson.toJson(new Resposta(false, "Erro interno no servidor. Tente novamente."));
            }
        });

    }
}
