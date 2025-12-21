package by.gabriel.Services;

import by.gabriel.Model.Usuario;
import by.gabriel.Repository.UserDAO;

public class UserService {

    private UserDAO dao;

    public UserService(UserDAO dao) {
        this.dao = dao;
    }

    // Método responsável por realizar o login
    public String realizarLogin(Usuario usuario) {
        
        try {
            
            // Normaliza os valores para minúsculo
            // Isso garante que a comparação não seja sensível a maiúsculas/minúsculas
            usuario.setNome(usuario.getNome().toLowerCase());
            usuario.setSenha(usuario.getSenha().toLowerCase());

            // Chama o DAO para verificar se existe um usuário com esse nome e senha no banco
            boolean autenticado = dao.realizarLogin(usuario);

            // Se o DAO retornar true, significa que o usuário foi encontrado e autenticado
            if (autenticado) {
                return "Login realizado com sucesso!";
            } else {
                // Caso contrário, ou nome ou senha estão incorretos
                return "Usuario ou senha incorretos!";
            }

        } catch (IllegalArgumentException e) {
            // Captura erros específicos de argumentos inválidos
            // Exemplo: se o nome ou senha vierem nulos ou vazios
            return e.getMessage();

        } catch (Exception e) {
            // Captura qualquer outro erro inesperado (ex: problemas de conexão com o banco)
            e.printStackTrace(); // Mostra o erro completo no console para debug
            return "Erro ao tentar realizar login. Tente novamente.";
        }
    }
}