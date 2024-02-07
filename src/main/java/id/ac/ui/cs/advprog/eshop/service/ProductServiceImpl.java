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

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product){
        productRepository.create(product);
        return product;
    }

    public void edit(Product product){
        productRepository.edit(product);
    }

    @Override
    public Product getProductByID(int productId) {
        List<Product> allProduct = findAll();
        for(Product currentProduct : allProduct){
            int currentProductID = Integer.parseInt(currentProduct.getProductId());
            if(currentProductID == productId){
                return currentProduct;
            }
        }
        return null; // product dijamin ditemukan di data product
    }

    @Override
    public void deleteProductById(int productId) {
        productRepository.deleteProductById(productId);
    }

    @Override
    public List<Product> findAll(){
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }
}