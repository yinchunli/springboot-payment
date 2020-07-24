package com.yingchun.tsys.assignment.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.yingchun.tsys.assignment.model.Customer;
import com.yingchun.tsys.assignment.model.CustomerPhone;
import com.yingchun.tsys.assignment.repo.CustomerPhoneRepository;
import com.yingchun.tsys.assignment.repo.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerPhoneRepository customerPhoneRepository;

	public List<Customer> getAllCustomers() {
		
		return (List<Customer>) customerRepository.findAll();
	}

	public Customer addCustomer(Customer customer) {

		try {
			customer = customerRepository.save(customer);
			Integer customerId = customer.getId();
			customer.getCustomerPhones().stream()
					.forEach(c -> {
						c.setCustomerId(customerId);
						customerPhoneRepository.save(c);
					});
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

	public Optional<Customer> updateCustomer(Customer customer, Integer id) {
		if(customerRepository.existsById(id))
			customer.setId(id);
		else 
			return Optional.empty();
		
		try {
			customer = customerRepository.save(customer);
			Integer customerId = customer.getId();
			customer.getCustomerPhones().stream()
					.forEach(c -> {
						c.setCustomerId(customerId);
						customerPhoneRepository.save(c);
					});
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.of(customer);
	}

	public Optional<Customer> getCustomerById(Integer id) {
		return customerRepository.findById(id);
	}

	public void deleteCustomerById(Integer id) {
		customerRepository.deleteById(id);
	}

	public Optional findById(Integer customerId) {
		
		return customerRepository.findById(customerId);
	}

}
