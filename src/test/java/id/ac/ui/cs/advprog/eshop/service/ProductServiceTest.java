package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @InjectMocks
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    Product createProduct(String productName, String productId, int quantity){
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setProductQuantity(quantity);

        return product;
    }
}