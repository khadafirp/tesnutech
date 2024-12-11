package com.example.tesnutech.pojos;

public class SaldoUserPojo {

    String idSaldo;
    String idUser;
    int nominalSaldo;
    int top_up_amount;
    String created_at;

    public int getTop_up_amount() {
        return top_up_amount;
    }

    public void setTop_up_amount(int top_up_amount) {
        this.top_up_amount = top_up_amount;
    }

    public String getIdSaldo() {
        return idSaldo;
    }

    public void setIdSaldo(String idSaldo) {
        this.idSaldo = idSaldo;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public int getNominalSaldo() {
        return nominalSaldo;
    }

    public void setNominalSaldo(int nominalSaldo) {
        this.nominalSaldo = nominalSaldo;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
