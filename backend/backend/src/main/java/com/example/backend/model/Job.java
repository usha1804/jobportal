package com.example.backend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "jobs")
public class Job {
    @Id
    private String id;

    private String title;
    private String company;
    private String location;
    private String type; // Full-time, Part-time
    // private String experience;
    private String salary;
    private LocalDateTime postedDate;
    private String description;
    private List<String> skills;
    private String tags; // comma-separated
}
