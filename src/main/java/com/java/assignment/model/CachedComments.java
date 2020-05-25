package com.java.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author Shubham Sharma
 * @date 25/5/20
 */
@Data
@Document
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CachedComments {

    @Id
    private String id;

    private List<CommentData> topComments;
}
