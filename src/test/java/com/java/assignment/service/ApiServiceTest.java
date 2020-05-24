package com.java.assignment.service;

import com.java.assignment.config.HackerNewsConfig;
import com.java.assignment.model.Story;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

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

    @Mock
    HackerNewsConfig hackerNewsConfig;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    @Autowired
    ApiService apiService;

    @Before
    public void setUp() throws InterruptedException, ExecutionException, IOException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testTopStories(){
        Mockito.when(hackerNewsConfig.getHost()).thenReturn(HACKER_NEWS_HOST);
        Mockito.when(hackerNewsConfig.getScheme()).thenReturn(SCHEME);
        Mockito.when(hackerNewsConfig.getPort()).thenReturn(PORT);
        Mockito.when(hackerNewsConfig.getPath(ArgumentMatchers.anyString())).thenReturn(TOP_STORIES_PATH);

        Mockito.when(restTemplate.getForEntity(ArgumentMatchers.anyString(), ArgumentMatchers.any()))
                .thenReturn(ResponseEntity.status(200).body(new Story().toString()));
        apiService.fetchTop500Stories();
    }

}
