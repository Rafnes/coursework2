package me.skypro.coursework2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class QuestionNotFoundException extends IllegalArgumentException {
    private static final String MESSAGE = "Такой вопрос не найден";

    public QuestionNotFoundException() {
        super(MESSAGE);
    }
}
