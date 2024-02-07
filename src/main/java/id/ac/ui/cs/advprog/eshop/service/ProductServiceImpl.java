package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    int counterId = 1;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product){
        productRepository.create(product);
        product.setProductId(String.valueOf(counterId));
        counterId += 1;
        return product;
    }

    public void edit(Product product){
        productRepository.edit(product);
    }

    @Override
    public Product getProductByID(String productId) {
        List<Product> allProduct = findAll();
        for(Product currentProduct : allProduct){
            if(currentProduct.getProductId().equals(productId)){
                return currentProduct;
            }
        }
        return null; // product dijamin ditemukan di data product
    }

    @Override
    public void deleteProductById(String productId) {
        productRepository.deleteProductById(productId);
    }

    @Override
    public List<Product> findAll(){
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    public Product findProductById(String productId) {
        return productRepository.findById(productId);
    }
}