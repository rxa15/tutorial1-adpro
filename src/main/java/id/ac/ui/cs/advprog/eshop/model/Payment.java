package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import lombok.Getter;

import java.util.Arrays;
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

    private void setPaymentStatus(String status){
        if(PaymentStatus.contains(status)){
            this.status = status;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    private void setPaymentData(Map<String, String>paymentData){
        if (method.equals(PaymentMethod.VOUCHER_CODE.getValue())){
            int numbers = 0;
            for (int i=0; i < paymentData.get("voucherCode").length(); i++){
                if (Character.isDigit(paymentData.get("voucherCode").charAt(i))){
                    numbers += 1;
                }
            }
            if (paymentData.get("voucherCode").length()!= 16 ||
                    !paymentData.get("voucherCode").startsWith("ESHOP") ||
                    numbers != 8){
                throw new IllegalArgumentException("Voucher Code is invalid!");
            }
        } else if (method.equals(PaymentMethod.COD.getValue())){
            if (paymentData.get("address").isBlank() ||
                    paymentData.get("deliveryFee").isBlank()){
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }
        this.paymentData = paymentData;
    }
}