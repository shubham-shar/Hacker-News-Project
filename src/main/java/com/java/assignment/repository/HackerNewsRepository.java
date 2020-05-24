package com.java.assignment.repository;

import com.java.assignment.model.Story;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Shubham Sharma
 * @date 23/5/20
 */
@Repository
public interface HackerNewsRepository extends MongoRepository<Story, String> {

}
