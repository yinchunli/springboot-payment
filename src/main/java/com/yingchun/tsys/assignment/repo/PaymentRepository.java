package com.yingchun.tsys.assignment.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yingchun.tsys.assignment.model.Payment;

@Repository("paymentRepo")
@Transactional
public interface PaymentRepository extends CrudRepository<Payment, Integer>{

	List<Payment> findByCustomerId(Integer customerId);

//	@Query("select p from payment p where p.customerId = ?1 and p.year=?2 and p.month >= ?3 and p.month <=?4 ")
	List<Payment> findByCustomerIdAndYearAndMonthBetween(Integer customerId, Integer year, Integer from, Integer to);

//	@Query("select p from payment p where p.customerId = ?1 and p.year=?2 and p.month = ?3 ")
	List<Payment> findByCustomerIdAndYearAndMonth(Integer customerId, Integer year, Integer month);
}