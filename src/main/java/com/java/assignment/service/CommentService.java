package com.java.assignment.service;

import com.java.assignment.model.CachedComments;
import com.java.assignment.model.Comment;
import com.java.assignment.model.CommentData;
import com.java.assignment.model.Story;
import com.java.assignment.model.User;
import com.java.assignment.repository.CachedCommentsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Shubham Sharma
 * @date 23/5/20
 */
@Slf4j
@Service
public class CommentService {

    @Autowired
    ApiService apiService;

    @Autowired
    CachedCommentsRepository cachedCommentsRepository;

    public Model getCommentData(Long storyId, Model model){

        List<CommentData> commentDataList = new ArrayList<>();
        Optional<CachedComments> cachedComment = cachedCommentsRepository.findById(storyId.toString());

        if(cachedComment.isPresent()){
            commentDataList.addAll(cachedComment.get().getTopComments());
        } else {
            Story story = apiService.fetchStory(storyId);
            List<Comment> topComments = getTopComments(story);
            commentDataList = topComments.stream()
                    .map(comment -> {
                        User user = apiService.fetchUser(comment.getUsername());
                        return buildCommentData(user, comment);
                    }).collect(Collectors.toList());
        }

        model.addAttribute("comments", commentDataList);
        return model;
    }

    public List<Comment> getTopComments(Story story) {
        if(Objects.nonNull(story.getComments())) {
            List<Comment> comments = story.getComments().stream()
                    .map(apiService::fetchComment)
                    .sorted(Comparator.comparingInt(comment -> comment.getSubComments().size()))
                    .collect(Collectors.toList());
            Collections.reverse(comments);
            return comments.stream().limit(10).collect(Collectors.toList());
        } else{
            return Collections.emptyList();
        }
    }

    public CommentData buildCommentData(User user, Comment comment) {
        CommentData commentData = CommentData.builder()
                                    .id(comment.getId())
                                    .text(comment.getText())
                                    .build();
        if(Objects.nonNull(user)){
            commentData.setAge(getAge(user.getCreatedAt()));
            commentData.setUsername(user.getUsername());
        }
        return commentData;

    }

    private int getAge(Long createdAt) {
        Calendar currentDate = Calendar.getInstance();
        Calendar creationDate = Calendar.getInstance();
        creationDate.setTimeInMillis(createdAt*1000);

        int age = currentDate.get(Calendar.YEAR) - creationDate.get(Calendar.YEAR);

        if (currentDate.get(Calendar.DAY_OF_YEAR) < currentDate.get(Calendar.DAY_OF_YEAR)){
            age--;
        }
        return age;
    }
}
