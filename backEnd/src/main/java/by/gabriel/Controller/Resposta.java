package by.gabriel.Controller;

import by.gabriel.Model.Usuario;

public class Resposta {

    private boolean sucesso;
    private String mensagem;
    private Usuario usuario;

    public Resposta(boolean sucesso, String mensagem) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
    }

    // Construtor com 3 parâmetros (sucesso + mensagem + usuario)
    public Resposta(boolean sucesso, String mensagem, Usuario usuario) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
