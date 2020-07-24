package com.yingchun.tsys.assignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.yingchun.tsys.assignment.model.PaymentHistory;
import com.yingchun.tsys.assignment.service.CustomerService;
import com.yingchun.tsys.assignment.service.PaymentHistoryService;

/**
 * 1.	Create a new REST API using Java/JVM technologies with the following endpoints:
 * HTTP Method	Route	Description
 * GET	/history/payments/{customerId}/{from}/{to}	List all payments
**/

@RestController
public class PaymentHistoryController {

	@Autowired 
	private PaymentHistoryService paymentHistoryService;
		
	@GetMapping(path = "/history/payments/{customerId}/{from}/{to}", produces = "application/json")
	public List<PaymentHistory>  findcustomerFromToByCustomerId(@PathVariable Integer customerId,
			@PathVariable Integer from, @PathVariable Integer to
			) throws Throwable {
			return	paymentHistoryService.getPaymentsHistoryBetween4Customer(from, to, customerId);
	}
	
	@GetMapping(path = "/history/payments/{customerId}/{year}/{month}", produces = "application/json")
	public List<PaymentHistory>  findPaymentHistoryOfMonthByCustomerId(@PathVariable Integer customerId,
			@PathVariable Integer year, @PathVariable Integer month
			) throws Throwable {
		return	paymentHistoryService.getPaymentsHistoryOfMonthAndYear4Customer(year, month, customerId);
	}
}