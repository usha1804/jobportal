package com.example.backend.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.model.Application;
import com.example.backend.model.Job;
import com.example.backend.model.User;
import com.example.backend.repo.ApplicationRepository;
import com.example.backend.repo.JobRepository;
import com.example.backend.repo.UserRepository;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @PostMapping
    public ResponseEntity<?> applyToJob(@RequestParam String jobId, @RequestParam String userId) {
        if (applicationRepository.existsByUserIdAndJobId(userId, jobId)) {
            return ResponseEntity.badRequest().body("Already applied to this job.");
        }

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Application application = new Application();
        application.setJobId(jobId);
        application.setUserId(userId);
        application.setAppliedDate(LocalDate.now());

        applicationRepository.save(application);
        return ResponseEntity.ok(application);
    }

    // @GetMapping("/user/{userId}")
    // public ResponseEntity<List<Application>> getApplications(@PathVariable String userId) {
    //     return ResponseEntity.ok(applicationRepository.findByUserId(userId));
    // }
    
    @GetMapping("/user/{userId}")
public ResponseEntity<List<Job>> getJobsForUser(@PathVariable String userId) {
    List<Application> applications = applicationRepository.findByUserId(userId);

    List<Job> jobs = applications.stream()
        .map(app -> jobRepository.findById(app.getJobId()).orElse(null))
        .filter(Objects::nonNull)
        .collect(Collectors.toList());

    return ResponseEntity.ok(jobs);
}

}
