package com.java.assignment.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.java.assignment.Utils.ObjectMapperUtil;
import com.java.assignment.config.HackerNewsConfig;
import com.java.assignment.model.Comment;
import com.java.assignment.model.Story;
import com.java.assignment.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Shubham Sharma
 * @date 22/5/20
 */
@Slf4j
@Service
public class ApiService {

    @Autowired
    HackerNewsConfig hackerNewsConfig;

    @Autowired
    RestTemplate restTemplate;

    public List<Long> fetchTop500Stories() {
        URI storiesUrl = null;
        try {

            storiesUrl = URIUtils.createURI(hackerNewsConfig.getScheme(), hackerNewsConfig.getHost(),
                    hackerNewsConfig.getPort(), hackerNewsConfig.getPath("stories"),
                    null, null);


            log.info("Calling top stories api - {}", storiesUrl.toString());

            ResponseEntity<String> hnResponse = restTemplate.getForEntity(storiesUrl, String.class);

            log.info("Fetched top stories with status: {}", hnResponse.getStatusCode());

            return ObjectMapperUtil.getInstance()
                                        .getObjectMapper()
                                        .readValue(hnResponse.getBody(), new TypeReference<List<Long>>(){});
        } catch (URISyntaxException ex) {
            log.error("Error in building top stories api", ex.getStackTrace());
        } catch (IOException ex){
            log.error("Error in fetching list of stories", ex.getStackTrace());
        }
        return Collections.emptyList();
    }

    public Story fetchStory(Long id) {
        URI storiesUrl = null;
        try {
            storiesUrl = URIUtils.createURI(hackerNewsConfig.getScheme(), hackerNewsConfig.getHost(),
                    hackerNewsConfig.getPort(), String.format(hackerNewsConfig.getPath("story"), id),
                    null, null);


            log.info("Calling story api for id {} - {}", id, storiesUrl.toString());

            ResponseEntity<String> hnResponse = restTemplate.getForEntity(storiesUrl, String.class);

            log.info("Fetched the story {} with status: {}", id, hnResponse.getStatusCode());
            return ObjectMapperUtil.getInstance().getObjectMapper().readValue(hnResponse.getBody(), Story.class);

        } catch (URISyntaxException e) {
            log.error("Error in building story api for {}", id, e);
        } catch (JsonParseException ex) {
            log.error("Error in parsing Story for {}", id, ex);
        } catch (IOException ex) {
            log.error("Error in fetching Story for {}", id, ex);
        }
        return null;
    }

    public Comment fetchComment(Long id) {
        URI storiesUrl = null;
        try {
            storiesUrl = URIUtils.createURI(hackerNewsConfig.getScheme(), hackerNewsConfig.getHost(),
                    hackerNewsConfig.getPort(), String.format(hackerNewsConfig.getPath("story"), id),
                    null, null);


            log.info("Calling comment api for id {} - {}", id, storiesUrl.toString());

            ResponseEntity<String> hnResponse = restTemplate.getForEntity(storiesUrl, String.class);

            log.info("Fetched the Comment {} with status: {}", id, hnResponse.getStatusCode());
            return ObjectMapperUtil.getInstance().getObjectMapper().readValue(hnResponse.getBody(), Comment.class);

        } catch (URISyntaxException e) {
            log.error("Error in building comment api for {}", id, e);
        } catch (JsonParseException ex) {
            log.error("Error in parsing Comment for {}", id, ex);
        } catch (IOException ex) {
            log.error("Error in fetching Comment for {}", id, ex);
        }
        return null;
    }

    public User fetchUser(String username) {
        URI storiesUrl = null;
        try {
            storiesUrl = URIUtils.createURI(hackerNewsConfig.getScheme(), hackerNewsConfig.getHost(),
                    hackerNewsConfig.getPort(), String.format(hackerNewsConfig.getPath("user"), username),
                    null, null);


            log.info("Calling User api for id {} - {}", username, storiesUrl.toString());

            ResponseEntity<String> hnResponse = restTemplate.getForEntity(storiesUrl, String.class);

            log.info("Fetched the user {} with status: {}", username, hnResponse.getStatusCode());
            return ObjectMapperUtil.getInstance().getObjectMapper().readValue(hnResponse.getBody(), User.class);

        } catch (URISyntaxException e) {
            log.error("Error in building user api for {}", username, e);
        } catch (JsonParseException ex) {
            log.error("Error in parsing user for {}", username, ex);
        } catch (IOException ex) {
            log.error("Error in fetching user for {}", username, ex);
        }
        return null;
    }
}
