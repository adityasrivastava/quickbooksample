package com.project.quickbook.service;

import java.util.List;

import com.intuit.ipp.data.Customer;

public interface CustomerService {
	
	void addCustomer(Customer customer);
	void deleteCustomer(Customer customer);
	void updateCustomer(Customer customer);
	Customer findACustomer(Customer customer);
	List<Customer> findAllCustomer();

}
