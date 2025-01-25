package ru.stock.mappers;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.stock.dto.CategoryDTO;
import ru.stock.entities.Category;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-02T14:13:35+0400",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toCategory(CategoryDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( dto.getId() );
        category.setTitleCategory( dto.getTitleCategory() );

        return category;
    }
}
