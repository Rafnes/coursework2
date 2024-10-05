package me.skypro.coursework2.service;

import me.skypro.coursework2.domain.Question;
import me.skypro.coursework2.exceptions.InvalidQuestionsAmountRequestException;
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
        if (amount > questionService.getAll().size() || amount <= 0) {
            throw new InvalidQuestionsAmountRequestException();
        }

        Set<Question> allQuestions = new HashSet<>(questionService.getAll());
        if (amount == allQuestions.size()) {
            return allQuestions;
        }

        Set<Question> resultSet = new HashSet<>();
        List<Question> questionList = new ArrayList<>(allQuestions);
        Random random = new Random();
        while (resultSet.size() < amount) {
            int randomIndex = random.nextInt(questionList.size());
            Question randomQuestion = questionList.get(randomIndex);
            resultSet.add(randomQuestion);
            questionList.remove(randomIndex);
        }
        return resultSet;
    }
}
