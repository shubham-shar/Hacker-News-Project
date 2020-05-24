package com.java.assignment.model.constants;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Shubham Sharma
 * @date 23/5/20
 */
public enum  HNType {
    @JsonProperty("job")
    JOB,

    @JsonProperty("story")
    STORY,

    @JsonProperty("comment")
    COMMENT,

    @JsonProperty("poll")
    POLL,

    @JsonProperty("pollopt")
    POLLOPT;
}
