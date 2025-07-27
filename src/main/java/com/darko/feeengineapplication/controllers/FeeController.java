package com.darko.feeengineapplication.controllers;


import com.darko.feeengineapplication.dto.FeeResultDTO;
import com.darko.feeengineapplication.dto.Transaction;
import com.darko.feeengineapplication.model.FeeCalculationLog;
import com.darko.feeengineapplication.repositories.FeeCalculationLogRepository;
import com.darko.feeengineapplication.services.FeeCalculationEngine;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fees")
public class FeeController {

    private final FeeCalculationEngine engine;
    private final FeeCalculationLogRepository feeCalculationLogRepository;

    public FeeController(FeeCalculationEngine engine,
                         FeeCalculationLogRepository feeCalculationLogRepository) {
        this.engine = engine;
        this.feeCalculationLogRepository = feeCalculationLogRepository;
    }

    @PostMapping("/single")
    public FeeResultDTO calculate(@RequestBody Transaction transaction)
    {
        return engine.calculate(transaction);
    }

    @PostMapping("/batch")
    public List<FeeResultDTO> calculateBatch(@RequestBody List<Transaction> transactions)
    {
        return  engine.calculateBatch(transactions);
    }

    @GetMapping("/history")
    public List<FeeCalculationLog> getHistory(){
        return feeCalculationLogRepository.findAll();
    }
}
