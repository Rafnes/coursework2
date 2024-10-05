package me.skypro.coursework2.controller;

import me.skypro.coursework2.domain.Question;
import me.skypro.coursework2.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final QuestionService questionService;

    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/add")
    public Question addQuestion(@RequestParam(value = "question") String question,
                                @RequestParam(value = "answer") String answer) {
        return questionService.add(question, answer);
    }

    @GetMapping("/remove")
    public Question removeQuestion(@RequestParam(value = "question") String question,
                                   @RequestParam(value = "answer") String answer) {
        return questionService.remove(question, answer);
    }

    @GetMapping
    public Collection<Question> getAll() {
        return questionService.getAll();
    }
}
