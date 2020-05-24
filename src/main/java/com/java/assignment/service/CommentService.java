package com.java.assignment.service;

import com.java.assignment.model.Comment;
import com.java.assignment.model.CommentData;
import com.java.assignment.model.Story;
import com.java.assignment.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
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

    public Model getCommentData(Long storyId, Model model){
        Story story = apiService.fetchStory(storyId);
        List<Comment> comments = story.getComments().stream()
                                    .map(apiService::fetchComment)
                                    .filter(comment -> Objects.nonNull(comment.getSubComments()))
                                    .sorted(Comparator.comparingInt(comment -> comment.getSubComments().size()))
                                    .collect(Collectors.toList());
        Collections.reverse(comments);
        List<Comment> topComments = comments.stream().limit(10).collect(Collectors.toList());
        List<CommentData> commentDataList = topComments.stream()
                .map(comment -> {
                    User user = apiService.fetchUser(comment.getUsername());
                    return buildCommentData(user, comment);
                }).collect(Collectors.toList());
        model.addAttribute("comments", commentDataList);
        return model;
    }

    private CommentData buildCommentData(User user, Comment comment) {
        return CommentData.builder()
                .id(comment.getId())
                .age(getAge(user.getCreatedAt()))
                .text(comment.getText())
                .username(user.getUsername())
                .build();
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
