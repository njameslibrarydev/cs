package com.santech.customermanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import io.swagger.annotations.ApiModelProperty;

/**
 * Simple JavaBean domain object representing a customer.
 *
 * @author Nikhil James
 */
@Entity
@Table(name = "customers")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_number")
	@ApiModelProperty(hidden = true) 
	private Long accountNumber;

	@Column(name = "first_name")
	@ApiModelProperty(hidden = true) 
	private String firstName;

	@ApiModelProperty(hidden = true) 
	@Column(name = "surname")
	private String surname;

	@ApiModelProperty(hidden = true) 
	@Column(name = "address")
	private String address;

	public Long getAccountNumber() {
		return accountNumber;
	}

	public Customer() {
			
	}
	
	public Customer(Long accountNumber, String firstName, String surname, String address) {
		this.accountNumber = accountNumber;
		this.firstName = firstName;
		this.surname = surname;
		this.address = address;
	}

	public Customer(String firstName, String surname, String address) {
		this.firstName = firstName;
		this.surname = surname;
		this.address = address;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Customer [accountNumber=" + accountNumber + ", firstName=" + firstName + ", surname=" + surname
				+ ", address=" + address + "]";
	}

	
	
	
}
