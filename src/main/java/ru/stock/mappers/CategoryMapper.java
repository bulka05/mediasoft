package ru.stock.mappers;

import org.mapstruct.Mapper;
import ru.stock.dto.CategoryDTO;
import ru.stock.entities.Category;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface CategoryMapper {
    Category toCategory(CategoryDTO dto);

}
