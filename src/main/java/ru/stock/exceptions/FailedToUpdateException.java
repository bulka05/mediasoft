package ru.stock.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class FailedToUpdateException extends RuntimeException {
    private static final String ID_IS_NOT_DB = "ID нет в БД";
    

    private static String createErrorMessage(Long id) {
        return "Продукта с таким "  + ID_IS_NOT_DB + ", ID product: " + id;
    }

    public FailedToUpdateException(Long id){
        super(createErrorMessage(id));
    }
}
