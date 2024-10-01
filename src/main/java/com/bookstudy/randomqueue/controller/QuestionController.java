package com.bookstudy.randomqueue.controller;

import com.bookstudy.randomqueue.dto.QuestionDto;
import com.bookstudy.randomqueue.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("questions", questionService.getAllQuestions());
        return "index";
    }

    @PostMapping("/submit")
    public String submitQuestion(QuestionDto questionDto) {
        questionService.addQuestion(questionDto);
        return "redirect:/";
    }

    @PostMapping("/randomize")
    public String randomizeQuestions(Model model) {
        List<QuestionDto> randomizedQuestions = questionService.randomizeQuestions();
        model.addAttribute("randomizedQuestions", randomizedQuestions);
        return "index";
    }
}
