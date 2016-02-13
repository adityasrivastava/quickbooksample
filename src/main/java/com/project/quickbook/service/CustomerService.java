package com.project.quickbook.service;

import java.util.List;

import com.intuit.ipp.data.Customer;

public interface CustomerService {
	
	Customer addCustomer(Customer customer);
	Customer deleteCustomer(Customer customer);
	Customer updateCustomer(Customer customer);
	Customer findACustomer(Customer customer);
	List<Customer> findAllCustomer();

}
