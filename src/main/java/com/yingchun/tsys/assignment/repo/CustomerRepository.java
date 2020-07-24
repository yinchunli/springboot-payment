package com.yingchun.tsys.assignment.repo;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yingchun.tsys.assignment.model.Customer;

@Repository("h2ContactRepo")
@Transactional
public interface CustomerRepository extends CrudRepository<Customer, Integer>{
}
