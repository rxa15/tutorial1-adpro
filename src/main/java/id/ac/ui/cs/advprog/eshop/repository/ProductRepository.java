package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        productData.add(product);
        product.setProductId(String.valueOf(productData.size()));
        return product;
    }

    public Product edit(Product product){
        for(Product currentProduct : productData){

            int currentProductID = Integer.parseInt(currentProduct.getProductId());

            int productID = Integer.parseInt(product.getProductId());
            String productName = product.getProductName();
            int productQuantity = product.getProductQuantity();

            if(currentProductID == productID){
                currentProduct.setProductName(productName);
                currentProduct.setProductQuantity(productQuantity);
                return currentProduct;
            }
        }
        return null; // product dijamin ditemukan di data product
    }

    public void delete(int productId){
        productData.remove(productId);
    }

    public void delete(Product product){
        productData.remove(product);
    }

    public void deleteProductById(int productId){
        for(Product willBeDeletedProduct : productData){
            int willBeDeletedProductId = Integer.parseInt(willBeDeletedProduct.getProductId());

            if(willBeDeletedProductId == productId){
                delete(willBeDeletedProduct);
                break;
            }
        }
    }

    public Iterator<Product> findAll(){

        return productData.iterator();
    }
}