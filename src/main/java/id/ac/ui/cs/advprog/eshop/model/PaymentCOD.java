package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class PaymentCOD extends Payment {
    public PaymentCOD(Order order, String method, String status, Map<String, String> paymentData){
        super(order, method, status, paymentData);
    }
    public PaymentCOD(String id, Order order, String method, String status, Map<String, String>paymentData){
        super(id, order, method, status, paymentData);
    }

    @Override
    protected void setPaymentData(Map<String, String> paymentData) {
        if (paymentData.get("address").isBlank() ||
                paymentData.get("deliveryFee").isBlank()){
            throw new IllegalArgumentException();
        }
        this.paymentData=paymentData;
    }
}