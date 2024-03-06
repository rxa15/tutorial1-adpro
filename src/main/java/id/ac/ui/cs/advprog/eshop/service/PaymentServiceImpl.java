package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.PaymentCOD;
import id.ac.ui.cs.advprog.eshop.model.PaymentVoucher;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment addPayment (String id, Order order, String method, Map<String, String> paymentData){
        if (paymentRepository.findById(id) == null){

            Payment payment;
            switch (method){
                case "VOUCHER_CODE":
                    payment = new PaymentVoucher(id, order, method, order.getStatus(), paymentData);
                    break;
                case "CASH_ON_DELIVERY":
                    payment = new PaymentCOD(id, order, method, order.getStatus(), paymentData);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
            paymentRepository.save(payment);
            return payment;
        }
        return null;
    }
    public Payment setStatus(Payment payment, String status){
        Payment savedPayment = paymentRepository.findById(payment.getId());
        if (savedPayment != null){

            savedPayment.setPaymentStatus(status);
            paymentRepository.save(savedPayment);
            return savedPayment;

        }else{
            throw new NoSuchElementException();
        }
    }
    public Payment getPayment(String paymentId){
        return paymentRepository.findById(paymentId);
    }
    public List<Payment> getAllPayments(){
        return paymentRepository.getAllPayment();
    }
}