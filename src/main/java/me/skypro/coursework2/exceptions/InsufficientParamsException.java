package me.skypro.coursework2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InsufficientParamsException extends IllegalArgumentException {
    private static final String MESSAGE = "Не подан один или оба параметра";

    public InsufficientParamsException() {
        super(MESSAGE);
    }
}
