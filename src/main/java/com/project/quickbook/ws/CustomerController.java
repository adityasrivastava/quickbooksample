package com.project.quickbook.ws;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intuit.ipp.core.Context;
import com.intuit.ipp.core.ServiceType;
import com.intuit.ipp.data.Account;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.data.EmailAddress;
import com.intuit.ipp.data.TelephoneNumber;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.net.MethodType;
import com.intuit.ipp.security.OAuthAuthorizer;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.services.QueryResult;
import com.project.quickbook.oauth.util.OAuthUtils;
import com.project.quickbook.service.CustomerService;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

@RestController
public class CustomerController {
	
	private String consumerKey;
	private String consumerSecret;
	private String accessToken;
	private String accessTokenSecret;
	
	private OAuthAuthorizer oauth;  
	private OAuthUtils oauthUtils;
	
	@Autowired
	CustomerService g_objCustomerService;
	
	
	@RequestMapping(value="/api/customer/addNewCustomer", method=RequestMethod.POST)
	public void addNewCustomer(@RequestBody Customer param_objCustomer){
		
		g_objCustomerService.addCustomer(param_objCustomer);
		
	}
	
	@RequestMapping(value="/api/customer/deleteCustomer", method=RequestMethod.DELETE)
	public void deleteACustomer(@RequestBody Customer param_objCustomer ){
		
		g_objCustomerService.deleteCustomer(param_objCustomer);
		
	}
	
	@RequestMapping(value="/api/customer/updateCustomer", method=RequestMethod.PUT)
	public void updateACustomer(@RequestBody Customer param_objCustomer){
		
		g_objCustomerService.updateCustomer(param_objCustomer);
		
	}
	
	@RequestMapping(value="/api/customer/findACustomer", method=RequestMethod.GET)
	public void findACustomer(@RequestBody Customer param_objCustomer){
		
		g_objCustomerService.findACustomer(param_objCustomer);
		
	}
	
	@RequestMapping(value="/api/customer/getCustomers", method=RequestMethod.GET)
	public void getAllCustomerList(){
		
		g_objCustomerService.findAllCustomer();
		
	}
	



}
