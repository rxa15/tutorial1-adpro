package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public Product getProductByID(int productId);
    public void edit(Product product);
    public void deleteProductById(int productId);
    public List<Product> findAll();
}