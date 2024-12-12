package com.example.tesnutech.pojos;

public class HistoryTransaksiPojo {

    String idHistoryTransaksi;
    String idUser;
    String idPpobMerchant;
    String invoiceNumber;
    String serviceCode;
    String serviceName;
    String jenis;
    String transactionType;
    int totalAmount;
    String createdAt;

    public String getIdHistoryTransaksi() {
        return idHistoryTransaksi;
    }

    public void setIdHistoryTransaksi(String idHistoryTransaksi) {
        this.idHistoryTransaksi = idHistoryTransaksi;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdPpobMerchant() {
        return idPpobMerchant;
    }

    public void setIdPpobMerchant(String idPpobMerchant) {
        this.idPpobMerchant = idPpobMerchant;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
