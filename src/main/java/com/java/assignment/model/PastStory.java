package com.java.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Shubham Sharma
 * @date 23/5/20
 */
@Data
@Document
@AllArgsConstructor
public class PastStory extends Story {

}
