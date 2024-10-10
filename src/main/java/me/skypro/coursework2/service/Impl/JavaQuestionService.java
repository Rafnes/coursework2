package me.skypro.coursework2.service.Impl;

import jakarta.annotation.PostConstruct;
import me.skypro.coursework2.domain.Question;
import me.skypro.coursework2.exceptions.InsufficientParamsException;
import me.skypro.coursework2.exceptions.QuestionAlreadyExistsException;
import me.skypro.coursework2.exceptions.QuestionNotFoundException;
import me.skypro.coursework2.service.QuestionService;
import me.skypro.coursework2.utilities.RandomGenerator;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions = new HashSet<>();

    @PostConstruct
    public void setUp() {
        questions.add(new Question("Вопрос 1",
                "Ответ 1"));
        questions.add(new Question("Вопрос 2",
                "Ответ 2"));
        questions.add(new Question("Вопрос 3",
                "Ответ 3"));
        questions.add(new Question("Какие существуют принципы ООП",
                "Наследование, инкапсуляция, полиморфизм, [абстракция]"));
        questions.add(new Question("Перечислите типы переменных с плавающей точкой",
                "double, float"));
        questions.add(new Question("Перечислите целочисленные типы переменных",
                "byte, short, int, long"));
        questions.add(new Question("Возможно ли в Java множественное наследование (состояния)",
                "Нет"));
        questions.add(new Question("В чем отличие постфиксного инкремента(декремента) от префиксного",
                "Постфиксный ин(де)кремент сначала возвращает значение, а потом увеличивает/уменьшает переменную"));
    }

    @Override
    public Question add(String question, String answer) {
        if (question == null || answer == null) {
            throw new InsufficientParamsException();
        }
        Question q = new Question(question, answer);
        if (questions.contains(q)) {
            throw new QuestionAlreadyExistsException();
        }
        questions.add(q);
        return q;
    }

    @Override
    public Question add(Question question) {
        if (question == null) {
            throw new InsufficientParamsException();
        }
        if (questions.contains(question)) {
            throw new QuestionAlreadyExistsException();
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (question == null) {
            throw new InsufficientParamsException();
        }
        if (!questions.contains(question)) {
            throw new QuestionNotFoundException();
        }
        questions.remove(question);
        return null;
    }

    public Question remove(String question, String answer) {
        if (question == null || answer == null) {
            throw new InsufficientParamsException();
        }
        Question q = new Question(question, answer);
        if (!questions.contains(q)) {
            throw new QuestionNotFoundException();
        }
        questions.remove(q);
        return null;
    }

    @Override
    public Collection<Question> getAll() {
        return new ArrayList<>(questions);
    }

    @Override
    public int getQuestionsCollectionSize() {
        return questions.size();
    }

    @Override
    public Question getRandomQuestion() {
        Question[] questionsArray = questions.toArray(new Question[0]);

        int randomIndex = RandomGenerator.getRandomNumber(questionsArray.length);
        return questionsArray[randomIndex];
    }
}
