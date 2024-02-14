package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        // Handle invalid input

        // Case 1: Blank product name
        if("".equals(product.getProductName())){
            product.setProductName("Produk Tidak Diketahui");
        }

        // Case 2: Negative product quantity
        if(product.getProductQuantity() < 0){
            product.setProductQuantity(0);
        }

        productData.add(product);
        return product;
    }

    public Product edit(Product product){
        // Handle invalid input
        // Case 1: Blank product name
        if("".equals(product.getProductName())){
            product.setProductName("Produk Tidak Diketahui");
        }

        // Case 2: Negative product quantity
        if(product.getProductQuantity() < 0){
            product.setProductQuantity(0);
        }

        for(Product currentProduct : productData){
            String currentProductId = currentProduct.getProductId();

            String productID = product.getProductId();
            String productName = product.getProductName();
            int productQuantity = product.getProductQuantity();

            if(currentProductId.equals(productID)){
                currentProduct.setProductName(productName);
                currentProduct.setProductQuantity(productQuantity);
                return currentProduct;
            }
        }
        return null; // product dijamin ditemukan di data product
    }

    public void delete(Product product){
        productData.remove(product);
    }

    public void deleteProductById(String productId){
        for(Product willBeDeletedProduct : productData){
            if(willBeDeletedProduct.getProductId().equals(productId)){
                delete(willBeDeletedProduct);
                break;
            }
        }
    }

    public Iterator<Product> findAll(){

        return productData.iterator();
    }

    public Product findProductById(String productId) {
        for (Product product : productData) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null;
    }
}