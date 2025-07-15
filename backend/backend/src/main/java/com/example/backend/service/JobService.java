package com.example.backend.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.backend.model.Job;
import com.example.backend.repo.JobRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Optional<Job> getJobById(String id) {
        return jobRepository.findById(id);
    }

    // public Job createJob(Job job) {
    //     return jobRepository.save(job);
    // }
    public Job createJob(Job job) {
        if (job.getPostedDate() == null)
            job.setPostedDate(LocalDateTime.now());
        return jobRepository.save(job);
    }


    public Job updateJob(String id, Job updatedJob) {
        updatedJob.setId(id);
        return jobRepository.save(updatedJob);
    }

    public void deleteJob(String id) {
        jobRepository.deleteById(id);
    }
}
