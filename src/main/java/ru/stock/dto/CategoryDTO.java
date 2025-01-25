package ru.stock.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class CategoryDTO implements Serializable {

    @Schema(description = "ID продукта")
    private final Long id;
    @NonNull
    @Schema(description = "Название категории")
    private final String titleCategory;
}
