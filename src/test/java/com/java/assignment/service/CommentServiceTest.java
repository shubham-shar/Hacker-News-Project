package com.java.assignment.service;

import com.java.assignment.model.Comment;
import com.java.assignment.model.Story;
import com.java.assignment.model.User;
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
@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class CommentServiceTest {

    @Mock
    ApiService apiService;

    @InjectMocks
    @Autowired
    CommentService commentService;

    @Test
    public void testGetCommentData(){

        final Model model = new ExtendedModelMap();
        Mockito.when(apiService.fetchStory(ArgumentMatchers.anyLong())).thenReturn(mock(Story.class));
        Mockito.when(apiService.fetchComment(ArgumentMatchers.anyLong())).thenReturn(mock(Comment.class));
        Mockito.when(apiService.fetchUser(ArgumentMatchers.anyString())).thenReturn(mock(User.class));

        Model response = commentService.getCommentData(ArgumentMatchers.anyLong(), model);

        assertTrue(response.containsAttribute("comments"));
    }
}
