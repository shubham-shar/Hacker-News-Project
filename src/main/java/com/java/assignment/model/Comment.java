package com.java.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.java.assignment.model.constants.HNType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author Shubham Sharma
 * @date 23/5/20
 */
@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    private String id;

    private String text;

    private Long parent;

    private HNType type;

    @JsonProperty("kids")
    private List<Long> subComments;

    @JsonProperty("time")
    private Long createdAt;

    @JsonProperty("by")
    private String username;
}
