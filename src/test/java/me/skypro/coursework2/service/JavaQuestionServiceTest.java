package me.skypro.coursework2.service;

import me.skypro.coursework2.domain.Question;
import me.skypro.coursework2.exceptions.InsufficientParamsException;
import me.skypro.coursework2.exceptions.QuestionNotFoundException;
import me.skypro.coursework2.service.Impl.JavaQuestionService;
import me.skypro.coursework2.utilities.RandomGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

class JavaQuestionServiceTest {
    Question question1 = new Question("Вопрос 1", "Ответ 1");
    Question question2 = new Question("Вопрос 2", "Ответ 2");

    MockedStatic<RandomGenerator> mockedStatic = mockStatic(RandomGenerator.class);

    @InjectMocks
    QuestionService questionService = new JavaQuestionService();


    @AfterEach
    void tearDown() {
        mockedStatic.close();
    }

    @Test
    void shouldAddWhenQuestionIsValid() {

        Question actual = questionService.add(question1);
        assertNotNull(actual);
        assertEquals(1, questionService.getQuestionsCollectionSize());
    }

    @Test
    void shouldThrowWhenQuestionParamIsNull() {
        assertThrows(InsufficientParamsException.class, () -> questionService.add(null, "Ответ"));
    }

    @Test
    void shouldThrowWhenAnswerParamIsNull() {
        assertThrows(InsufficientParamsException.class, () -> questionService.add("Вопрос", null));
    }

    @Test
    void shouldThrowWhenQuestionAndAnswerParamsAreNull() {
        assertThrows(InsufficientParamsException.class, () -> questionService.add(null, null));
    }

    @Test
    void shouldThrowWhenQuestionObjIsNull() {
        assertThrows(InsufficientParamsException.class, () -> questionService.add(null));
    }

    @Test
    void shouldRemoveExistentQuestion() {
        questionService.add("Вопрос", "Ответ");
        Question questionToRemove = questionService.remove("Вопрос", "Ответ");
        assertNull(questionToRemove);
        assertEquals(0, questionService.getQuestionsCollectionSize());
    }

    @Test
    void removeShouldThrowWhenQuestionIsNotFound() {
        questionService.add(question1);
        assertThrows(QuestionNotFoundException.class, () -> questionService.remove(question2));
    }

    @Test
    void removeShouldThrowWhenQuestionIsNull() {
        assertThrows(InsufficientParamsException.class, () -> questionService.remove(null));
    }

    @ParameterizedTest
    @MethodSource("provideParamsForRemove")
    void removeShouldThrowWhenQuestionOrAnswerParamIsNull(String question, String answer) {
        assertThrows(InsufficientParamsException.class, () -> questionService.remove(question, answer));
    }

    public static Stream<Arguments> provideParamsForRemove() {
        return Stream.of(
                Arguments.of("Вопрос", null),
                Arguments.of(null, "Ответ"),
                Arguments.of(null, null)
        );
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
        mockedStatic.when(() -> RandomGenerator.getRandomNumber(2)).thenReturn(0);
        Question randomQuestion = questionService.getRandomQuestion();
        assertEquals(randomQuestion, question1);
    }
}