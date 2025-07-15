package com.example.backend.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.model.Application;


// public interface ApplicationRepository extends MongoRepository<Application, String> {
//     List<Application> findByUserId(Long userId);
//     boolean existsByUserIdAndJobId(String userId, String jobId);
// }

@Repository
public interface ApplicationRepository extends MongoRepository<Application, String> {
    List<Application> findByUserId(String userId); // You may need custom projection here
    boolean existsByUserIdAndJobId(String userId, String jobId); // Custom query method
}
