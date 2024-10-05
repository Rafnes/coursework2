package me.skypro.coursework2.service;

import me.skypro.coursework2.domain.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface QuestionService {
    Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question question);

    Question remove(String question, String answer);

    Collection<Question> getAll();

    Question getRandomQuestion();
}
