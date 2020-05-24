package com.java.assignment.model.constants;

import com.fasterxml.jackson.annotation.JsonProperty;

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
