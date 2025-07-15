package com.example.backend.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.model.Job;

@Repository
public interface JobRepository extends MongoRepository<Job, String> {
  
}
