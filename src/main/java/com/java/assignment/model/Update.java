package com.java.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Shubham Sharma
 * @date 25/5/20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Update {

    private List<Long> items;

    private List<String> User;
}
