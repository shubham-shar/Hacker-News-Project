package com.java.assignment.repository;

import com.java.assignment.model.PastStory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Shubham Sharma
 * @date 23/5/20
 */
@Repository
public interface PastStoryRepository extends MongoRepository<PastStory, String> {
}
