package com.java.assignment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.assignment.config.HackerNewsConfig;
import com.java.assignment.model.Comment;
import com.java.assignment.model.Story;
import com.java.assignment.model.User;
import com.java.assignment.utils.ObjectMapperUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * @author Shubham Sharma
 * @date 25/5/20
 */
@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class ApiServiceTest {


    public static final String HACKER_NEWS_HOST = "hacker-news.firebaseio.com";
    public static final String SCHEME = "http";
    public static final int PORT = 443;
    public static final String TOP_STORIES_PATH = "/v0/topstories.json";
    public static final String STORY_PATH = "/v0/item/%d.json";
    public static final String USER_PATH = "/v0/user/%s.json";

    @Mock
    HackerNewsConfig hackerNewsConfig;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    @Autowired
    ApiService apiService;

    @Test
    public void testStory() throws JsonProcessingException {
        Story story = new Story();
        ObjectMapper objectMapper = new ObjectMapper();
        String response = objectMapper.writeValueAsString(story);
        Mockito.when(hackerNewsConfig.getHost()).thenReturn(HACKER_NEWS_HOST);
        Mockito.when(hackerNewsConfig.getScheme()).thenReturn(SCHEME);
        Mockito.when(hackerNewsConfig.getPort()).thenReturn(PORT);
        Mockito.when(hackerNewsConfig.getPath(ArgumentMatchers.anyString())).thenReturn(STORY_PATH);

        Mockito.when(restTemplate.getForEntity(URI.create("http://hacker-news.firebaseio.com:443/v0/item/23273247.json"), String.class))
                .thenReturn(new ResponseEntity(response, HttpStatus.OK));
        Story stories1 = apiService.fetchStory(23273247L);

        assertTrue(Objects.nonNull(stories1));
    }

    @Test
    public void testComment() throws JsonProcessingException {
        Comment comment = new Comment();
        ObjectMapper objectMapper = new ObjectMapper();
        String response = objectMapper.writeValueAsString(comment);
        Mockito.when(hackerNewsConfig.getHost()).thenReturn(HACKER_NEWS_HOST);
        Mockito.when(hackerNewsConfig.getScheme()).thenReturn(SCHEME);
        Mockito.when(hackerNewsConfig.getPort()).thenReturn(PORT);
        Mockito.when(hackerNewsConfig.getPath(ArgumentMatchers.anyString())).thenReturn(STORY_PATH);

        Mockito.when(restTemplate.getForEntity(URI.create("http://hacker-news.firebaseio.com:443/v0/item/23275922.json"), String.class))
                .thenReturn(new ResponseEntity(response, HttpStatus.OK));
        Comment responseComment = apiService.fetchComment(23275922L);

        assertTrue(Objects.nonNull(responseComment));
    }

    @Test
    public void testFetchUser() throws JsonProcessingException {
        User user = new User();
        ObjectMapper objectMapper = new ObjectMapper();
        String response = objectMapper.writeValueAsString(user);
        Mockito.when(hackerNewsConfig.getHost()).thenReturn(HACKER_NEWS_HOST);
        Mockito.when(hackerNewsConfig.getScheme()).thenReturn(SCHEME);
        Mockito.when(hackerNewsConfig.getPort()).thenReturn(PORT);
        Mockito.when(hackerNewsConfig.getPath(ArgumentMatchers.anyString())).thenReturn(USER_PATH);

        Mockito.when(restTemplate.getForEntity(URI.create("http://hacker-news.firebaseio.com:443/v0/user/jrk.json"), String.class))
                .thenReturn(new ResponseEntity(response, HttpStatus.OK));
        User responseUser = apiService.fetchUser("jrk");

        assertTrue(Objects.nonNull(responseUser));
    }

    @Test
    public void testTopStories() throws JsonProcessingException {

        Mockito.when(hackerNewsConfig.getHost()).thenReturn(HACKER_NEWS_HOST);
        Mockito.when(hackerNewsConfig.getScheme()).thenReturn(SCHEME);
        Mockito.when(hackerNewsConfig.getPort()).thenReturn(PORT);
        Mockito.when(hackerNewsConfig.getPath(ArgumentMatchers.anyString())).thenReturn(TOP_STORIES_PATH);

        Mockito.when(restTemplate.getForEntity(URI.create("http://hacker-news.firebaseio.com:443/v0/topstories.json"), String.class))
                .thenReturn(new ResponseEntity("", HttpStatus.OK));
        List<Long> stories1 = apiService.fetchTop500Stories();

        assertTrue(stories1.size() == 0);
    }
}
