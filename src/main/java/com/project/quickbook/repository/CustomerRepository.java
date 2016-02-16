package com.project.quickbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.quickbook.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findBycustomerQBOId(String customerQBOId);
}
