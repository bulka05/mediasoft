package ru.stock.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.stock.dto.CategoryDTO;
import ru.stock.entities.Category;
import ru.stock.exceptions.DataNotInDBException;
import ru.stock.mappers.CategoryMapper;
import ru.stock.repositories.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public Category createCategory(CategoryDTO dto) {
        Category category = categoryMapper.toCategory(dto);
        category.setTitleCategory(dto.getTitleCategory());
        categoryRepository.save(category);
        return category;
    }

    public Category updateCategory(String titleCategory, Long categoryId){
        Category category = getCategoryById(categoryId);
        category.setTitleCategory(titleCategory);
        categoryRepository.updateTitleCategoryById(titleCategory,categoryId);
        return category;
    }

    public Category getCategoryById (Long categoryId) throws DataNotInDBException {
        if (!isExistsCategory(categoryId)) {
            throw  new DataNotInDBException();
        }
        return categoryRepository.findCategoryById(categoryId);
    }

    public boolean isExistsCategory(Long categoryId){
        return categoryRepository.existsById(categoryId);
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategoryByTitle(String titleCategory){
        return categoryRepository.findByTitleCategory(titleCategory);
    }

    public void deleteCategoryById(Long categoryId){
        categoryRepository.deleteById(categoryId);
    }
}
