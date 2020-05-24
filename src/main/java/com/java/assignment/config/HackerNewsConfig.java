package com.java.assignment.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author Shubham Sharma
 * @date 22/5/20
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "hacker.news")
public class HackerNewsConfig {
    private String host;
    private String scheme;
    private String query;
    private int port;
    private Map<String,String> paths;

    public String getPath(String reqFor){
        return paths.get(reqFor);
    }
}
