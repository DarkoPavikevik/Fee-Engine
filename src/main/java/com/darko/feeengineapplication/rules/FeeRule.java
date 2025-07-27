package com.darko.feeengineapplication.rules;

import com.darko.feeengineapplication.dto.FeeResultDTO;
import com.darko.feeengineapplication.dto.Transaction;

public interface FeeRule {
    boolean matches(Transaction transaction);
    FeeResultDTO apply(Transaction transaction);
    String getRuleName();
}
