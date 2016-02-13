package com.project.quickbook.ws;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
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
import com.project.quickbook.oauth.util.OAuthQBOUtils;
import com.project.quickbook.service.CustomerService;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

@RestController
public class CustomerController {

	@Autowired
	CustomerService g_objCustomerService;
	
	
	@RequestMapping(value="/api/customer/addNewCustomer", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> addNewCustomer(@RequestBody Customer param_objCustomer){
		return new ResponseEntity<Customer>(g_objCustomerService.addCustomer(param_objCustomer),HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/customer/deleteCustomer", method=RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> deleteACustomer(@RequestBody Customer param_objCustomer ){
		return new ResponseEntity<Customer>(g_objCustomerService.deleteCustomer(param_objCustomer), HttpStatus.OK);	
	}
	
	@RequestMapping(value="/api/customer/updateCustomer", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> updateACustomer(@RequestBody Customer param_objCustomer){
		return new ResponseEntity<Customer>(g_objCustomerService.updateCustomer(param_objCustomer), HttpStatus.OK);
	}
	
//	@RequestMapping(value="/api/customer/sparseUpdateCustomer", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Customer> sparseUpdateACustomer(@RequestBody Customer param_objCustomer){
//		return new ResponseEntity<Customer>(g_objCustomerService.updateCustomer(param_objCustomer), HttpStatus.OK);
//	}
	
	@RequestMapping(value="/api/customer/findACustomer", method=RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> findACustomer(@RequestBody Customer param_objCustomer){
		return new ResponseEntity<Customer>(g_objCustomerService.findACustomer(param_objCustomer), HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/customer/getCustomers", method=RequestMethod.GET)
	public ResponseEntity<List<Customer>> getAllCustomerList(){
		return new ResponseEntity<List<Customer>>(g_objCustomerService.findAllCustomer(), HttpStatus.OK);	
	}
	
	@RequestMapping(value="/api/customer/objCustomer", method=RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> getAEmptyCustomerInstant(){
		return new ResponseEntity<Customer>(new Customer(), HttpStatus.OK);
	}
	



}
