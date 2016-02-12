package com.project.quickbook.service;

import java.util.List;
import java.util.Properties;

import com.intuit.ipp.core.Context;
import com.intuit.ipp.core.ServiceType;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.security.OAuthAuthorizer;
import com.intuit.ipp.services.DataService;
import com.project.quickbook.oauth.util.OAuthUtils;

public class CustomerServiceBean implements CustomerService{
	
	private OAuthUtils g_objOAuthUtils;
	private Properties g_objQuickbookProperties;
	private OAuthAuthorizer g_objOAuthAuthorizer;
	
	private String g_objConsumerKey;
	private String g_objConsumerSecret;
	private String g_objAccessToken;
	private String g_objAccessTokenSecret;
	private String g_objAppToken;
	private String g_objCompanyId;
	private Context g_objContext;
	private DataService g_objDataService;

	@Override
	public void addCustomer(Customer customer) {
		g_objOAuthUtils = new OAuthUtils();
		
		g_objQuickbookProperties = g_objOAuthUtils.readProperties();
		g_objConsumerKey = g_objQuickbookProperties.getProperty("oauth_consumer_key");
		g_objConsumerSecret = g_objQuickbookProperties.getProperty("oauth_consumer_secret");
		g_objAccessToken = g_objQuickbookProperties.getProperty("accessToken");
		g_objAccessTokenSecret = g_objQuickbookProperties.getProperty("accessTokenSecret");
		g_objAppToken = g_objQuickbookProperties.getProperty("app_token");
		g_objCompanyId = g_objQuickbookProperties.getProperty("realm_id");
		
		g_objOAuthAuthorizer = new OAuthAuthorizer(g_objConsumerKey, g_objConsumerSecret, g_objAccessToken, g_objAccessTokenSecret); 
		
		try {
			g_objContext = new Context(g_objOAuthAuthorizer, ServiceType.QBO, g_objCompanyId);
			g_objDataService = new DataService(g_objContext);
			g_objDataService.add(customer);
		} catch (FMSException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCustomer(Customer customer) {
		
		g_objOAuthUtils = new OAuthUtils();
		
		g_objQuickbookProperties = g_objOAuthUtils.readProperties();
		g_objConsumerKey = g_objQuickbookProperties.getProperty("oauth_consumer_key");
		g_objConsumerSecret = g_objQuickbookProperties.getProperty("oauth_consumer_secret");
		g_objAccessToken = g_objQuickbookProperties.getProperty("accessToken");
		g_objAccessTokenSecret = g_objQuickbookProperties.getProperty("accessTokenSecret");
		g_objAppToken = g_objQuickbookProperties.getProperty("app_token");
		g_objCompanyId = g_objQuickbookProperties.getProperty("realm_id");
		
		g_objOAuthAuthorizer = new OAuthAuthorizer(g_objConsumerKey, g_objConsumerSecret, g_objAccessToken, g_objAccessTokenSecret); 
		
		try {
			g_objContext = new Context(g_objOAuthAuthorizer, ServiceType.QBO, g_objCompanyId);
			g_objDataService = new DataService(g_objContext);
			g_objDataService.delete(customer);
		} catch (FMSException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateCustomer(Customer customer) {
		g_objOAuthUtils = new OAuthUtils();
		
		g_objQuickbookProperties = g_objOAuthUtils.readProperties();
		g_objConsumerKey = g_objQuickbookProperties.getProperty("oauth_consumer_key");
		g_objConsumerSecret = g_objQuickbookProperties.getProperty("oauth_consumer_secret");
		g_objAccessToken = g_objQuickbookProperties.getProperty("accessToken");
		g_objAccessTokenSecret = g_objQuickbookProperties.getProperty("accessTokenSecret");
		g_objAppToken = g_objQuickbookProperties.getProperty("app_token");
		g_objCompanyId = g_objQuickbookProperties.getProperty("realm_id");
		
		g_objOAuthAuthorizer = new OAuthAuthorizer(g_objConsumerKey, g_objConsumerSecret, g_objAccessToken, g_objAccessTokenSecret); 
		
		try {
			g_objContext = new Context(g_objOAuthAuthorizer, ServiceType.QBO, g_objCompanyId);
			g_objDataService = new DataService(g_objContext);
			g_objDataService.delete(customer);
		} catch (FMSException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Customer findACustomer(Customer customer) {
		Customer v_objCustomer;
		g_objOAuthUtils = new OAuthUtils();
		v_objCustomer = new Customer();
		
		g_objQuickbookProperties = g_objOAuthUtils.readProperties();
		g_objConsumerKey = g_objQuickbookProperties.getProperty("oauth_consumer_key");
		g_objConsumerSecret = g_objQuickbookProperties.getProperty("oauth_consumer_secret");
		g_objAccessToken = g_objQuickbookProperties.getProperty("accessToken");
		g_objAccessTokenSecret = g_objQuickbookProperties.getProperty("accessTokenSecret");
		g_objAppToken = g_objQuickbookProperties.getProperty("app_token");
		g_objCompanyId = g_objQuickbookProperties.getProperty("realm_id");
		
		g_objOAuthAuthorizer = new OAuthAuthorizer(g_objConsumerKey, g_objConsumerSecret, g_objAccessToken, g_objAccessTokenSecret); 
		
		try {
			g_objContext = new Context(g_objOAuthAuthorizer, ServiceType.QBO, g_objCompanyId);
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
		
		g_objOAuthUtils = new OAuthUtils();
		
		
		g_objQuickbookProperties = g_objOAuthUtils.readProperties();
		g_objConsumerKey = g_objQuickbookProperties.getProperty("oauth_consumer_key");
		g_objConsumerSecret = g_objQuickbookProperties.getProperty("oauth_consumer_secret");
		g_objAccessToken = g_objQuickbookProperties.getProperty("accessToken");
		g_objAccessTokenSecret = g_objQuickbookProperties.getProperty("accessTokenSecret");
		g_objAppToken = g_objQuickbookProperties.getProperty("app_token");
		g_objCompanyId = g_objQuickbookProperties.getProperty("realm_id");
		
		g_objOAuthAuthorizer = new OAuthAuthorizer(g_objConsumerKey, g_objConsumerSecret, g_objAccessToken, g_objAccessTokenSecret); 
		
		try {
			g_objContext = new Context(g_objOAuthAuthorizer, ServiceType.QBO, g_objCompanyId);
			g_objDataService = new DataService(g_objContext);
			v_objListCustomer = g_objDataService.findAll(new Customer());

			return v_objListCustomer;
		} catch (FMSException e) {
			e.printStackTrace();
		}
		return null;
		
	}

}
