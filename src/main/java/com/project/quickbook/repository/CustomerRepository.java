package com.project.quickbook.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.quickbook.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
