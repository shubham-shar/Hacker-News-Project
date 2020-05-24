package com.java.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shubham Sharma
 * @date 23/5/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @JsonProperty("id")
    private String username;

    @JsonProperty("created")
    private Long createdAt;
}
