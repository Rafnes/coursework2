package me.skypro.coursework2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidQuestionsAmountRequestException extends IllegalArgumentException {
    private static final String MESSAGE = "Запрошено некорректное количество вопросов";

    public InvalidQuestionsAmountRequestException() {
        super(MESSAGE);
    }
}