package com.java.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Shubham Sharma
 * @date 23/5/20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentData {

    private String id;

    private String username;

    private int age;

    private String text;
}
