package com.darko.feeengineapplication.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;



public class Transaction {
    private String type;
    private BigDecimal amount;
    private String currency;
    private boolean isDomestic;
    private Client client;

    public Transaction(String type, BigDecimal amount, String currency, boolean isDomestic, Client client) {
        this.type = type;
        this.amount = amount;
        this.currency = currency;
        this.isDomestic = isDomestic;
        this.client = client;
    }

    public Transaction() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isDomestic() {
        return isDomestic;
    }

    public void setDomestic(boolean domestic) {
        isDomestic = domestic;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
