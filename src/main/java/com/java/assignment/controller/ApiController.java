package com.java.assignment.controller;

import com.java.assignment.model.Comment;
import com.java.assignment.service.CommentService;
import com.java.assignment.service.PastStoryService;
import com.java.assignment.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Shubham Sharma
 * @date 22/5/20
 */
@Controller
public class ApiController {

    @Autowired
    StoryService storyService;

    @Autowired
    CommentService commentService;

    @Autowired
    PastStoryService pastStoryService;

    @GetMapping("/top-stories")
    public String getTopStories(Model model){
        model = storyService.fetchTopStories(model);
        return "top-stories";
    }

    @GetMapping("/comments/{id}")
    public String getTopComments(@PathVariable("id") Long storyId, Model model){
        //23273247
        model = commentService.getCommentData(storyId, model);
        return "top-comments";
    }

    @GetMapping("/past-stories")
    public String getPastStories(Model model){
        model = pastStoryService.fetchTopStories(model);
        return "past-stories";
    }
}
