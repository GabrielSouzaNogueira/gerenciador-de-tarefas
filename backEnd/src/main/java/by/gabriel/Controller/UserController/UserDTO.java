package by.gabriel.Controller.UserController;

import by.gabriel.Model.Status.UserStatus;

public class UserDTO {

    private boolean sucesso;
    private String mensagem;
    private int userId;
    private String nome;
    private UserStatus status;

    // Construtor completo
    public UserDTO(boolean sucesso, String mensagem, int userId, String nome, UserStatus status) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
        this.userId = userId;
        this.nome = nome;
        this.status = status;
    }

    // Construtor para erros (sem dados de usuário)
    public UserDTO(boolean sucesso, String mensagem) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    
}