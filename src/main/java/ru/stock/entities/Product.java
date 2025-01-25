package ru.stock.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.stock.dto.CategoryDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Сущность Product (товар) содержит поля:
 * id продукта
 * vendorCode артикул товара
 * description описание товара
 * category категория товара
 * price цена товара
 * lastQuantityTime дата и время последнего изменения количества
 * dateOfCreation дата создания.
 * quantity количество.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "vendor_code", unique = true)
    private String vendorCode;

    @Column(name = "title")
    private String titleProduct;

    @Column(name = "description")
    private String description;

    @Setter
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Category category;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "last_quantity_time")
    private LocalDateTime lastQuantityTime;

    @Column(name = "date_of_creation")
    private LocalDateTime dateOfCreation;

    @Column(name = "quantity")
    private int quantity;

}
