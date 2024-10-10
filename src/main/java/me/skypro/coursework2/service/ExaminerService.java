package me.skypro.coursework2.service;

import me.skypro.coursework2.domain.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
