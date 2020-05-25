package com.java.assignment.repository;

import com.java.assignment.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Shubham Sharma
 * @date 25/5/20
 */
@Repository
public interface CommentsRepository extends MongoRepository<Comment, String> {
}
