package com.example.tesnutech.model;

public class HistoryLoginModel {

    String idHistoryLogin;
    String idUser;
    String token;
    String expiredTime;

    public String getIdHistoryLogin() {
        return idHistoryLogin;
    }

    public void setIdHistoryLogin(String idHistoryLogin) {
        this.idHistoryLogin = idHistoryLogin;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(String expiredTime) {
        this.expiredTime = expiredTime;
    }

}
