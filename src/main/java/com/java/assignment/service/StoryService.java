package com.java.assignment.service;

import com.java.assignment.model.Story;
import com.java.assignment.repository.HackerNewsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Shubham Sharma
 * @date 22/5/20
 */
@Slf4j
@Service
public class StoryService {

    @Autowired
    HackerNewsRepository hackerNewsRepository;

    public Model fetchTopStories(Model model) {
        List<Story> latestStories = hackerNewsRepository.findAll()
                                        .stream()
                                        .sorted(Comparator.comparingLong(Story::getScore).reversed())
                                        .collect(Collectors.toList());
        model.addAttribute("stories", latestStories);
        return model;
    }
}
