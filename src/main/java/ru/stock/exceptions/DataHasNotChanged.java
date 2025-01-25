package ru.stock.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DataHasNotChanged extends IllegalArgumentException {
    private static final String HAS_NOT_CHANGED = " значения в БД не изменилось";

    public DataHasNotChanged(String titleField, Long productId) {
        super(createErrorMessage(titleField, productId));
    }

    private static String createErrorMessage(String titleField, Long id) {
        return "Нулевое или не заполнено поле: " + titleField + ": " + HAS_NOT_CHANGED + ", продукт с ID: " + id;
    }
}
