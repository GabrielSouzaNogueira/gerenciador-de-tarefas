package by.gabriel.Model.Movimentacao;

import java.time.LocalDateTime;

import by.gabriel.Model.Movimentacao.Enum.AcaoMovUser;
import by.gabriel.Model.Movimentacao.Enum.CampoMovUser;

public class MovUser {

    private int movUserId;
    private int userId;
    private AcaoMovUser acaoMov;
    private CampoMovUser campoAfetado;
    private transient LocalDateTime dataMoviment;

    public MovUser(){

    }

    public MovUser(int movUserId, int userId, LocalDateTime dataMoviment,AcaoMovUser acaoMov,CampoMovUser campoAfetado) {
        this.movUserId = movUserId;
        this.userId = userId;
        this.dataMoviment = dataMoviment;
        this.acaoMov = acaoMov;
        this.campoAfetado = campoAfetado;
    }

    public int getMovUserId() {
        return movUserId;
    }

    public void setMovUserId(int movUserId) {
        this.movUserId = movUserId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getDataMoviment() {
        return dataMoviment;
    }

    public void setDataMoviment(LocalDateTime dataMoviment) {
        this.dataMoviment = dataMoviment;
    }

    public AcaoMovUser getAcaoMov() {
        return acaoMov;
    }

    public void setAcaoMov(AcaoMovUser acaoMov) {
        this.acaoMov = acaoMov;
    }

    public CampoMovUser getCampoAfetado() {
        return campoAfetado;
    }

    public void setCampoAfetado(CampoMovUser campoAfetado) {
        this.campoAfetado = campoAfetado;
    }

    
}

