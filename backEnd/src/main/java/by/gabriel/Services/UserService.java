package by.gabriel.Services;

import java.sql.SQLDataException;

import by.gabriel.Model.Usuario;
import by.gabriel.Repository.UserDAO;
import by.gabriel.Model.Status.UserStatus;

public class UserService {

    UserStatus userStatus;

    private UserDAO dao;

    public UserService(UserDAO dao) {
        this.dao = dao;
    }

    // Método responsável por realizar o login
    public Usuario realizarLogin(Usuario usuario) {
        
        String nome = usuario.getNome();
        nome = nome.trim(); //Removendo os espaços no começo e no fim pois diferente da senha não posso remover os espaços entre o nome.

        //Verificando se o tamanho da senha é menor que 6 e se a senha contem qualquer espaço.(contains())  verificar se
        //  uma string contém outra string dentro dela.
        if(usuario.getSenha().length() < 6 || usuario.getSenha().contains(" ")){
            throw new IllegalArgumentException("A senha precisa ter no minimo 6 e não pode conter espaços");
        }

        //Verifico se o nome está nulo ou (||) se o nome está vazio ou se contem somente espaços
        if (usuario.getNome() == null || usuario.getNome().trim().isBlank()) {
            throw new IllegalArgumentException("Nome de usuário está incorreto ou vazio");
        }

          //Verifico se a senha está nula ou (||) se a senha está vazio ou se contem somente espaços
        if (usuario.getSenha() == null || usuario.getSenha().trim().isBlank()) {
            throw new IllegalArgumentException("A senha está incorreta ou vazia!");
        }

        try {
            
            // Normaliza os valores para minúsculo
            // Isso garante que a comparação não seja sensível a maiúsculas/minúsculas
            usuario.setNome(usuario.getNome().toLowerCase());
            usuario.setSenha(usuario.getSenha());

            // Chama o DAO para verificar se existe um usuário com esse nome e senha no banco
            Usuario autenticado = dao.realizarLogin(usuario);

            if(autenticado == null ) {
                throw new IllegalArgumentException("Usuário ou senha incorretos!");

            }

            if(autenticado.getStatus() == userStatus.DESATIVADO) {
                throw new IllegalStateException("Usuário está desativado e não pode efetuar login.");
            }

            return autenticado; //Caso passe pelas verificações

        } catch (IllegalArgumentException e) {
            // Captura erros específicos de argumentos inválidos
            // Exemplo: se o nome ou senha vierem nulos ou vazios
            throw new IllegalArgumentException("Nome ou senha invalidos: " + e.getMessage());

        } catch (Exception e) {
            // Captura qualquer outro erro inesperado (ex: problemas de conexão com o banco)
            e.printStackTrace(); // Mostra o erro completo no console para debug
            throw new IllegalArgumentException("Ocorreu algum erro ao realizar o login: " + e.getMessage());
        }
    }

    //Metodo para cadastrar o Usuario
    public boolean cadastroUser(Usuario usuario) {
    
        String nome = usuario.getNome();

        nome = nome.trim(); //Removendo os espaços no começo e no fim pois diferente da senha não posso remover os espaços entre o nome.

        //Verifico se o nome está nulo ou (||) se o nome está vazio ou se contem somente espaços 
        if (usuario.getNome() == null || usuario.getNome().trim().isBlank()) {
            throw new IllegalArgumentException("Nome de usuário está vazio ou possui apenas espaços");
        }

        //Verifico se a senha está nula ou (||) se a senha está vazio ou se contem somente espaços
        if (usuario.getSenha() == null || usuario.getSenha().trim().isBlank()) {
            throw new IllegalArgumentException("Senha está vazia ou possui apenas espaços");
        }

        //Verificando se o tamanho da senha é menor que 6 e se a senha contem qualquer espaço.(contains())  verificar se
        //  uma string contém outra string dentro dela.
        if(usuario.getSenha().length() < 6 || usuario.getSenha().contains(" ")){
            throw new IllegalArgumentException("A senha precisa ter no minimo 6 e não pode conter espaços");
        }

        try {
            
            // retorna true se cadastrou, false se não
            return dao.cadastroUser(usuario); 
        
        } 
        catch (SQLDataException e) {
            
            //Caso tenha erro de banco eu trato aqui
            throw new IllegalStateException("Erro ao inserir no banco: " + e.getMessage(), e);
        } 
        catch (Exception e) {
            
            //Caso tenha erro da parte sistemica trato aqui
            throw new RuntimeException("Erro sistêmico: " + e.getMessage(), e);
        
        }
    }
}