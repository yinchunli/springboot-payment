package com.yingchun.tsys.assignment.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yingchun.tsys.assignment.model.Payment;
import com.yingchun.tsys.assignment.model.PaymentHistory;
import com.yingchun.tsys.assignment.repo.PaymentRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	private static Logger logger = LoggerFactory.getLogger(PaymentService.class);
	   
	public List<Payment> getAllPayments4Customer(Integer customerId) {
		return (List<Payment>) paymentRepository.findByCustomerId(customerId);
	}

	public Payment addPayment(Payment payment) {

		try {
			payment = paymentRepository.save(payment);
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return payment;
	}

	public Optional<Payment> updatePayment(Payment payment, Integer id) {
		if(!paymentRepository.existsById(payment.getPaymentId()))
			return Optional.empty();
		
		try {
			payment = paymentRepository.save(payment);
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return Optional.of(payment);
	}

	public Optional<Payment> getPaymentById(Integer id) {
		return paymentRepository.findById(id);
	}

	public void deletePaymentById(Integer id) {
		paymentRepository.deleteById(id);
	}

	public Optional findById(Integer paymentId) {
		return paymentRepository.findById(paymentId);
	}

	public List<Payment> getPaymentsHistoryBetween4PaymentHistory(Integer customerId, Integer year, Integer from, Integer to) {
		return (List<Payment>)
				paymentRepository.findByCustomerIdAndYearAndMonthBetween(customerId, year, from, to);
	}

	public List<Payment> getPaymentsHistoryOfMonthAndYear4PaymentHistory(Integer year, Integer month,
			Integer customerId) {
		return (List<Payment>)
				paymentRepository.findByCustomerIdAndYearAndMonth(year, month, customerId);
	}
}