package com.project.quickbook.repository.service.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.quickbook.mapper.QuickbookMapper;
import com.project.quickbook.model.Customer;
import com.project.quickbook.repository.CustomerRepository;
import com.project.quickbook.repository.service.CustomerRepositoryService;

@Service
public class CustomerRepoServiceBean implements CustomerRepositoryService{
	
	@Autowired
	CustomerRepository g_objCustomerRepository;
	
	@Autowired
	private QuickbookMapper g_objQuickbookMapper;

	@Override
	public Customer saveQBOCustomerToCustomerRepository(com.intuit.ipp.data.Customer customer) {
		
		Customer v_objCustomer;
		v_objCustomer = g_objQuickbookMapper.mapQBOCustomerToCustomerDTO(customer);
		return g_objCustomerRepository.save(v_objCustomer);
	}

	@Override
	public Customer updateQBOCustomerToCustomerRepository(com.intuit.ipp.data.Customer customer, Customer customerDto ) {
		Customer v_objCustomer;
		v_objCustomer = g_objQuickbookMapper.mapQBOCustomerToCustomerDTO(customer);
		v_objCustomer.setId(customerDto.getId());
		return g_objCustomerRepository.save(v_objCustomer);
	}

	@Override
	public void saveAllQBOCustomersToCustomerRepository(List<com.intuit.ipp.data.Customer> customer) {
		Iterator<com.intuit.ipp.data.Customer> v_iterQBOCustomerList;
		List<Customer> v_iterCustomerDTO;
		
		v_iterQBOCustomerList = customer.iterator(); 
		v_iterCustomerDTO = new ArrayList<Customer>();
		
		while(v_iterQBOCustomerList.hasNext()){	
			g_objQuickbookMapper.mapQBOCustomerToCustomerDTO(v_iterQBOCustomerList.next());
		}
		
		g_objCustomerRepository.save(v_iterCustomerDTO);
	}

	@Override
	public void deleteAQBOCustomerFromCustomerRepository(Customer customer) {
		
		g_objCustomerRepository.delete(customer.getId());
		
	}

	@Override
	public Customer findCustomerByQBOId(String id) {
		Customer customer;
		customer = g_objCustomerRepository.findBycustomerQBOId(id);
		System.out.println(customer.toString());
		return null;
	}

	@Override
	public List<Customer> getAllCustomersFromRepository() {
		
		return g_objCustomerRepository.findAll();
	}
	
	

}
