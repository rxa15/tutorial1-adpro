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
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

        when(productRepository.create(product)).thenReturn(product);
        productService.create(product);
    }

    @Test
    void testFindAll(){
        Product product = new Product();
        product.setProductId("155");
        product.setProductName("Susu Milo");
        product.setProductQuantity(15);

        when(productRepository.findAll()).thenReturn(List.of(product).iterator());
        Iterator <Product> productIterator = productService.findAll().iterator();

        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindProductById() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productRepository.findProductById("eb558e9f-1c39-460e-8860-71af6af63bd6")).thenReturn(product);
        Product savedProduct = productService.findProductById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        assertEquals(product, savedProduct);
    }

    @Test
    void testEditWhenProductExists() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        Product editedProduct = new Product();
        editedProduct.setProductId("1");
        editedProduct.setProductName("Sampo Cap Bango");
        editedProduct.setProductQuantity(30);
        productService.edit(editedProduct);

        verify(productRepository, times(1)).edit(editedProduct);
    }

    @Test
    void testGetProductById(){
        Product product = new Product();
        product.setProductId("abc");
        productRepository.create(product);

        Mockito.when(productService.findProductById("abc")).thenReturn(product);
        Product getProduct = productService.findProductById("abc");
        assertEquals(product, getProduct);
    }
    @Test
    void testDeleteByIdWhenProductExists() {
        Product product = new Product();
        product.setProductId("1");

        productService.deleteProductById("1");

        verify(productRepository, times(1)).deleteProductById("1");
    }
}