package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator <Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator <Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator <Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductId("0118");
        product.setProductName("Susu Hilo");
        product.setProductQuantity(10);
        productRepository.create(product);

        Product editedProduct = new Product();
        editedProduct.setProductId("0118");
        editedProduct.setProductName("Susu Milo");
        editedProduct.setProductQuantity(20);
        Product updatedProduct = productRepository.edit(editedProduct);

        assertNotNull(updatedProduct);
        assertEquals("Susu Milo", updatedProduct.getProductName());
        assertEquals(20, updatedProduct.getProductQuantity());
    }

    @Test
    void testEditProductNegative() {
        Product editedProduct = new Product();
        editedProduct.setProductId("0115");
        editedProduct.setProductName("Susu Greenfield");
        editedProduct.setProductQuantity(-20);

        productRepository.create(editedProduct);
        assertEquals(0, editedProduct.getProductQuantity());
    }

    @Test
    void testBlankProductNameHandling(){
        Product product = new Product();
        product.setProductId("222");
        product.setProductName("");
        product.setProductQuantity(10);

        productRepository.create(product);
        assertEquals("Produk Tidak Diketahui", product.getProductName());
    }

    @Test
    void testNegativeProductQuantityHandling(){
        Product product = new Product();
        product.setProductId("333");
        product.setProductName("Susu Milo");
        product.setProductQuantity(-22);

        productRepository.create(product);

        assertEquals(0, product.getProductQuantity());
    }

    @Test
    void testEditExistingProduct(){
        Product existingProduct = new Product();
        existingProduct.setProductId("1054");
        existingProduct.setProductName("Susu Hilo");
        existingProduct.setProductQuantity(10);

        productRepository.create(existingProduct);

        existingProduct.setProductName("Susu Milo");
        Product editedProduct = productRepository.edit(existingProduct);

        assertEquals(existingProduct, editedProduct);
    }

    @Test
    void testEditBlankProductName(){
        Product product = new Product();
        product.setProductName("Susu Milo");
        product.setProductId("2318");
        product.setProductQuantity(13);

        productRepository.create(product);

        product.setProductName("");
        Product editedProduct = productRepository.edit(product);

        assertEquals("Produk Tidak Diketahui", product.getProductName());
    }

    @Test
    void testEditNegativeProductQuantity(){
        Product product = new Product();
        product.setProductName("Susu Milo");
        product.setProductId("2318");
        product.setProductQuantity(13);

        productRepository.create(product);

        product.setProductQuantity(-99);
        Product editedProduct = productRepository.edit(product);

        assertEquals(0, product.getProductQuantity());
    }

    @Test
    void testEditNotExistingProduct(){
        Product product = new Product();
        product.setProductName("Susu Milo");
        product.setProductId("2318");
        product.setProductQuantity(13);

        Product editedProduct = productRepository.edit(product);

        assertNull(editedProduct);

    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af630115");
        product.setProductName("Susu Hilo");
        product.setProductQuantity(10);
        productRepository.create(product);

        productRepository.delete(product);

        Product deletedProduct = productRepository.findProductById("eb558e9f-1c39-460e-8860-71af6af630115");
        assertNull(deletedProduct);
    }

    @Test
    void testDeleteProductById(){
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af630115");
        product.setProductName("Susu Hilo");
        product.setProductQuantity(10);
        productRepository.create(product);

        productRepository.deleteProductById(product.getProductId());
        productRepository.deleteProductById("hahahaha");

        Product deletedProduct = productRepository.findProductById(product.getProductId());
        assertNull(deletedProduct, "The product should have been deleted");
    }

    @Test
    void testDeleteNonExistingProductById(){
        Product product = new Product();
        product.setProductId("non_existing_id");
        product.setProductName("Non Existing Product");
        product.setProductQuantity(0);

        productRepository.deleteProductById(product.getProductId());

        Product deletedProduct = productRepository.findProductById("non_existing_id");
        assertNull(deletedProduct);
    }

    @Test
    public void testDeleteMultipleProducts() {
        // Create two products
        Product product1 = new Product();
        product1.setProductName("Product1");
        product1.setProductQuantity(3);
        Product product2 = new Product();
        product2.setProductName("Product2");
        product2.setProductQuantity(99);

        productRepository.create(product1);
        productRepository.create(product2);

        productRepository.delete(product1);
        productRepository.delete(product2);

        assertNotNull(product1);

        assertNotNull(product2);

        Iterator<Product> iterator = productRepository.findAll();
        assertFalse(iterator.hasNext());
    }

    @Test
    void testFindProductById(){
        Product product = new Product();
        product.setProductName("Susu Milo");
        product.setProductId("2318");
        product.setProductQuantity(13);

        productRepository.create(product);

        Product findProduct = productRepository.findProductById("2318");

        assertEquals(product, findProduct);
    }

    @Test
    void findNotExistingProductById(){
        Product product = new Product();
        product.setProductName("Susu Milo");
        product.setProductId("2318");
        product.setProductQuantity(13);

        Product findProduct = productRepository.findProductById("2318");

        assertNull(findProduct);
    }
}