package com.project.quickbook.mapper;


import org.springframework.stereotype.Service;

import com.project.quickbook.model.Customer;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Service
public class CustomerMapper implements QuickbookMapper{
	private final static MapperFacade mapper;

	static{
		
		final MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		mapperFactory.classMap(com.intuit.ipp.data.Customer.class, Customer.class)
	      .field("id", "customerQBOId")
	      .field("displayName", "firstName")
	      .field("familyName", "lastName")
	      .field("companyName","companyName")
	      .field("primaryEmailAddr", "emailAddress")
	      .field("syncToken","syncToken")
	      .field("sparse","sparse")
	      .byDefault()
	      .register();  

	    mapper = mapperFactory.getMapperFacade();
	}
	
	 public Customer mapQBOCustomerToCustomerDTO(final com.intuit.ipp.data.Customer pDomain) {
	        Customer pDto = mapper.map(pDomain, Customer.class);
	        return pDto;
	    }


}
