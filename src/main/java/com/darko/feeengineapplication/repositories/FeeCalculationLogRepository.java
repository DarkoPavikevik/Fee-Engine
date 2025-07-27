package com.darko.feeengineapplication.repositories;

import com.darko.feeengineapplication.model.FeeCalculationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeCalculationLogRepository extends JpaRepository<FeeCalculationLog,Long> {
}
