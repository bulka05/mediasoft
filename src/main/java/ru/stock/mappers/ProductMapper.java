package ru.stock.mappers;

import org.mapstruct.Mapper;
import ru.stock.dto.ProductDTO;
import ru.stock.entities.Product;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface ProductMapper {

    Product toProduct(ProductDTO dto);

}
