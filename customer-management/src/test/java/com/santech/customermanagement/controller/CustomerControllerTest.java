package com.santech.customermanagement.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santech.customermanagement.model.Customer;
import com.santech.customermanagement.repository.CustomerRepository;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Customer Controller Test class for the {@link CustomerController}
 *
 * @author Nikhil James
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CustomerControllerTest {

	private static final Long TEST_CUSTOMER_ID = 1L;

	private static final ObjectMapper om = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerRepository customerRepository;

	@Before
	public void init() {
		Customer customer = new Customer(TEST_CUSTOMER_ID, "Jake", "Joe", "20, Regent Street, London");
		when(customerRepository.findById(TEST_CUSTOMER_ID)).thenReturn(Optional.of(customer));
	}

	@Test
	public void add_Customer_OK() throws Exception {
		Customer newCustomer = new Customer(TEST_CUSTOMER_ID, "Jake", "Joe", "20, Regent Street, London");
		when(customerRepository.save(any(Customer.class))).thenReturn(newCustomer);

		mockMvc.perform(post("/api/v1/customers/add").content(om.writeValueAsString(newCustomer))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.accountNumber", is(1)))
				.andExpect(jsonPath("$.firstName", is("Jake")))
				.andExpect(jsonPath("$.surname", is("Joe")))
				.andExpect(jsonPath("$.address", is("20, Regent Street, London")));
		verify(customerRepository, times(1)).save(any(Customer.class));
	}

	@Test
	public void find_allCustomer_OK() throws Exception {

		List<Customer> customer = Arrays
				.asList(new Customer(TEST_CUSTOMER_ID, "Jake", "Joe", "20, Regent Street, London"));
		when(customerRepository.findAll()).thenReturn(customer);
		mockMvc.perform(get("/api/v1/customers/all")).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].accountNumber", is(1)))
				.andExpect(jsonPath("$[0].firstName", is("Jake")))
				.andExpect(jsonPath("$[0].surname", is("Joe")))
				.andExpect(jsonPath("$[0].address", is("20, Regent Street, London")));
		verify(customerRepository, times(1)).findAll();
	}
	
	@Test
    public void find_customerId_OK() throws Exception {

        mockMvc.perform(get("/api/v1/customers/accountnumber/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountNumber", is(1)))
				.andExpect(jsonPath("$.firstName", is("Jake")))
				.andExpect(jsonPath("$.surname", is("Joe")))
				.andExpect(jsonPath("$.address", is("20, Regent Street, London")));
        verify(customerRepository, times(1)).findById(1L);

    }

	@Test
    public void find_customerSurname_OK() throws Exception {
		List<Customer> customer = Arrays
				.asList(new Customer(TEST_CUSTOMER_ID, "Jake", "Joe", "20, Regent Street, London"));
		when(customerRepository.findBySurname("Joe")).thenReturn(customer);	
        mockMvc.perform(get("/api/v1/customers/surname/Joe"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].accountNumber", is(1)))
				.andExpect(jsonPath("$[0].firstName", is("Jake")))
				.andExpect(jsonPath("$[0].surname", is("Joe")))
				.andExpect(jsonPath("$[0].address", is("20, Regent Street, London")));
        verify(customerRepository, times(1)).findBySurname("Joe");

    }	

	@Test
	public void find_customerIdNotFound_404() throws Exception {
		mockMvc.perform(get("/api/v1/customers/accountnumber/50")).andExpect(status().isNotFound());
	}

	@Test
	public void update_customer_OK() throws Exception {

		Customer updateCustomer = new Customer(TEST_CUSTOMER_ID, "Peter", "John","25, Victoria Street, London");
		when(customerRepository.save(any(Customer.class))).thenReturn(updateCustomer);

		mockMvc.perform(put("/api/v1/customers/update/1").content(om.writeValueAsString(updateCustomer))
				.header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.accountNumber", is(1)))
				.andExpect(jsonPath("$.firstName", is("Peter")))
				.andExpect(jsonPath("$.surname", is("John")))
				.andExpect(jsonPath("$.address", is("25, Victoria Street, London")));
	}

	@Test
	public void delete_customer_OK() throws Exception {

		doNothing().when(customerRepository).deleteById(TEST_CUSTOMER_ID);
		mockMvc.perform(delete("/api/v1/customers/delete/1"))				
				.andExpect(status().isAccepted());

		verify(customerRepository, times(1)).deleteById(1L);
	}

}