package com.yingchun.tsys.assignment.controller;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yingchun.tsys.assignment.exception.ResourceNotFoundException;
import com.yingchun.tsys.assignment.model.Payment;
import com.yingchun.tsys.assignment.service.PaymentService;
import com.yingchun.tsys.assignment.service.ServiceAdapter;
import com.yingchun.tsys.assignment.util.ResponseUtil;

/**
 * 1.	Create a new REST API using Java/JVM technologies with the following endpoints:
 * HTTP Method	Route	Description
 * GET	/payments/customer/{customerId}	List all payments for customer
 * POST	/payments	Create a new payment
 * PUT	/payments/{paymentId}	Update a payment
 * DELETE	/payments/{paymentId}	Delete a payment
 * GET	/payments/{paymentId}	Get a specific payment
 * GET	/payments/customer/history/{customerId}/{year}/{month} List all payments for customer on the month of the year
 * GET	/payments/customer/{customerId}/{year}/{fromMonth}/{toMonth}  List all payments for customer on the year in between
 * 
 * 
**/

@RestController
public class PaymentController {

	@Autowired 
	private PaymentService paymentService;

	@Autowired
	Environment environment;

	@Autowired
	ServiceAdapter systemServiceA;
	
	@Autowired
	ServiceAdapter systemServiceB;
	
	@GetMapping(path="/payments/customer/{customerId}", produces = "application/json")
	public List<Payment> findAllPayments(@PathVariable Integer customerId) {
		return paymentService.getAllPayments4Customer(customerId);
	}
	
	@PostMapping(path = "/payments", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Payment> savePayment(@RequestBody Payment payment) throws Throwable {
		Optional<Payment> paymentOptional = Optional.of(paymentService.addPayment(payment));
		
		return (ResponseEntity<Payment>) paymentOptional.map( c -> 
				ResponseEntity.created(ResponseUtil.resourceUri(payment.getPaymentId()))
				.body(c)
		).orElseThrow(()-> new ResourceNotFoundException(
            "Payment: " + payment.getPaymentId() + " not created.")
		);
	}

	@PutMapping(path ="/payments/{paymentId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Payment> updatepayment(@RequestBody Payment payment, @PathVariable Integer paymentId) throws Throwable {
		Optional<Payment> paymentOptional = paymentService.updatePayment(payment, paymentId);
 		return (ResponseEntity<Payment>) 
 				paymentOptional.map(c ->
					ResponseEntity
					.ok()
					.location(ResponseUtil.resourceUri(paymentId))
 					.body(c)
            )
            .orElseThrow(() -> new ResourceNotFoundException(
                            "Payment: " + paymentId + " not found"
                    )
            );
	}

	@GetMapping(path = "/payments/{id}", produces = "application/json")
	public ResponseEntity<Payment>  findPaymentById(@PathVariable Integer id) throws Throwable {
		Optional<Payment> paymentOptional = paymentService.getPaymentById(id);
		return  (ResponseEntity<Payment>) paymentOptional.map(c ->
					ResponseEntity
							.ok()
//							.location(ResponseUtil.resourceUri(id))
							.body(c)
				)
			.orElseThrow(() -> new ResourceNotFoundException(
			                "paymentId: " + id + " not found"
			        )
			);
	}

	@SuppressWarnings("unchecked")
	@DeleteMapping(value="/payments/{paymentId}", produces = "application/text")
	public ResponseEntity<String> deletepaymentById(@PathVariable Integer paymentId) throws Throwable {
		 
		 return (ResponseEntity<String>) paymentService.findById(paymentId)
	                .map(payment -> {
	                	paymentService.deletePaymentById(Integer.valueOf(paymentId));
	                    return  ResponseEntity
	                            .ok()
	                            .body("paymentID " + paymentId + " is deleted");
	                })
	                .orElseThrow(() -> new ResourceNotFoundException(
	                                "paymentID " + paymentId + " not found"
	                        )
	                );
	}
			
	@GetMapping(path = "/payments/customer/{customerId}/{year}/{from}/{to}", produces = "application/json")
	public List<Payment>  findcustomerFromToByCustomerId(@PathVariable Integer customerId,
			@PathVariable Integer year, @PathVariable Integer from, @PathVariable Integer to
			) throws Throwable {
			
			return	paymentService.getPaymentsHistoryBetween4PaymentHistory(customerId, year, from, to);
	}
		
	@GetMapping(path = "/payments/customer/history/{customerId}/{year}/{month}", produces = "application/json")
	public List<Payment>  findPaymentHistoryOfMonthByCustomerId(@PathVariable Integer customerId,
			@PathVariable Integer year, @PathVariable Integer month
			) throws Throwable {
		LocalDate currentdate = LocalDate.now();
		Month currentMonth = currentdate.getMonth();
		
		boolean runAB=false;
		
		//in condition of the exception, to gracefully exit, we can add time out or sleep/wait/retry for certain periods
		if(runAB) {
			try {
				systemServiceA.process(month);
			}catch(Exception e) {
				System.out.println();
			}
			try {
				systemServiceB.process(month);
			}catch(Exception e) {
				systemServiceB.process(month);
				System.out.println();
			}
		}
		else {
			if(month==currentMonth.getValue()) {
				try {
					systemServiceA.process(month);
				}catch(Exception e) {
					systemServiceB.process(month);
				}
			}
			else {
				try {
					systemServiceB.process(month);
				}catch(Exception e) {
					systemServiceB.process(month);
				}
			}
		}
		
		return	paymentService.getPaymentsHistoryOfMonthAndYear4PaymentHistory(customerId, year, month);
	}
}