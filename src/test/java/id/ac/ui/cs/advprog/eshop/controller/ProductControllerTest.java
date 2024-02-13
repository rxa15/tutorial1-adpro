package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;

import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.springframework.ui.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    @Mock
    private ProductServiceImpl productService;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProductPage(){
        Model model = mock(Model.class);
        String result = productController.createProductPage(model);
        assertEquals("createProduct", result);
    }

    @Test
    void testCreateProductPost(){
        Model model = mock(Model.class);
        Product product = new Product();
        Mockito.when(productService.create(product)).thenReturn(product);
        String result = productController.createProductPost(product, model);
        assertEquals("redirect:list", result);
    }

    @Test
    void testProductListPage() {
        Model model = mock(Model.class);
        Mockito.when(productService.findAll()).thenReturn(null);
        String result = productController.productListPage(model);
        assertEquals("listProduct", result);
    }

    @Test
    void testEditProductPage(){
        Model model = mock(Model.class);
        Product product = new Product();
//        Mockito.when(productService.findProductById("eb558e9f-1c39-460e-8860-71af6af63bd6")).thenReturn(product);
        String result = productController.editProductPage(model,"eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertEquals("editProduct", result);
    }

    @Test
    void testEditProductPost(){
        Model model = mock(Model.class);
        Product product = new Product();
        productService.edit(product);
        String result = productController.editProductPost(product);
        assertEquals("redirect:../list", result);
    }
    @Test
    void testDeleteProductPage(){
        Product product = new Product();

        Mockito.when(productService.findProductById("eb558e9f-1c39-460e-8860-71af6af63bd6")).thenReturn(product);
        String result = productController.deleteProductPage("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertEquals("redirect:/product/list", result);
    }
}
