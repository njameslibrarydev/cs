package com.santech.customermanagement.services;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.santech.customermanagement.model.Customer;
import com.santech.customermanagement.repository.CustomerRepository;
import springfox.documentation.annotations.ApiIgnore;
/**
 * Service layer Implementations: implements CustomerService
 * @author Nikhil James
 *
 */
@Service
@ApiIgnore
public class CustomerServiceImpl implements CustomerService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Override
    public Customer updateCustomer(Customer customer) {
        logger.info("Customer details updated: {}", customer.getAccountNumber());
        return customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        logger.info("Deleted customer: {}", id);
        customerRepository.deleteById(id);
    }

	@Override
	public List<Customer> findAll() {
		logger.debug("Listing all customer details");
        return customerRepository.findAll();
	}

	@Override
	public Customer addCustomer(Customer customer) {
		logger.info("New customer added: {}", customer.getAccountNumber());
        return customerRepository.save(customer);		
	}

	@Override
	public Optional<Customer> getCustomerByAccountNumber(Long accountNumber) {
		logger.debug("getCustomerByAccountNumber: {}",accountNumber);
        return customerRepository.findById(accountNumber);
	}

	@Override
	public List<Customer> findBySurname(String surname) {
		logger.debug("findBySurname: {}",surname);
        return customerRepository.findBySurname(surname);
	}
}