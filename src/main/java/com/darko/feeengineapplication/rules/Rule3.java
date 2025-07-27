package com.darko.feeengineapplication.rules;


import com.darko.feeengineapplication.dto.FeeResultDTO;
import com.darko.feeengineapplication.dto.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class Rule3 implements FeeRule {
    @Override
    public boolean matches(Transaction transaction) {
        return transaction.getClient().getCreditScore() > 400;

    }

    @Override
    public FeeResultDTO apply(Transaction transaction) {
        BigDecimal orginal = transaction.getAmount();
        BigDecimal discount = orginal.multiply(BigDecimal.valueOf(0.01));
        return new FeeResultDTO(discount.negate(), List.of("Rule3"));
    }

    @Override
    public String getRuleName() {
        return "Discount1";
    }
}
