package com.yingchun.tsys.assignment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yingchun.tsys.assignment.exception.ResourceNotFoundException;
import com.yingchun.tsys.assignment.model.Customer;
import com.yingchun.tsys.assignment.service.CustomerService;
import com.yingchun.tsys.assignment.util.ResponseUtil;

/**
 * 1.	Create a new REST API using Java/JVM technologies with the following endpoints:
 * HTTP Method	Route	Description
 * GET	/customers	List all customers
 * POST	/customers	Create a new customer
 * PUT	/customers/{id}	Update a customer
 * GET	/customers/{id}	Get a specific customer
 * DELETE	/customers/{id}	Delete a customer
**/

@RestController
public class CustomerController {

	@Autowired 
	private CustomerService customerService;
	
	@GetMapping(path="/customers", produces = "application/json")
	public List<Customer> findAllcustomers() {
		return customerService.getAllCustomers();
	}
	
	@PostMapping(path = "/customers", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Customer> savecustomer(@RequestBody Customer customer) throws Throwable {
		
		Optional<Customer> customerOptional = Optional.of(customerService.addCustomer(customer));
		
		return (ResponseEntity<Customer>) customerOptional.map( c -> 
				ResponseEntity.created(ResponseUtil.resourceUri(customer.getId()))
				.body(c)
		).orElseThrow(()-> new ResourceNotFoundException(
            "Customer: " + customer.getCustomerName().getFirstName() + " not created.")
			);
	}

	@PutMapping(path ="/customers/{customerId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Customer> updatecustomer(@RequestBody Customer customer, @PathVariable Integer customerId) throws Throwable {
		Optional<Customer> customerOptional = customerService.updateCustomer(customer, customerId);
 		return (ResponseEntity<Customer>) 
 				customerOptional.map(c ->
					ResponseEntity
					.ok()
					.location(ResponseUtil.resourceUri(customerId))
 					.body(c)
            )
            .orElseThrow(() -> new ResourceNotFoundException(
                            "Customer: " + customerId + " not found"
                    )
            );
	}

	@GetMapping(path = "/customers/{id}", produces = "application/json")
	public ResponseEntity<Customer>  findcustomerById(@PathVariable Integer id) throws Throwable {
		Optional<Customer> customerOptional = customerService.getCustomerById(id);
		return  (ResponseEntity<Customer>) customerOptional.map(c ->
					ResponseEntity
							.ok()
							.location(ResponseUtil.resourceUri(id))
							.body(c)
				)
			.orElseThrow(() -> new ResourceNotFoundException(
			                "customerId: " + id + " not found"
			        )
			);
	}

	@SuppressWarnings("unchecked")
	@DeleteMapping(value="/customers/{customerId}", produces = "application/text")
	public ResponseEntity<String> deletecustomerById(@PathVariable Integer customerId) throws Throwable {
		 
		 return (ResponseEntity<String>) customerService.findById(customerId)
	                .map(customer -> {
	                	customerService.deleteCustomerById(Integer.valueOf(customerId));
	                    return  ResponseEntity
	                            .ok()
	                            .body("customerID " + customerId + " is deleted");
	                })
	                .orElseThrow(() -> new ResourceNotFoundException(
	                                "customerID " + customerId + " not found"
	                        )
	                );
	}
}