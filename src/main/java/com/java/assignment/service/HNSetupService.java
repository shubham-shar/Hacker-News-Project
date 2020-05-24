package com.java.assignment.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.java.assignment.model.PastStory;
import com.java.assignment.model.Story;
import com.java.assignment.model.constants.HNType;
import com.java.assignment.repository.HackerNewsRepository;
import com.java.assignment.repository.PastStoryRepository;
import com.java.assignment.utils.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Shubham Sharma
 * @date 23/5/20
 */
@Service
public class HNSetupService {

    public static final int TEN_MINUTES = 10 * 60 * 1000;

    @Autowired
    ApiService apiService;

    @Autowired
    HackerNewsRepository hackerNewsRepository;

    @Autowired
    PastStoryRepository pastStoryRepository;

//    @Scheduled(fixedRate = TEN_MINUTES, initialDelay = TEN_MINUTES)
    public void persistTopStories() {

        List<Long> storiesList = apiService.fetchTop500Stories();

        List<Story> lastestStories = storiesList.stream()
                .map(apiService::fetchStory)
                .filter(Objects::nonNull)
                .filter(story -> HNType.STORY.equals(story.getType()))
                .sorted(Comparator.comparingLong(Story::getScore).reversed())
                .limit(10)
                .collect(Collectors.toList());

        List<PastStory> pastStories = ObjectMapperUtil.getInstance().getObjectMapper()
                .convertValue(hackerNewsRepository.findAll(), new TypeReference<List<PastStory>>() {
                });
        pastStoryRepository.saveAll(pastStories);

        if(lastestStories.size() > 0) {
            hackerNewsRepository.deleteAll();
            hackerNewsRepository.saveAll(lastestStories);
        }
    }

//    @PostConstruct
//    public void init(){
//        persistTopStories();
//    }
}
