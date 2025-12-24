package by.gabriel.Controller.UserController;

import com.google.gson.Gson;
import static spark.Spark.*;
import by.gabriel.Controller.Resposta;
import by.gabriel.Model.Usuario;
import by.gabriel.Repository.UserDAO;
import by.gabriel.Services.UserService;
import by.gabriel.Controller.UserController.UserDTO;

public class UserController {

    private UserService userService;
    private Gson gson;

    public UserController() {
        this.userService = new UserService(new UserDAO());
        this.gson = new Gson();
        configurarRotas();

    }

    private void configurarRotas() {
        
        //EndPoint para realizar o login
        post("/login", (req, res) -> {
            
            try {
                
                // Converte o JSON recebido no corpo da requisição para um objeto Usuario
                Usuario usuario = gson.fromJson(req.body(), Usuario.class);

                // Chama o service para realizar o login
                Usuario autenticado = userService.realizarLogin(usuario);

                // Cria o DTO com os dados do usuário autenticado + sucesso/mensagem
                UserDTO dto = new UserDTO(
                    true,
                    "Usuário logado com sucesso!",
                    autenticado.getUserId(),
                    autenticado.getNome(),
                    autenticado.getStatus()
                );

                // Configura a resposta HTTP
                res.type("application/json");
                res.status(200); // OK

                // Retorna o DTO convertido em JSON
                return gson.toJson(dto);

            } catch (IllegalArgumentException e) {
                
                // Erros de validação ou usuário inexistente
                res.status(400); // Bad Request
                res.type("application/json");
                return gson.toJson(new UserDTO(false, e.getMessage()));

            } catch (IllegalStateException e) {
                
                // Usuário desativado
                res.status(401); // Unauthorized
                res.type("application/json");
                return gson.toJson(new UserDTO(false, e.getMessage()));

            } catch (Exception e) {
                
                // Erros inesperados
                e.printStackTrace();
                res.status(500); // Internal Server Error
                res.type("application/json");
                return gson.toJson(new UserDTO(false, "Erro interno no servidor. Tente novamente."));
            }
        });


        // Endpoint POST para cadastro de usuário
        post("/cadastro", (req, res) -> {
            
            try {

                // Converte o corpo(dados) da requisição (JSON) em um objeto Usuario
                Usuario usuario = gson.fromJson(req.body(), Usuario.class);

                // Chama o service para tentar cadastrar o usuário
                Usuario cadastrado = userService.cadastroUser(usuario);

                //Criando o DTO para devolver ao cliente
                UserDTO dto = new UserDTO(
                    true,
                    "Usuario cadastrado com sucesso!: ",
                    cadastrado.getUserId(),
                    cadastrado.getNome(),
                    cadastrado.getStatus()
                );

                // Configura a resposta HTTP
                res.type("application/json");
                res.status(200); // OK

                // Retorna o DTO convertido em JSON
                return gson.toJson(dto);

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
