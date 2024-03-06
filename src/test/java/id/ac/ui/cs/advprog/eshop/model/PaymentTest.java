package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PaymentTest {
    private List<Payment> payments;
    private List<Product> products;
    private List<Order> orders;

    @BeforeEach
    void setUp(){
        // Set up products
        this.products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);

        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);

        this.products.add(product1);
        this.products.add(product2);

        // Set up orders
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", this.products,
                1708560000L, "Safira Sudrajat");
        Order order2 = new Order("93652556-012a-4c07-b546-54eb1396d79b", this.products,
                1708570000L, "Tengku Laras");

        this.orders.add(order1);
        this.orders.add(order2);
    }

    @Test
    void testCreatePaymentWithEmptyMethod(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678RXA");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment(new Order(orders.get(0).getId(), orders.get(0).getProducts(), orders.get(0).getOrderTime(),
                    orders.get(0).getAuthor()), "", "SUCCESS", paymentData);
        }, "Payment Method cannot be empty!");
    }

    @Test
    void testCreatePaymentWithNoStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678RXA");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment(new Order(orders.get(0).getId(), orders.get(0).getProducts(), orders.get(0).getOrderTime(),
                    orders.get(0).getAuthor()), "Voucher Code", "", paymentData);
        }, "Payment Status cannot be empty!");
    }

    @Test
    void testCreatePaymentSuccess(){
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP12345678RXA");

        Payment payment = new Payment("13652556-0115-4c07-b546-54eb1396d79b", orders.get(0),
                "Voucher Code", "SUCCESS", paymentData);

        assertEquals("13652556-0115-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals("Voucher Code", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(orders.get(0), payment.getOrder());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentInvalidStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678RXA");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment(new Order(orders.get(0).getId(), orders.get(0).getProducts(), orders.get(0).getOrderTime(),
                    orders.get(0).getAuthor()), "Voucher Code", "WKWKWKWKW", paymentData);
        }, "Payment Status is invalid!");
    }

    @Test
    void testEditPaymentStatusWithValidStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678RXA");

        Payment payment = new Payment("13652556-0115-4c07-b546-54eb1396d79b", orders.get(0),
                "Voucher Code", "REJECTED", new HashMap<>());
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testEditPaymentStatusWithInvalidStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP12345678RXA");

        Payment payment = new Payment("13652556-0115-4c07-b546-54eb1396d79b", orders.get(0),
                "Voucher Code", "SUCCESS", paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("WKWKWK");
        }, "Payment Status is invalid!");
    }

    @Test
    void testCreatePaymentWithInvalidVoucherCode(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "HAHAHAHAH");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("13652556-0115-4c07-b546-54eb1396d79b", orders.get(0),
                    "Voucher Code", "SUCCESS", paymentData);}, "Voucher Code is invalid!");
    }

    @Test
    void testCreatePaymentWithVoucherCodeWithWrongPrefix(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOY12345678RXA");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("13652556-0115-4c07-b546-54eb1396d79b", orders.get(0),
                    "Voucher Code", "SUCCESS", paymentData);}, "Voucher Code is invalid!");
    }

    @Test
    void testCreatePaymentWithVoucherCodeWithWrongLength(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP123456789RXA");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("13652556-0115-4c07-b546-54eb1396d79b", orders.get(0),
                    "Voucher Code", "SUCCESS", paymentData);}, "Voucher Code is invalid!");
    }

    @Test
    void testCreateCODPaymentSuccess(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jl. Seno");
        paymentData.put("deliveryFee", "20000");

        Payment payment = new Payment("13652556-0115-4c07-b546-54eb1396d79b", orders.get(1),
                "COD", "SUCCESS", paymentData);

        assertEquals("13652556-0115-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals("COD", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(orders.get(1), payment.getOrder());
        assertEquals(paymentData, payment.getPaymentData());
    }

    @Test
    void testCreateCODBlankAddress(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "");
        paymentData.put("deliveryFee", "20000");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("13652556-0115-4c07-b546-54eb1396d79b", orders.get(1), "COD", "SUCCESS", paymentData);
        });
    }

    @Test
    void testCreateCODBlankPaymentFee(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jl. Seno");
        paymentData.put("deliveryFee", "");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("13652556-0115-4c07-b546-54eb1396d79b", orders.get(1), "COD", "SUCCESS", paymentData);
        });
    }
}