package com.darko.feeengineapplication.rules;

import com.darko.feeengineapplication.dto.FeeResultDTO;
import com.darko.feeengineapplication.dto.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class Rule1 implements  FeeRule{

    @Override
    public boolean matches(Transaction transaction)
    {
        return transaction.getType().equals("POS");
    }

    @Override
    public FeeResultDTO apply (Transaction transaction)
    {
        BigDecimal fee;
        if(transaction.getAmount().compareTo(BigDecimal.valueOf(100)) <= 0)
        {
            fee = BigDecimal.valueOf(0.20);
        }
        else {
            fee = transaction.getAmount().multiply(BigDecimal.valueOf(0.002));
        }
        return new FeeResultDTO(fee, List.of("Rule1"));
    }

    @Override
    public String getRuleName()
    {
        return "Rule1";
    }
}
