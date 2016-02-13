package com.project.quickbook.service.bean;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.intuit.ipp.core.Context;
import com.intuit.ipp.core.ServiceType;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.security.OAuthAuthorizer;
import com.intuit.ipp.services.DataService;
import com.project.quickbook.oauth.util.OAuthQBOUtils;
import com.project.quickbook.service.CustomerService;

@Service
public class CustomerServiceBean implements CustomerService{

	private OAuthAuthorizer g_objOAuthAuthorizer;
	private Context g_objContext;
	private DataService g_objDataService;

	@Override
	public Customer addCustomer(Customer customer) {
		Customer v_objNewCustomer = null;
		
		g_objOAuthAuthorizer = new OAuthAuthorizer(OAuthQBOUtils.OAUTH_CONSUMER_KEY, OAuthQBOUtils.OAUTH_CONSUMER_SECRET, OAuthQBOUtils.ACCESS_TOKEN, OAuthQBOUtils.ACCESS_TOKEN_SECRET); 
		System.out.println("OutputDisplayName: "+customer.getDisplayName());
		try {
			g_objContext = new Context(g_objOAuthAuthorizer, ServiceType.QBO, OAuthQBOUtils.REALM_ID);
			g_objDataService = new DataService(g_objContext);
			v_objNewCustomer = g_objDataService.add(customer);
			return v_objNewCustomer;
		} catch (FMSException e) {
			e.printStackTrace();
		}
		return v_objNewCustomer;
	}

	@Override
	public Customer deleteCustomer(Customer customer) {
		g_objOAuthAuthorizer = new OAuthAuthorizer(OAuthQBOUtils.OAUTH_CONSUMER_KEY, OAuthQBOUtils.OAUTH_CONSUMER_SECRET, OAuthQBOUtils.ACCESS_TOKEN, OAuthQBOUtils.ACCESS_TOKEN_SECRET); 
		
		try {
			g_objContext = new Context(g_objOAuthAuthorizer, ServiceType.QBO, OAuthQBOUtils.REALM_ID);
			g_objDataService = new DataService(g_objContext);
			return g_objDataService.delete(customer);
		} catch (FMSException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		g_objOAuthAuthorizer = new OAuthAuthorizer(OAuthQBOUtils.OAUTH_CONSUMER_KEY, OAuthQBOUtils.OAUTH_CONSUMER_SECRET, OAuthQBOUtils.ACCESS_TOKEN, OAuthQBOUtils.ACCESS_TOKEN_SECRET); 
		try {
			g_objContext = new Context(g_objOAuthAuthorizer, ServiceType.QBO, OAuthQBOUtils.REALM_ID);
			g_objDataService = new DataService(g_objContext); System.out.println("Name: ---->"+customer.getFullyQualifiedName());
			return g_objDataService.update(customer);
		} catch (FMSException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public Customer findACustomer(Customer customer) {
		Customer v_objCustomer;
		v_objCustomer = new Customer();
		g_objOAuthAuthorizer = new OAuthAuthorizer(OAuthQBOUtils.OAUTH_CONSUMER_KEY, OAuthQBOUtils.OAUTH_CONSUMER_SECRET, OAuthQBOUtils.ACCESS_TOKEN, OAuthQBOUtils.ACCESS_TOKEN_SECRET); 
		
		try {
			g_objContext = new Context(g_objOAuthAuthorizer, ServiceType.QBO, OAuthQBOUtils.REALM_ID);
			g_objDataService = new DataService(g_objContext);
			v_objCustomer = g_objDataService.findById(customer);
			
			return v_objCustomer;
			
		} catch (FMSException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	public List<Customer> findAllCustomer() {
		List<Customer> v_objListCustomer;
		
		g_objOAuthAuthorizer = new OAuthAuthorizer(OAuthQBOUtils.OAUTH_CONSUMER_KEY, OAuthQBOUtils.OAUTH_CONSUMER_SECRET, OAuthQBOUtils.ACCESS_TOKEN, OAuthQBOUtils.ACCESS_TOKEN_SECRET); 
		
		try {
			g_objContext = new Context(g_objOAuthAuthorizer, ServiceType.QBO, OAuthQBOUtils.REALM_ID);
			g_objDataService = new DataService(g_objContext);
			v_objListCustomer = g_objDataService.findAll(new Customer());
			return v_objListCustomer;
		} catch (FMSException e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
