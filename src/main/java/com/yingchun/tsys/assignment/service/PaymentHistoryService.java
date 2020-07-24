package com.yingchun.tsys.assignment.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yingchun.tsys.assignment.model.PaymentHistory;
import com.yingchun.tsys.assignment.repo.PaymentHistoryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentHistoryService {
	
	@Autowired
	private PaymentHistoryRepository paymentHistoryRepository;
	private static Logger logger = LoggerFactory.getLogger(PaymentService.class);

	public List<PaymentHistory> getPaymentsHistoryBetween4Customer(Integer from, Integer to, Integer customerId) {
		
		return (List<PaymentHistory>)
				paymentHistoryRepository.findByCustomerIdAndFromAndTo(customerId, from, to);
	}

	public List<PaymentHistory> getPaymentsHistoryOfMonthAndYear4Customer(Integer year, Integer month,
			Integer customerId) {
		return (List<PaymentHistory>)
				paymentHistoryRepository.findByCustomerIdAndYearAndMonth(customerId,year, month);
	}
	
	public PaymentHistory createPaymentHistory(PaymentHistory paymentHistory) {

		try {
			paymentHistory = paymentHistoryRepository.save(paymentHistory);
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return paymentHistory;
	}

	public Optional<PaymentHistory> updatePaymentHistory(PaymentHistory paymentHistory) {
		if(paymentHistoryRepository.existsById(paymentHistory.getPaymentHistoryId()))
			return Optional.empty();
		
		try {
			paymentHistory = paymentHistoryRepository.save(paymentHistory);
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return Optional.of(paymentHistory);
	}

	public Optional<PaymentHistory> getPaymentHistoryById(Integer id) {
		return paymentHistoryRepository.findById(id);
	}

	public void deletePaymentHistoryById(Integer id) {
		paymentHistoryRepository.deleteById(id);
	}

	public Optional findById(Integer paymentHistoryId) {
		
		return paymentHistoryRepository.findById(paymentHistoryId);
	}
}