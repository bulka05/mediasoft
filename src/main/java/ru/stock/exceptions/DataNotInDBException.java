package ru.stock.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DataNotInDBException extends RuntimeException{
    private static final String NOT_FOUND = "Данные не найдены в БД";
    public DataNotInDBException() {
        super(NOT_FOUND);
    }
}
