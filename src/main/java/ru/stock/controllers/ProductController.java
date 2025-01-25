package ru.stock.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.stock.dto.ProductDTO;
import ru.stock.entities.Product;
import ru.stock.servises.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products")
@Tag(name = "ProductController", description = "API продуктов")
@Validated
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(description = "Создание продукта")
    @PostMapping
    public ResponseEntity<Product> createProductController(@Parameter(description = "Создание продукта", required = true)
                                                           @RequestParam(value = "categoryId") Long categoryId,
                                                           @RequestBody(required = false) ProductDTO request) {
        return ResponseEntity.ok(productService.createProduct(request, categoryId));
    }

    @Operation(description = "Добавление продукта (количество)")
    @PutMapping(value = "add/{productId}")
    public ResponseEntity<String> addingProducts(@Parameter(description = "Добавление значения количества продукта")
                                                 @PathVariable(value = "productId") Long productId,
                                                 @RequestParam(value = "quantity", required = false) int quantity) {
        productService.addingProducts(productId, quantity);
        String titleProduct = productService.getTitleProductById(productId);
        String msg = "Для " +
                titleProduct + " ID: " + productId +
                " успешно добавлены продукты в количестве " +
                quantity + " шт.";
        return ResponseEntity.ok(msg);
    }

    @Operation(description = "Обновление продукта")
    @PutMapping(value = "update/{productId}")
    public ResponseEntity <Product> updateProduct(@Parameter(description = "Добавление значения количества продукта")
                                                           @PathVariable(value = "productId") Long productId,
                                                           @RequestBody(required = false) ProductDTO request) {
        ;
        return ResponseEntity.ok(productService.updateProduct(request, productId));
    }

    @Operation(description = "Получение списка продуктов")
    @GetMapping(value = "all", produces = {"application/json"})
    public ResponseEntity<List<Product>> getListAllProducts(@Parameter(description = "Список всех продуктов")
                                                            @RequestParam(value = "products", required = false) String allProducts) {
        return ResponseEntity.ok(productService.getListAllProducts());
    }

    @Operation(description = "Получение продукта(по ID)")
    @GetMapping(value = "product/{productId}")
    public ResponseEntity<Optional<Product>> getProductById(@Parameter(description = "Получение продукта(по ID)")
                                                            @PathVariable(value = "productId") Long productId) {
        return ResponseEntity.ok(Optional.ofNullable(productService.getProductById(productId)));
    }

    @Operation(description = "Получение продукта (по vendorCod(артикул))")
    @GetMapping(value = "search/{vendorCode}")
    public ResponseEntity<Product> getProductByVendorCode(@Parameter(description = "Получение продукта(по ID)")
                                                          @PathVariable(value = "vendorCode") String vendorCode) {
        return ResponseEntity.ok(productService.getProductByVendorCode(vendorCode));
    }

    @Operation(description = "Получение списка продуктов(по категории)")
    @GetMapping(value = "categories/{categoryId}", produces = {"application/json"})
    public ResponseEntity<List<Product>> getProductByCategory(@Parameter(description = "Список продуктов(по категории)")
                                                              @PathVariable Long categoryId) {
        return ResponseEntity.ok(productService.getProductByCategory(categoryId));
    }
    @Operation(description = "Получение списка продуктов(по наименованию)")
    @GetMapping(value = "titles/{titleProduct}", produces = {"application/json"})
    public ResponseEntity<List<Product>> getProductByTitleProduct(@Parameter(description = "Список продуктов(по наименованию)")
                                                                      @PathVariable(value ="titleProduct" ) String titleProduct){
        return ResponseEntity.ok(productService.getProductByTitleProduct(titleProduct));
    }

    @Operation(description = "Удаление продукта")
    @DeleteMapping(value = "{productId}")
    public ResponseEntity<Object> deleteProduct(@Parameter(description = "Удаление продукта по идентификатору", required = true)
                                                @PathVariable(value = "productId") Long productId){
     productService.deleteProduct(productId);
     return ResponseEntity.noContent().build();
    }
}
