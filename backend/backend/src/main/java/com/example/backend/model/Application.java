package com.example.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "applications")
public class Application {

    @Id
    private String id;
    private String jobId;
    private String userId;
    private LocalDate appliedDate;

    public Application() {
        this.appliedDate = LocalDate.now();
    }

    public Application(String jobId, String userId) {
        this.jobId = jobId;
        this.userId = userId;
        this.appliedDate = LocalDate.now();
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(LocalDate appliedDate) {
        this.appliedDate = appliedDate;
    }
}
