package by.gabriel.Model.Movimentacao;

import java.time.LocalDateTime;

import by.gabriel.Model.Status.StatusMovUser;

public class MovUser {

    private int movUserId;
    private int userId;
    private transient LocalDateTime dataMoviment;
    private StatusMovUser statusMovUser;

    public MovUser(){

    }

    public MovUser(int movUserId, int userId, LocalDateTime dataMoviment,StatusMovUser statusMovUser) {
        this.movUserId = movUserId;
        this.userId = userId;
        this.dataMoviment = dataMoviment;
        this.statusMovUser = statusMovUser;
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

    
}

