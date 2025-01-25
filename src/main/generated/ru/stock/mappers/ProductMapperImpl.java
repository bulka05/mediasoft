package ru.stock.mappers;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.stock.dto.ProductDTO;
import ru.stock.entities.Product;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-02T14:13:35+0400",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toProduct(ProductDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setVendorCode( dto.getVendorCode() );
        product.setTitleProduct( dto.getTitleProduct() );
        product.setDescription( dto.getDescription() );
        product.setPrice( dto.getPrice() );
        product.setQuantity( dto.getQuantity() );

        return product;
    }
}
