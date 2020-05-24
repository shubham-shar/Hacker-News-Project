package com.java.assignment.service;

import com.java.assignment.model.PastStory;
import com.java.assignment.repository.PastStoryRepository;
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
@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest
public class PastStoryServiceTest {

    @Mock
    PastStoryRepository pastStoryRepository;

    @InjectMocks
    @Autowired
    PastStoryService pastStoryService;

    @Test
    public void testFetchTopStories(){

        final Model model = new ExtendedModelMap();
        List<PastStory> pastStories = IntStream.range(0, 20).mapToObj(operand -> mock(PastStory.class))
                                                .collect(Collectors.toList());

        Mockito.when(pastStoryRepository.findAll())
                .thenReturn(pastStories);

        Model response = pastStoryService.fetchTopStories(model);

        assertTrue(response.containsAttribute("stories"));
    }
}
