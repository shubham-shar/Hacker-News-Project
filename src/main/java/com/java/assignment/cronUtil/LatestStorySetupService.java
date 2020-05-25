package com.java.assignment.cronUtil;

import com.java.assignment.model.CachedComments;
import com.java.assignment.model.Comment;
import com.java.assignment.model.CommentData;
import com.java.assignment.model.Story;
import com.java.assignment.model.User;
import com.java.assignment.repository.CachedCommentsRepository;
import com.java.assignment.repository.CommentsRepository;
import com.java.assignment.service.ApiService;
import com.java.assignment.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Shubham Sharma
 * @date 25/5/20
 */
@Slf4j
@Service
public class LatestStorySetupService {
    public static final int TEN_MINUTES = 10 * 60 * 1000;

    @Autowired
    HNUtil hnUtil;

    @Autowired
    ApiService apiService;

    @Autowired
    CommentService commentService;

    @Autowired
    CommentsRepository commentsRepository;

    @Autowired
    CachedCommentsRepository cachedCommentsRepository;

    @Scheduled(fixedRate = TEN_MINUTES)
    public void persistLatestStories() {
        log.info("Persisting latest stories");
        List<Long> latestStories = hnUtil.fetchLatestStories();

        List<Story> stories = latestStories.stream()
                .map(apiService::fetchStory)
                .collect(Collectors.toList());

        stories.forEach(story -> {
            List<Comment> comments = commentService.getTopComments(story);
            if(comments.size()>0){
                commentsRepository.saveAll(comments);

                List<CommentData> commentData = comments.stream()
                        .map(comment -> {
                            User user = apiService.fetchUser(comment.getUsername());
                            return commentService.buildCommentData(user, comment);
                        }).collect(Collectors.toList());

                CachedComments commentsToCache = getCommentsToCache(story, commentData);
                cachedCommentsRepository.save(commentsToCache);
            }
        });
        log.info("Persisted latest stories");
    }

    private CachedComments getCommentsToCache(Story story, List<CommentData> commentData) {
        return CachedComments.builder()
                .id(story.getId())
                .topComments(commentData)
                .build();
    }
}
