package by.gabriel.Model.Usuario;

import java.time.LocalDateTime;

import by.gabriel.Model.Usuario.Enum.UserStatus;;

public class Usuario {
    
    private int userId;
    private String nome;
    private String senha;
    private UserStatus status;
    private transient LocalDateTime dataCriacao;

    public Usuario() {

    }

    public Usuario(int userId, String nome, String senha, UserStatus status, LocalDateTime dataCriacao) {
        this.userId = userId;
        this.nome = nome;
        this.senha = senha;
        this.status = status;
        this.dataCriacao = dataCriacao;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

}
