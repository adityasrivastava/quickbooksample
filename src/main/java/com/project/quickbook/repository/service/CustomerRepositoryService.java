package com.project.quickbook.repository.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.project.quickbook.model.Customer;

@Component
public interface CustomerRepositoryService {
		
	Customer saveQBOCustomerToCustomerRepository(com.intuit.ipp.data.Customer customer);
	Customer updateQBOCustomerToCustomerRepository(com.intuit.ipp.data.Customer customer, Customer v_objUpdateCustomerDto);
	void saveAllQBOCustomersToCustomerRepository(List<com.intuit.ipp.data.Customer> customer);
	void deleteAQBOCustomerFromCustomerRepository(Customer customer);
	Customer findCustomerByQBOId(String id);
	List<Customer> getAllCustomersFromRepository();
}
