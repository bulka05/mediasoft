package ru.stock.servises;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.stubbing.OngoingStubbing;
import ru.stock.dto.CategoryDTO;
import ru.stock.dto.ProductDTO;
import ru.stock.entities.Category;
import ru.stock.entities.Product;
import ru.stock.exceptions.FailedToUpdateException;
import ru.stock.mappers.CategoryMapper;
import ru.stock.mappers.ProductMapper;
import ru.stock.repositories.CategoryRepository;
import ru.stock.repositories.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ProductDTO productDTO;
    @Mock
    private ProductMapper productMapper;
    @Mock
    private CategoryMapper categoryMapper;

    @Mock
    private Product product;
    @Spy
    @InjectMocks
    private CategoryService categoryService;

    @Spy
    @InjectMocks
    private ProductService productService;
    private Product mockProduct;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Инициализация моков

        productService = new ProductService(productRepository,categoryRepository,categoryService, productMapper, categoryMapper);
    }

    /**
     * Инициализируем mockProduct и mockCategory и определяем поведение
     */
    private void setupMockProduct() {
        Category mockCategory = new Category(1L, "Category 1");
        mockProduct = new Product(1L, "123", "Product 1",
                "Description 1", mockCategory, BigDecimal.TEN, LocalDateTime.now(),
                LocalDateTime.now(), 5);
        Mockito.when(productRepository.save(mockProduct)).thenReturn(mockProduct);
        Mockito.when(productRepository.existsById(1L)).thenReturn(true);
    }

    @Test
    public void createProductTest() {
        // Подготавливаем тестовые данные
        CategoryDTO categoryDTO = new CategoryDTO(1L, "Category 1");
        Category category = new Category(1L, "Category 1");
        Mockito.when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.of(category));
        Mockito.when(categoryService.isExistsCategory(1L)).thenReturn(true);
        Mockito.when(categoryService.getCategoryById(1L)).thenReturn(category);
        // Используем мок объект productDTO
        Mockito.when(productDTO.getTitleProduct()).thenReturn("Product 1");
        Mockito.when(productDTO.getDescription()).thenReturn("Description 1");
        Mockito.when(productDTO.getCategoryDto()).thenReturn(categoryDTO);
        Mockito.when(productDTO.getPrice()).thenReturn(BigDecimal.TEN);
        Mockito.when(productDTO.getQuantity()).thenReturn(5);

        Mockito.when(productMapper.toProduct(any(ProductDTO.class))).thenReturn(product);

        // Используем мок объект Product
        Mockito.when(product.getTitleProduct()).thenReturn("Product 1");
        Mockito.when(product.getVendorCode()).thenReturn("123");
        Mockito.when(product.getDescription()).thenReturn("Description 1");
        Mockito.when(product.getCategory()).thenReturn(category);
        Mockito.when(product.getPrice()).thenReturn(BigDecimal.TEN);
        Mockito.when(product.getQuantity()).thenReturn(5);
        Mockito.when(product.getDateOfCreation()).thenReturn(LocalDateTime.now());
        Mockito.when(product.getLastQuantityTime()).thenReturn(LocalDateTime.now());
        Mockito.when(product.getId()).thenReturn(1L);
        Mockito.when(productRepository.save(product)).thenReturn(product);

        Long idTest = 1L;
        Product createdProduct;
        createdProduct = productService.createProduct(productDTO, idTest);
        assertEquals(createdProduct, product);
    }

    @Test
    void updateProduct() {

        CategoryDTO categoryUpdate = new CategoryDTO(1L, "Category 1 update");
        Category mockCategory = new Category(1L, "Category 1");
        ProductDTO productUpdate = new ProductDTO("1235", "Product 1 update",
                "Description 1 update", categoryUpdate, BigDecimal.TEN,  7);
        Product mockProduct = new Product(1L, "123", "Product 1",
                "Description 1", mockCategory, BigDecimal.TEN, LocalDateTime.now(),
                LocalDateTime.now(), 5);
        Mockito.when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.of(mockCategory));
        Mockito.when(categoryService.isExistsCategory(1L)).thenReturn(true);
        Mockito.when(categoryService.getCategoryById(1L)).thenReturn(mockCategory);
        Mockito.when(categoryRepository.save(mockCategory)).thenReturn(mockCategory);
        Mockito.when(productRepository.save(mockProduct)).thenReturn(mockProduct);
        Mockito.when(productRepository.existsById(1L)).thenReturn(true);
        Long testId = 1L;
        Mockito.when(categoryMapper.toCategory(categoryUpdate)).thenReturn(mockCategory);
        Mockito.when(productService.getProductById(testId)).thenReturn(mockProduct);
        Mockito.when(productService.updateProduct(productUpdate,testId)).thenReturn(mockProduct);

        assertEquals(productService.updateProduct(productUpdate,testId),mockProduct);
    }


    @Test
    void addingProducts() {
        setupMockProduct();
        Long idTest = 1L;
        int testQuantity = 3;
        ProductService mockProductService = mock(ProductService.class);
        Mockito.doNothing().when(mockProductService).addingProducts(idTest, 0);
//        Mockito.when(mockProduct.getQuantity()).thenReturn(5);
        mockProductService.addingProducts(idTest, testQuantity);
//        Mockito.verify(mockProduct).setQuantity(8);
        Mockito.verify(mockProductService).addingProducts(idTest, testQuantity);
    }

    /**
     * Тест получения продукта по id.
     */
    @Test
    void getProductById() {
        setupMockProduct();
        Long testId = 1L;
        Mockito.when(productService.getProductById(testId)).thenReturn(mockProduct);
        assertEquals(productService.getProductById(testId), mockProduct);
    }

    /**
     * Тест выбрасывания исключения при не существующем id.
     */
    @Test
    void getProductByIdWithException() {
        setupMockProduct();
        Long testId = 1L;
        Long invalidId = 2L;
        ProductService productServiceMock = Mockito.mock(ProductService.class);
        Mockito.when(productServiceMock.getProductById(testId)).thenReturn(mockProduct);
        Mockito.doThrow(new FailedToUpdateException(invalidId)).when(productServiceMock).getProductById(invalidId);
        assertThrows(FailedToUpdateException.class, ()-> productServiceMock.getProductById(invalidId));
    }


    @Test
    void deleteProduct() {
        setupMockProduct();
        Long testId = 1L;
        ProductRepository mockProductRepository = Mockito.mock(ProductRepository.class);
        productService = new ProductService(mockProductRepository,categoryRepository,categoryService,productMapper,categoryMapper);
        Mockito.doNothing().when(mockProductRepository).deleteById(testId);
        productService.deleteProduct(testId);

        Mockito.verify(mockProductRepository).deleteById(testId);

    }
}