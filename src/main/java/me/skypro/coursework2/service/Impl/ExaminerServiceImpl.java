package me.skypro.coursework2.service.Impl;

import me.skypro.coursework2.domain.Question;
import me.skypro.coursework2.exceptions.InvalidQuestionsAmountRequestException;
import me.skypro.coursework2.service.ExaminerService;
import me.skypro.coursework2.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount > questionService.getQuestionsCollectionSize() || amount <= 0) {
            throw new InvalidQuestionsAmountRequestException();
        }
        Set<Question> allQuestions = new HashSet<>(questionService.getAll());
        if (amount == questionService.getQuestionsCollectionSize()) {
            return allQuestions;
        }

        Set<Question> resultSet = new HashSet<>();
        while (resultSet.size() < amount) {
            resultSet.add(questionService.getRandomQuestion());
        }
        return resultSet;
    }
}
