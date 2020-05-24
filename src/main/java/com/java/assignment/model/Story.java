package com.java.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.java.assignment.model.constants.HNType;
import com.sun.javafx.beans.IDProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.util.List;

/**
 * @author Shubham Sharma
 * @date 23/5/20
 */
@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Story {

    @Id
    private String id;

    private String title;

    private String url;

    private Long score;

    private HNType type;

    @JsonProperty("kids")
    private List<Long> comments;

    @JsonProperty("time")
    private Long createdAt;

    @JsonProperty("by")
    private String username;
}
