package com.project.quickbook.service.bean;

import static com.intuit.ipp.query.GenerateQuery.$;
import static com.intuit.ipp.query.GenerateQuery.select;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.intuit.ipp.core.Context;
import com.intuit.ipp.core.IEntity;
import com.intuit.ipp.core.ServiceType;
import com.intuit.ipp.data.Customer;
import com.intuit.ipp.exception.AuthenticationException;
import com.intuit.ipp.exception.FMSException;
import com.intuit.ipp.query.GenerateQuery;
import com.intuit.ipp.security.OAuthAuthorizer;
import com.intuit.ipp.services.DataService;
import com.intuit.ipp.services.QueryResult;
import com.project.quickbook.mapper.CustomerMapper;
import com.project.quickbook.mapper.QuickbookMapper;
import com.project.quickbook.oauth.util.OAuthQBOUtils;
import com.project.quickbook.repository.CustomerRepository;
import com.project.quickbook.repository.service.CustomerRepositoryService;
import com.project.quickbook.service.CustomerService;



@Service
public class CustomerServiceBean implements CustomerService{
	
	@Autowired
	private CustomerRepositoryService g_objCustomerRepoService;
	
	private OAuthAuthorizer g_objOAuthAuthorizer;
	private Context g_objContext;
	private DataService g_objDataService;

	@Override
	public Customer addCustomer(Customer customer) {
		Customer v_objNewCustomer;
		com.project.quickbook.model.Customer v_objNewCustomerDto;
		g_objOAuthAuthorizer = new OAuthAuthorizer(OAuthQBOUtils.OAUTH_CONSUMER_KEY, OAuthQBOUtils.OAUTH_CONSUMER_SECRET, OAuthQBOUtils.ACCESS_TOKEN, OAuthQBOUtils.ACCESS_TOKEN_SECRET); 
		
		try {
			g_objContext = new Context(g_objOAuthAuthorizer, ServiceType.QBO, OAuthQBOUtils.REALM_ID);
			g_objDataService = new DataService(g_objContext);
			
			v_objNewCustomer = g_objDataService.add(customer);
//			g_objCustomerRepoService.saveQBOCustomerToCustomerRepository(v_objNewCustomer);
			
			return v_objNewCustomer;
		}catch(AuthenticationException aux){
			System.out.println("Authentication Exception : Check your quickbooks configurations or update your keys!!");
		}catch (FMSException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Customer deleteCustomer(Customer customer) {
		Customer v_objDeleteCustomer;
		com.project.quickbook.model.Customer v_objDeleteCustomerDto;
		
		g_objOAuthAuthorizer = new OAuthAuthorizer(OAuthQBOUtils.OAUTH_CONSUMER_KEY, OAuthQBOUtils.OAUTH_CONSUMER_SECRET, OAuthQBOUtils.ACCESS_TOKEN, OAuthQBOUtils.ACCESS_TOKEN_SECRET); 
		
		try {
			
			g_objContext = new Context(g_objOAuthAuthorizer, ServiceType.QBO, OAuthQBOUtils.REALM_ID);
			g_objDataService = new DataService(g_objContext);
			v_objDeleteCustomer = g_objDataService.delete(customer);

			v_objDeleteCustomerDto = g_objCustomerRepoService.findCustomerByQBOId(v_objDeleteCustomer.getId());
			g_objCustomerRepoService.deleteAQBOCustomerFromCustomerRepository(v_objDeleteCustomerDto);
			
			return v_objDeleteCustomer;
		} catch (FMSException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		Customer v_objUpdatedCustomer;
		com.project.quickbook.model.Customer v_objUpdateCustomerDto;
		
		g_objOAuthAuthorizer = new OAuthAuthorizer(OAuthQBOUtils.OAUTH_CONSUMER_KEY, OAuthQBOUtils.OAUTH_CONSUMER_SECRET, OAuthQBOUtils.ACCESS_TOKEN, OAuthQBOUtils.ACCESS_TOKEN_SECRET); 
		try {
			g_objContext = new Context(g_objOAuthAuthorizer, ServiceType.QBO, OAuthQBOUtils.REALM_ID);
			g_objDataService = new DataService(g_objContext);
			v_objUpdatedCustomer = g_objDataService.update(customer);
			
			v_objUpdateCustomerDto = g_objCustomerRepoService.findCustomerByQBOId(v_objUpdatedCustomer.getId());
			g_objCustomerRepoService.updateQBOCustomerToCustomerRepository(v_objUpdatedCustomer, v_objUpdateCustomerDto);
			
			return v_objUpdatedCustomer;
		} catch (FMSException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public Customer findACustomer(Customer customer) {
		Customer v_objCustomer;
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
	
	@Override
	public Customer syncFindACustomer(Customer customer) {
		Customer v_objCustomer;
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
	public List<Customer> syncfindAllCustomer() {
		List<Customer> v_objListCustomer;
		Customer v_objQueryCustomerQBO;
		List<com.project.quickbook.model.Customer> v_objCustomerDto;
		g_objOAuthAuthorizer = new OAuthAuthorizer(OAuthQBOUtils.OAUTH_CONSUMER_KEY, OAuthQBOUtils.OAUTH_CONSUMER_SECRET, OAuthQBOUtils.ACCESS_TOKEN, OAuthQBOUtils.ACCESS_TOKEN_SECRET); 
		
		try {
			
			// 1. Get all data from QBO
			g_objContext = new Context(g_objOAuthAuthorizer, ServiceType.QBO, OAuthQBOUtils.REALM_ID);
			g_objDataService = new DataService(g_objContext);
			v_objListCustomer = g_objDataService.findAll(new Customer());
			
			// 2. Get all from our db
			
			v_objCustomerDto = g_objCustomerRepoService.getAllCustomersFromRepository();
			v_objQueryCustomerQBO = GenerateQuery.createQueryEntity(Customer.class);
			
			// 3. Create unique array select
			List<String> qboIdlist = new ArrayList();
			
			for(com.project.quickbook.model.Customer customer: v_objCustomerDto){
				qboIdlist.add(customer.getCustomerQBOId());
			}
			
			String[] qboIdArray = new String[qboIdlist.size()];
			qboIdlist.toArray(qboIdArray);
			System.out.println(qboIdArray.length);
			// SELECT * FROM Customer WHERE familyName IN (...) ORDER BY familyName

			   String query = select($(v_objQueryCustomerQBO)).generate();
			System.out.println(query);
			
			QueryResult result;
			
			result = g_objDataService.executeQuery(query);
			
			
			for(IEntity ie : result.getEntities()){
				
				Customer customer1 = (Customer) ie;
				System.out.println(customer1.getId());
			}
		
//			return v_objListCustomer;
		} catch (FMSException e) {
			e.printStackTrace();
		}
		return null;
		
	}



}
