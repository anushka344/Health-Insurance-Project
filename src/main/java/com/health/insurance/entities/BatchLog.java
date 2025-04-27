package com.health.insurance.entities;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_batch_log")
public class BatchLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long batchLogId;

    private String batchName;
    private int successCount;
    private int failureCount;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status; // SUCCESS, PARTIAL_SUCCESS, FAILED
    private String errorMessage;

    // Getters and Setters
    public Long getBatchLogId() {
        return batchLogId;
    }

    public void setBatchLogId(Long batchLogId) {
        this.batchLogId = batchLogId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public int getFailureCount() {
        return failureCount;
    }

    public void setFailureCount(int failureCount) {
        this.failureCount = failureCount;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
