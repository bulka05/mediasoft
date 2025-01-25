package ru.stock.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.stock.dto.CategoryDTO;
import ru.stock.entities.Category;
import ru.stock.servises.CategoryService;

import java.util.List;

@RestController
@RequestMapping("categories")
@Tag(name = "CategoryController", description = "API категорий продуктов")
@Validated

public class CategoryController {
    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @Operation(description = "Добавление/создание категории")
    @PostMapping
    public ResponseEntity<Category> createProductController(@Parameter(description = "Запрос на создание категории продукта", required = true)
                                                                @RequestBody(required = false) CategoryDTO request) {
        return ResponseEntity.ok(categoryService.createCategory(request));
    }
    @Operation(description = "Редактирование категории")
    @PutMapping(value = "{category_id}")
    public ResponseEntity<Category> updateCategory(@Parameter(description = "Запрос обновления/редактирования категории", required = true)
                                                   @RequestParam(value = "titleCategory") String titleCategory, @PathVariable("category_id") Long categoryId){
        return ResponseEntity.ok(categoryService.updateCategory(titleCategory,categoryId));
    }

    @Operation(description = "Получение списка категорий")
    @GetMapping(value = "all", produces = {"application/json"})
    public ResponseEntity<List<Category>> getAllCategories(@Parameter(description = "Получение списка категорий")
                                                           @RequestParam(value = "allCategories") String allCategories){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @Operation(description = "Получение категории")
    @GetMapping(value = "{categoryId}")
    public ResponseEntity<Category> getCategoryById(@Parameter(description = "Получение категории")
                                                     @PathVariable(value = "categoryId") Long categoryId){
        return ResponseEntity.ok(categoryService.getCategoryById(categoryId));
    }

    @Operation(description = "Получение категории по наименованию")
    @GetMapping(value = "title/{titleCategory}")
    public ResponseEntity<Category> getCategoryByTitle(@Parameter(description = "Получение категории по наименованию")
                                                    @PathVariable(value = "titleCategory") String titleCategory){
        return ResponseEntity.ok(categoryService.getCategoryByTitle(titleCategory));
    }

    @Operation(description = "Удаление категории")
    @DeleteMapping(value = "{categoryId}")
    public ResponseEntity<Object> deleteCategoryById(@Parameter(description = "Удаление категории по идентификатору", required = true)
                                                @PathVariable(value = "categoryId") Long productId){
        categoryService.deleteCategoryById(productId);
        return ResponseEntity.noContent().build();
    }

}
