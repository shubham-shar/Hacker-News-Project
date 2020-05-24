package com.java.assignment.controller;

import com.java.assignment.service.CommentService;
import com.java.assignment.service.PastStoryService;
import com.java.assignment.service.StoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * @author Shubham Sharma
 * @date 25/5/20
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ApiControllerTest {

    @Mock
    StoryService storyService;

    @Mock
    CommentService commentService;

    @Mock
    PastStoryService pastStoryService;

    @InjectMocks
    @Autowired
    ApiController apiController;

    @Test
    public void testTopStories(){
        final Model model = new ExtendedModelMap();
        Mockito.when(storyService.fetchTopStories(ArgumentMatchers.any(Model.class)))
                .thenReturn(mock(Model.class));

        String response = apiController.getTopStories(model);

        assertTrue(response.equals("top-stories"));
    }

    @Test
    public void testGetTopComments(){
        final Model model = new ExtendedModelMap();
        Mockito.when(commentService.getCommentData(ArgumentMatchers.anyLong(), ArgumentMatchers.any(Model.class)))
                .thenReturn(mock(Model.class));

        String response = apiController.getTopComments(1234L, model);

        assertTrue(response.equals("top-comments"));
    }

    @Test
    public void testGetPastStories(){
        final Model model = new ExtendedModelMap();
        Mockito.when(pastStoryService.fetchTopStories(ArgumentMatchers.any(Model.class)))
                .thenReturn(mock(Model.class));

        String response = apiController.getPastStories(model);

        assertTrue(response.equals("past-stories"));
    }

}
