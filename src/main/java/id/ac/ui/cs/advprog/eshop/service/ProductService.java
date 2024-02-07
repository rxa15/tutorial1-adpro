package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public Product getProductByID(String productId);
    public void edit(Product product);
    public void deleteProductById(String productId);
    public List<Product> findAll();
    public Product findProductById(String productId);
}