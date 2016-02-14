package com.project.quickbook.ws;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intuit.ipp.data.Customer;
import com.project.quickbook.AbstractTestController;
import com.project.quickbook.service.CustomerService;


public class CustomerControllerTest extends AbstractTestController{
	
	@Autowired
	CustomerService g_objCustomerService;
	
	@Before
	public void setUp(){
		super.setUp();
	}
	
	@Test
	public void test(){
		
	}
	
	//@Test
	public void addNewCustomerTest(){
		
		String v_objUri = "/api/customer/addNewCustomer";
		MvcResult v_objMvcResult;
		Customer v_objNewCustomer;
		
		v_objNewCustomer = getNewCustomer();

		try {
			v_objMvcResult = g_objMvc.perform(MockMvcRequestBuilders.post(v_objUri)
					.content(new ObjectMapper().writeValueAsString(v_objNewCustomer))
					.contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
			
			System.out.println("Add new Customer response :"+v_objMvcResult.getResponse().getContentAsString());
			
		}catch(JsonProcessingException e1){
			e1.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//@Test 
	public void deleteACustomer(){
		
		String v_objUri = "/api/customer/deleteCustomer";
		MvcResult v_objMvcResult;
		
		Customer v_objNewCustomer = deleteARandomCustomer();
		
		try {
			v_objMvcResult = g_objMvc.perform(MockMvcRequestBuilders.delete(v_objUri, v_objNewCustomer)
					.contentType(MediaType.APPLICATION_JSON_VALUE)
					.content(new ObjectMapper().writeValueAsString(v_objNewCustomer))
					).andReturn();
			System.out.println("Delete Customer response: "+v_objMvcResult.getResponse().getContentAsString());
		} catch (JsonParseException jEx) {
			jEx.printStackTrace();
		} catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	//@Test
	public void updateACustomer(){
		
		String v_objUri = "/api/customer/updateCustomer";
		MvcResult v_objMvcResult;
		
		Customer v_objUpdateCustomer = updateCustomerDetails();
		
		try {
			v_objMvcResult = g_objMvc.perform(MockMvcRequestBuilders.put(v_objUri).content(new ObjectMapper()
							.writeValueAsString(v_objUpdateCustomer)).contentType(MediaType.APPLICATION_JSON_VALUE))
							.andReturn();
			System.out.println("Update Customer response:"+v_objMvcResult.getResponse().getContentAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
//	@Test
	public void getAllCustomers(){
		
		String v_objUri = "/api/customer/getCustomers";
		MvcResult v_objMvcResult;
		try {
			v_objMvcResult = g_objMvc.perform(MockMvcRequestBuilders.get(v_objUri).accept(MediaType.ALL)).andReturn();
			System.out.println("Get Customers response: "+v_objMvcResult.getResponse().getContentAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//@Test
	public void getACustomer(){
		
		String v_objUri = "/api/customer/findACustomer";
		MvcResult v_objMvcResult;
		try {
			v_objMvcResult = g_objMvc.perform(MockMvcRequestBuilders.get(v_objUri).content(
					new ObjectMapper()
					.writeValueAsString(getCustomerById()))
					.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();
			System.out.println("Find A Customer Response: "+v_objMvcResult.getResponse().getContentAsString());
		}catch(JsonParseException jEx){
			jEx.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Customer getACustomerTestUpdate(Customer customer){
		String v_objUri = "/api/customer/findACustomer";
		MvcResult v_objMvcResult;
		try {
			v_objMvcResult = g_objMvc.perform(MockMvcRequestBuilders.get(v_objUri).content(
					new ObjectMapper()
					.writeValueAsString(customer))
					.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andReturn();
			
			String jsonString = v_objMvcResult.getResponse().getContentAsString();
			return new ObjectMapper().readValue(jsonString, Customer.class);
		}catch(JsonParseException jEx){
			jEx.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Customer deleteARandomCustomer(){
		Customer v_objDeleteCustomer = new Customer();
		v_objDeleteCustomer.setId("64");
		return v_objDeleteCustomer;
	}
	
	public Customer updateCustomerDetails(){
		Customer v_objFindCustomerDetails = new Customer();
		v_objFindCustomerDetails.setId("64");
		
		Customer v_objUpdateCustomer = getACustomerTestUpdate(v_objFindCustomerDetails);
		v_objUpdateCustomer.setDisplayName("AreaXL");
	
		v_objUpdateCustomer.setSyncToken("5");
		v_objUpdateCustomer.setSparse(true);
		return v_objUpdateCustomer;
	}
	
	public Customer getNewCustomer(){
		
		Customer v_objNewCustomer = new Customer();
		v_objNewCustomer.setDisplayName("Area52");
		return v_objNewCustomer;
		
	}
	
	public Customer getCustomerById(){
		Customer v_objCustomerById = new Customer();
		v_objCustomerById.setId("64");
		return v_objCustomerById;
	}

}
