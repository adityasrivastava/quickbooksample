package com.project.quickbook.mapper;

import com.project.quickbook.model.Customer;

public interface QuickbookMapper {
	
	Customer mapQBOCustomerToCustomerDTO(com.intuit.ipp.data.Customer customer);

}
