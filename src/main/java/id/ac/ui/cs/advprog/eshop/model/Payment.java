package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class Payment {
    String id;
    Order order;
    String method;
    String status;
    Map<String, String> paymentData;

    public Payment(String id,  Order order, String method, String status, Map<String, String>paymentData){
        this.id = id;
        this.order = order;
        this.method = method;
        setPaymentData(paymentData);

        try{
            setPaymentData(paymentData);
            setPaymentStatus(PaymentStatus.SUCCESS.getValue());
        }catch (IllegalArgumentException e){
            setPaymentStatus(PaymentStatus.REJECTED.getValue());
            throw new IllegalArgumentException();
        }
    }
    public Payment(Order order, String method, String status, Map<String, String>paymentData){
        if (method == null || method.trim().isEmpty()) {
            throw new IllegalArgumentException("Method cannot be empty");
        }
        this.id = UUID.randomUUID().toString();
        this.method = method;
        setPaymentStatus(status);
        this.order = order;
        setPaymentData(paymentData);
    }

    public void setPaymentStatus(String status){
        if(PaymentStatus.contains(status)){
            this.status = status;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    protected void setPaymentData(Map<String, String>paymentData){
        if(PaymentMethod.contains(this.method)){
            this.paymentData = paymentData;
        } else{
            throw new IllegalArgumentException();
        }
    }
}