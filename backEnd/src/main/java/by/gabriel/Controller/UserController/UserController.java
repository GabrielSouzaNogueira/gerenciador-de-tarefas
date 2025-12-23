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

                // Converte o JSON recebido no corpo da requisição para um objeto Usuario
                Usuario usuario = gson.fromJson(req.body(), Usuario.class);

                // Chama o service para realizar o login
                // Se credenciais inválidas ou usuário desativado, o service lança exceção
                Usuario autenticado = userService.realizarLogin(usuario);

                // Se chegou aqui, o login foi bem-sucedido
                res.type("application/json");
                res.status(200); // OK

                // Retorna uma resposta JSON com sucesso = true e mensagem
                return gson.toJson(new Resposta(true, "Usuário logado com sucesso!", autenticado));


            } 
            catch (IllegalArgumentException e) {
                
                // Erros de validação ou usuário inexistente
                res.status(400); // Bad Request
                res.type("application/json");
                return gson.toJson(new Resposta(false, e.getMessage()));

            } 
            catch (IllegalStateException e) {
                
                // Usuário desativado
                res.status(401); // Unauthorized
                res.type("application/json");
                return gson.toJson(new Resposta(false, e.getMessage()));

            } 
            catch (Exception e) {
                
                // Erros inesperados
                e.printStackTrace();
                res.status(500); // Internal Server Error
                res.type("application/json");
                return gson.toJson(new Resposta(false, "Erro interno no servidor. Tente novamente."));
            }

        });

        // Endpoint POST para cadastro de usuário
        post("/cadastro", (req, res) -> {
            
            try {

                // Converte o corpo da requisição (JSON) em um objeto Usuario
                // Exemplo: {"nome":"Gabriel","senha":"123"} vira um objeto Usuario
                Usuario usuario = gson.fromJson(req.body(), Usuario.class);

                // Chama o service para tentar cadastrar o usuário
                // O service retorna true se cadastrou com sucesso, false se não
                boolean cadastrado = userService.cadastroUser(usuario);

                // Se o cadastro foi bem-sucedido (true)
                if (cadastrado) {
                    
                    res.status(201); // Define o status HTTP como 201 (Created)
                    return gson.toJson("Usuário cadastrado com sucesso!");
                
                } else {
                    
                    // Se não conseguiu cadastrar (ex: DAO retornou false)
                    res.status(400); // Define o status HTTP como 400 (Bad Request)
                    return gson.toJson("Não foi possível cadastrar o usuário.");
                }

            } 
            catch (IllegalArgumentException e) {
                
                // Captura erros de validação (ex.: nome ou senha inválidos)
                res.status(400); // Bad Request
                return gson.toJson(e.getMessage());

            } 
            catch (IllegalStateException e) {
                
                // Captura erros relacionados ao banco de dados (ex.: SQLDataException tratado no service)
                res.status(500); // Internal Server Error
                return gson.toJson("Erro ao inserir no banco: " + e.getMessage());

            } 
            catch (Exception e) {
                
                // Captura qualquer outro erro inesperado (ex.: problemas sistêmicos)
                e.printStackTrace(); // Mostra o erro completo no console para debug
                res.status(500); // Internal Server Error
                return gson.toJson("Erro sistêmico: " + e.getMessage());
            }
            
        });

    }
}
