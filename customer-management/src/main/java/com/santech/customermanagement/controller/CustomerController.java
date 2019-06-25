package com.santech.customermanagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.santech.customermanagement.exception.ResourceNotFoundException;
import com.santech.customermanagement.model.Customer;
import com.santech.customermanagement.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Customer Rest Controller
 * @author Nikhil James
 */
@RestController
@RequestMapping("/api/v1/customers")
@Api(value = "CustomerManagement", description = "Operations pertaining to Customer Management")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@ApiOperation(value = "Get list of all customer", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved all customers"),
			@ApiResponse(code = 404, message = "Customer record not found") })
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
	public List<Customer> getAllCustomers() {
		List<Customer> customerList = customerService.findAll();
		return customerList;
	}

	@ApiOperation(value = "Get customer details by Account Number", response = Customer.class)
	@RequestMapping(value = "/accountnumber/{accountNumber}", method = RequestMethod.GET, produces = "application/json")
	public Customer getCustomerDeails(@PathVariable Long accountNumber, Model model) throws ResourceNotFoundException {
		return customerService.getCustomerByAccountNumber(accountNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer Record not found"));
	}

	@ApiOperation(value = "Get customer by Surname", response = List.class)
	@RequestMapping(value = "/surname/{surname}", method = RequestMethod.GET, produces = "application/json")
	public List<Customer> findBySurname(@PathVariable String surname, Model model) {
		List<Customer> customerList = customerService.findBySurname(surname);
		return customerList;
	}

	@ApiOperation(value = "Add a new Customer")
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		customerService.addCustomer(customer);
		return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update/Add Customer")
	@RequestMapping(value = "/update/{accountNumber}", method = RequestMethod.PUT, produces = "application/json")
	public Customer updateCustomer(@PathVariable Long accountNumber, @RequestBody Customer customer)
			throws ResourceNotFoundException {

		return customerService.getCustomerByAccountNumber(accountNumber).map(storedCustomer -> {
			storedCustomer.setFirstName(customer.getFirstName());
			storedCustomer.setSurname(customer.getSurname());
			storedCustomer.setAddress(customer.getAddress());
			return customerService.updateCustomer(storedCustomer);
		}).orElseGet(() -> {
			customer.setAccountNumber(accountNumber);
			return customerService.addCustomer(customer);
		});
	}

	@ApiOperation(value = "Delete Customer")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		customerService.delete(id);
		return new ResponseEntity<String>("Customer deleted successfully", HttpStatus.ACCEPTED);

	}
}
