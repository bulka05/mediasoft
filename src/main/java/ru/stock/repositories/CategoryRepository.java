package ru.stock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.stock.entities.Category;
@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    Long findIdByTitleCategory(String titleCategory);
    Category findByTitleCategory(String titleCategory);

    @Query("SELECT c FROM Category c WHERE c.id = :categoryId")
    Category findCategoryById(@Param("categoryId")Long categoryId);

    @Transactional
    @Modifying
    @Query("update Category c set c.titleCategory = ?1 where c.id = ?2")
    void updateTitleCategoryById(String titleCategory, Long id);


}
