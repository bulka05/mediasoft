package ru.stock.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorMessage {
//    LocalDateTime time = LocalDateTime.now();
//    String path;
    private String message;
}
