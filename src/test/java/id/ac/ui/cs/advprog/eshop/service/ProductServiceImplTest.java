package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductRepository productRepository;

    @BeforeEach
    void setUp(){

    }

    @Test
    void testCreateProduct(){
        Product product = new Product();
        product.setProductId("199");
        product.setProductName("Susu Hilo");
        product.setProductQuantity(18);

        Mockito.when(productRepository.create(product)).thenReturn(product);
        productService.create(product);
    }

    @Test
    void testFindAll(){
        Product product = new Product();
        product.setProductId("155");
        product.setProductName("Susu Milo");
        product.setProductQuantity(15);

        Mockito.when(productRepository.findAll()).thenReturn(List.of(product).iterator());
        Iterator <Product> productIterator = productService.findAll().iterator();

        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }
}