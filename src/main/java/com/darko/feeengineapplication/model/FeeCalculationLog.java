package com.darko.feeengineapplication.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
public class FeeCalculationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String inputJson;
    @Lob
    private String resultJson;
    private LocalDateTime timestamp;


    public FeeCalculationLog(Long id, String inputJson, String resultJson, LocalDateTime timestamp) {
        this.id = id;
        this.inputJson = inputJson;
        this.resultJson = resultJson;
        this.timestamp = timestamp;
    }

    public FeeCalculationLog() {
    }

    public FeeCalculationLog(String inputJson, String resultJson, LocalDateTime timestamp) {
        this.inputJson = inputJson;
        this.resultJson = resultJson;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInputJson() {
        return inputJson;
    }

    public void setInputJson(String inputJson) {
        this.inputJson = inputJson;
    }

    public String getResultJson() {
        return resultJson;
    }

    public void setResultJson(String resultJson) {
        this.resultJson = resultJson;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
