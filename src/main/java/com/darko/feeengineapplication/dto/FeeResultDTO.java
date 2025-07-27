package com.darko.feeengineapplication.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;



public class FeeResultDTO {
    private BigDecimal fee;
    private List<String> appliedRules;

    public FeeResultDTO(BigDecimal fee, List<String> appliedRules) {
        this.fee = fee;
        this.appliedRules = appliedRules;
    }

    public FeeResultDTO() {
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public List<String> getAppliedRules() {
        return appliedRules;
    }

    public void setAppliedRules(List<String> appliedRules) {
        this.appliedRules = appliedRules;
    }
}
