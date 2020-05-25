package com.java.assignment.repository;

import com.java.assignment.model.CachedComments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Shubham Sharma
 * @date 25/5/20
 */
@Repository
public interface CachedCommentsRepository extends MongoRepository<CachedComments, String> {
}
