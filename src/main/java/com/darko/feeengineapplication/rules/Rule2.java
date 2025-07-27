package com.darko.feeengineapplication.rules;

import com.darko.feeengineapplication.dto.FeeResultDTO;
import com.darko.feeengineapplication.dto.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class Rule2 implements FeeRule {

    @Override
    public boolean matches(Transaction transaction) {
        return transaction.getType().equals("ECOMMERCE");
    }

    @Override
    public FeeResultDTO apply(Transaction transaction)
    {
        BigDecimal fee = transaction.getAmount().multiply(BigDecimal.valueOf(0.018)).add(BigDecimal.valueOf(0.15));
        fee = fee.min(BigDecimal.valueOf(120));
        return new FeeResultDTO(fee, List.of("Rule2"));
    }

    @Override
    public String getRuleName()
    {
        return "Rule2";
    }
}
