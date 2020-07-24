package com.yingchun.tsys.assignment.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yingchun.tsys.assignment.model.Customer;
import com.yingchun.tsys.assignment.model.Payment;
import com.yingchun.tsys.assignment.model.PaymentHistory;

@Repository("paymentHistoryRepo")
@Transactional
public interface PaymentHistoryRepository extends CrudRepository<PaymentHistory, Integer>{

	@Query("select p from paymentHistory p where p.customerId = ?1 and p.year=?2 and p.month > ?2 and p.month <?3 ")
	List<PaymentHistory> findByCustomerIdAndFromAndTo(Integer customerId, Integer from, Integer to);

	List<PaymentHistory> findByCustomerIdAndYearAndMonth(Integer customerId, Integer year, Integer month);
}
