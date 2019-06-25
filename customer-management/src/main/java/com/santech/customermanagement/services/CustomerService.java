package com.santech.customermanagement.services;

import java.util.List;
import java.util.Optional;
import com.santech.customermanagement.model.Customer;

/**
 * CustomerService Interface
 * @author Nikhil James
 *
 */
public interface CustomerService {
	
	public List<Customer> findAll();
	
	public Customer addCustomer(Customer customer);	
	
	public Optional<Customer> getCustomerByAccountNumber(Long accountNumber) ;
	
	public void delete(Long accountNumber);	
	
	public List<Customer> findBySurname(String surname);	
	
	public Customer updateCustomer(Customer customer);
	
}
