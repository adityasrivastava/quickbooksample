package com.project.quickbook.service;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import com.intuit.ipp.core.Context;
import com.intuit.ipp.core.ServiceType;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.security.OAuthAuthorizer;
import com.intuit.ipp.services.DataService;
import com.project.quickbook.oauth.util.OAuthUtils;

public class CustomerController {
	
	private String consumerKey;
	private String consumerSecret;
	private String accessToken;
	private String accessTokenSecret;
	
	private OAuthAuthorizer oauth;  
	private OAuthUtils oauthUtils;
	

	
	public void addCustomer(){
		
		
		oauthUtils = new OAuthUtils();
		Properties v_objQuickbookProperites = oauthUtils.readProperties();
		
		consumerKey = v_objQuickbookProperites.getProperty("oauth_consumer_key");
		consumerSecret = v_objQuickbookProperites.getProperty("oauth_consumer_secret");
		accessToken = v_objQuickbookProperites.getProperty("accessToken");
		accessTokenSecret = v_objQuickbookProperites.getProperty("accessTokenSecret");
		
		
		oauth = new OAuthAuthorizer(consumerKey, consumerSecret, accessToken, accessTokenSecret); 
		
		String appToken = v_objQuickbookProperites.getProperty("app_token");
		String companyId = v_objQuickbookProperites.getProperty("app_id");
		
		try {
			Context context = new Context(oauth, appToken, ServiceType.QBO, companyId);
			
			DataService service = new DataService(context);
			
			Customer customer = new Customer();
			customer.setDisplayName("MetaDesign");
			
			Customer result = service.add(customer);
			
			List<Customer> customers = service.findAll(customer);
			Iterator<Customer> itr = customers.iterator();
			while (itr.hasNext()) {
			   Customer customerIt = (Customer) itr.next();
			   String customerName = customerIt.getDisplayName();
			   System.out.println(customerName);
			}
			
			
		} catch (FMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
