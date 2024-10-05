package me.skypro.coursework2.service;

import me.skypro.coursework2.domain.Question;
import me.skypro.coursework2.exceptions.InvalidQuestionsAmountRequestException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

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
        Question[] questionsArray = questionService.getAll().toArray(new Question[0]);
        List<Question> resultList = new ArrayList<>();
        Random random = new Random();
        int randomIndex = random.nextInt(amount);

        for (int i = 0; i < amount; ) {
            if (!resultList.contains(questionsArray[randomIndex])) {
                resultList.add(questionService.getRandomQuestion());
                i++;
            }
            randomIndex = random.nextInt(amount);
        }
        return resultList;
    }
}
