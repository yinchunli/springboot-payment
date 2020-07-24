package com.yingchun.tsys.assignment.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yingchun.tsys.assignment.model.CustomerPhone;

@Repository("h2ContactPhoneRepo")
public interface CustomerPhoneRepository extends CrudRepository<CustomerPhone, Integer>{
}
