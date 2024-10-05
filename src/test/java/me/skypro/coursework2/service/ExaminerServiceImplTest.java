package me.skypro.coursework2.service;

import me.skypro.coursework2.domain.Question;
import me.skypro.coursework2.exceptions.InvalidQuestionsAmountRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    Question question1 = new Question("Вопрос 1", "Ответ 1");
    Question question2 = new Question("Вопрос 2", "Ответ 2");
    Question question3 = new Question("Вопрос 3", "Ответ 3");
    Question question4 = new Question("Вопрос 4", "Ответ 4");

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    void shouldThrowIfAmountIsZeroOrNegative() {
        assertThrows(InvalidQuestionsAmountRequestException.class, () -> examinerService.getQuestions(0));
        assertThrows(InvalidQuestionsAmountRequestException.class, () -> examinerService.getQuestions(-1));
        verify(questionService, never()).getRandomQuestion();
    }

    @Test
    void shouldThrowIfAmountIsBiggerThanCollection() {
        when(questionService.getAll()).thenReturn(Arrays.asList(question1, question2));

        assertThrows(InvalidQuestionsAmountRequestException.class, () -> examinerService.getQuestions(5));
        verify(questionService, never()).getRandomQuestion();
    }

    @Test
    void shouldReturnUniqueQuestions() {
        when(questionService.getAll()).thenReturn(Arrays.asList(question1, question2, question3, question4));

        Collection<Question> actual = examinerService.getQuestions(4);
        Set<Question> expected = new HashSet<>(actual);

        assertEquals(actual.size(), expected.size());
        assertTrue(actual.containsAll(expected));
        verify(questionService, atLeastOnce()).getAll();
    }

}