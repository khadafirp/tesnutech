package com.example.tesnutech.model;

public class PpobMerchantModel {

    String idPpobMerchant;
    String serviceCode;
    String serviceName;
    String jenis;
    int price;
    String created_at;

    public String getIdPpobMerchant() {
        return idPpobMerchant;
    }

    public void setIdPpobMerchant(String idPpobMerchant) {
        this.idPpobMerchant = idPpobMerchant;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
