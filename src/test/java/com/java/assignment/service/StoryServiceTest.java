package com.java.assignment.service;

import com.java.assignment.model.Story;
import com.java.assignment.repository.HackerNewsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * @author Shubham Sharma
 * @date 25/5/20
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class StoryServiceTest {

    @Mock
    HackerNewsRepository hackerNewsRepository;

    @InjectMocks
    @Autowired
    StoryService storyService;

    @Test
    public void testFetchTopStories(){

        final Model model = new ExtendedModelMap();
        List<Story> stories = IntStream.range(0, 10).mapToObj(operand -> mock(Story.class))
                .collect(Collectors.toList());

        Mockito.when(hackerNewsRepository.findAll())
                .thenReturn(stories);

        Model response = storyService.fetchTopStories(model);

        assertTrue(response.containsAttribute("stories"));
    }
}
