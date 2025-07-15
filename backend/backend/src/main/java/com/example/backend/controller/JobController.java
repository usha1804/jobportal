package com.example.backend.controller;

// import lombok.RequiredArgsConstructor;
// import org.springframework.web.bind.annotation.*;

// import com.example.backend.model.Job;
// import com.example.backend.service.JobService;

// import java.util.List;

// @RestController
// @RequestMapping("/api/jobs")
// @CrossOrigin(origins = "http://localhost:8080", allowCredentials = "true")
// @RequiredArgsConstructor
// public class JobController {
//     private final JobService jobService;

//     @GetMapping
//     public List<Job> getAllJobs() {
//         return jobService.getAllJobs();
//     }

//     @GetMapping("/search")
//     public List<Job> searchJobs(
//             @RequestParam(required = false) String query,
//             @RequestParam(required = false) String location,
//             @RequestParam(required = false) String type,
//             @RequestParam(required = false) String experience
//     ) {
//         return jobService.searchJobs(query, location, type, experience);
//     }
// }

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.backend.model.Job;
import com.example.backend.service.JobService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class JobController {

    private final JobService jobService;

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable String id) {
        return jobService.getJobById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
    System.out.println("New job created: " + job.getTitle());

        Job savedJob = jobService.createJob(job);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedJob);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Job> updateJob(@PathVariable String id, @RequestBody Job job) {
        return ResponseEntity.ok(jobService.updateJob(id, job));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteJob(@PathVariable String id) {
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }
}
