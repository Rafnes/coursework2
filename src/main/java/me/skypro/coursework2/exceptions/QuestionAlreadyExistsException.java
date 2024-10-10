package me.skypro.coursework2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuestionAlreadyExistsException extends IllegalArgumentException {

    public QuestionAlreadyExistsException() {
        super("Вопрос уже существует");
    }
}
