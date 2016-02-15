package com.project.quickbook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String customerQBOId;
	private String firstName;
	private String lastName;
	private String companyName;
	private String emailAddress;
	private String syncToken;
	private String sparse;
	
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCustomerQBOId() {
		return customerQBOId;
	}
	
	public void setCustomerQBOId(String customerQBOId) {
		this.customerQBOId = customerQBOId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getSyncToken() {
		return syncToken;
	}
	
	public void setSyncToken(String syncToken) {
		this.syncToken = syncToken;
	}
	
	public String getSparse() {
		return sparse;
	}
	
	public void setSparse(String sparse) {
		this.sparse = sparse;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", customer_id=" + customerQBOId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", companyName=" + companyName + ", emailAddress=" + emailAddress + ", syncToken="
				+ syncToken + ", sparse=" + sparse + "]";
	}

}
