package com.citiustech.reports.repository;

import org.springframework.data.repository.CrudRepository;
import com.citiustech.reports.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
	
}