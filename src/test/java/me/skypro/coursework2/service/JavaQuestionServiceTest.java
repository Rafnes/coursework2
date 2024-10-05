package me.skypro.coursework2.service;

import me.skypro.coursework2.domain.Question;
import me.skypro.coursework2.exceptions.InsufficientParamsException;
import me.skypro.coursework2.exceptions.QuestionNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class JavaQuestionServiceTest {
    Question question1 = new Question("Вопрос 1", "Ответ 1");
    Question question2 = new Question("Вопрос 2", "Ответ 2");

    @Mock
    private Random randomMock;

    @InjectMocks
    QuestionService questionService = new JavaQuestionService();

    @Test
    void shouldAddWhenQuestionIsValid() {
        Question actual = questionService.add(question1);
        assertNotNull(actual);
    }

    @Test
    void shouldThrowWhenQuestionOrAnswerIsNull() {
        assertThrows(InsufficientParamsException.class, () -> questionService.add(null, "Ответ"));
        assertThrows(InsufficientParamsException.class, () -> questionService.add("Вопрос", null));
        assertThrows(InsufficientParamsException.class, () -> questionService.add(null, null));
        assertDoesNotThrow(() -> questionService.add("Вопрос", "Ответ"));
    }

    @Test
    void shouldRemoveExistentQuestion() {
        questionService.add("Вопрос", "Ответ");
        Question questionToRemove = questionService.remove("Вопрос", "Ответ");
        assertNull(questionToRemove);
        assertEquals(0, questionService.getAll().size());
    }

    @Test
    void removeShouldThrowWhenQuestionIsNotFoundOrNull() {
        assertThrows(QuestionNotFoundException.class, () -> questionService.remove("Несуществующий вопрос", "Несуществующий ответ"));
        assertThrows(InsufficientParamsException.class, () -> questionService.remove(null));
        assertThrows(InsufficientParamsException.class, () -> questionService.remove("Вопрос", null));
        assertThrows(InsufficientParamsException.class, () -> questionService.remove(null, "ответ"));
        assertThrows(InsufficientParamsException.class, () -> questionService.remove(null, null));
    }

    @Test
    void returnsValidListWhenItsAllGood() {
        questionService.add(question1);
        questionService.add(question2);
        Collection<Question> expected = new ArrayList<>(List.of(question1, question2));
        assertEquals(expected, questionService.getAll());
    }

    @Test
    void returnsEmptyCollectionWhenSetIsEmpty() {
        assertEquals(Collections.EMPTY_LIST, questionService.getAll());
    }

    @Test
    void testGetRandom() {
        MockitoAnnotations.openMocks(this);
        questionService.add(question1);
        questionService.add(question2);
        when(randomMock.nextInt(2)).thenReturn(0);
        Question randomQuestion = questionService.getRandomQuestion();
        assertEquals(randomQuestion, question1);
    }
}