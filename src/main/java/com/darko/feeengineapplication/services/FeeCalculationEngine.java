package com.darko.feeengineapplication.services;

import com.darko.feeengineapplication.dto.FeeResultDTO;
import com.darko.feeengineapplication.dto.Transaction;
import com.darko.feeengineapplication.model.FeeCalculationLog;
import com.darko.feeengineapplication.repositories.FeeCalculationLogRepository;
import com.darko.feeengineapplication.rules.FeeRule;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeeCalculationEngine {

    private final List<FeeRule> rules;
    private final FeeCalculationLogRepository feeCalculationLogRepository;
    private final ObjectMapper objectMapper;

    public FeeCalculationEngine(List<FeeRule> rules, FeeCalculationLogRepository feeCalculationLogRepository, ObjectMapper objectMapper) {
        this.rules = rules;
        this.feeCalculationLogRepository = feeCalculationLogRepository;
        this.objectMapper = objectMapper;
    }

    public FeeResultDTO calculate (Transaction transaction)
    {
        BigDecimal totalFee = BigDecimal.ZERO;
        List<String> applied = new ArrayList<>();

        List<FeeRule> discountRules = rules.stream()
                .filter(r -> r.getRuleName().startsWith("Discount"))
                .toList();

        List<FeeRule> regularRules = rules.stream()
                .filter(r -> !r.getRuleName().startsWith("Discount"))
                .toList();

        for(FeeRule rule : regularRules)
        {
            if(rule.matches(transaction))
            {
                FeeResultDTO result = rule.apply(transaction);
                totalFee = totalFee.add(result.getFee());
                applied.addAll(result.getAppliedRules());
            }
        }

        for(FeeRule rule : discountRules)
        {
            if(rule.matches(transaction))
            {
                BigDecimal discount = totalFee.multiply(BigDecimal.valueOf(0.01)).negate();
                totalFee = totalFee.add(discount);
                applied.add(rule.getRuleName());
            }
        }
        FeeResultDTO finalResult = new FeeResultDTO(totalFee,applied);

        try{
            String inputJson = objectMapper.writeValueAsString(transaction);
            String resultJson = objectMapper.writeValueAsString(finalResult);
            FeeCalculationLog log = new FeeCalculationLog(inputJson, resultJson, java.time.LocalDateTime.now());
            feeCalculationLogRepository.save(log);
        }catch (Exception e)
        {
            System.err.println("Failed to log transaction: " + e.getMessage());
        }
        return finalResult;
    }

    public List<FeeResultDTO> calculateBatch(List<Transaction> transactions)
    {
        return transactions.parallelStream()
                .map(this::calculate)
                .collect(Collectors.toList());
    }
}
