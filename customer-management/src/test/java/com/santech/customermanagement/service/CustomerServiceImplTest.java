package com.santech.customermanagement.service;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import com.santech.customermanagement.model.Customer;
import com.santech.customermanagement.repository.CustomerRepository;

/**
 * Integration test of the Service and the Repository layer.
 * <p>
 * CustomerServiceImplTest subclasses benefit from the following services provided by the Spring
 * TestContext Framework: </p> <ul> <li><strong>Spring IoC container</strong> which spares us unnecessary set up
 * time between test execution.</li> <li><strong>Dependency Injection</strong> of test fixture instances </li> </ul>
 *
 * @author Nikhil James
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplTest {
	 	
		@Autowired
	    private CustomerRepository customerRepository;
	       
	    @Autowired
		private TestEntityManager entityManager;
	    
	    @Test
		public void shouldInsertCustomer(){
			Customer customer = createCustomer();
			Customer savedCustomer = entityManager.persist(customer);			
			Optional<Customer> getCustomerFromDb = customerRepository.findById(savedCustomer.getAccountNumber());		
			assertThat(getCustomerFromDb.get()).isEqualTo(savedCustomer);
		}
		
		private Customer createCustomer() {
			Customer customer = new Customer();
			customer.setFirstName("Sean");
			customer.setSurname("Murphy");
			customer.setAddress("5, Benskin Road, Watford");
			return customer;
		}
	    
	    @Test
	    public void shouldFindCustomersBySurname() {
	    	Customer customer = createCustomer();
			Customer savedCustomer = entityManager.persist(customer);
	        List<Customer> customerList = this.customerRepository.findBySurname(savedCustomer.getSurname());
	        assertThat(customerList.size()).isEqualTo(1);
	        assertThat(customerList.get(0).getFirstName()).isEqualTo(savedCustomer.getFirstName());
	    }

	    @Test
	    public void shouldFindCustomerById() {	    	
	    	Customer customer = createCustomer();
			Customer savedCustomer = entityManager.persist(customer);	    	
	        Optional<Customer> customerSaved = this.customerRepository.findById(savedCustomer.getAccountNumber());
	        assertThat(customerSaved.get().getFirstName()).isEqualTo("Sean");
	        assertThat(customerSaved.get().getSurname()).isEqualTo("Murphy");
	        assertThat(customerSaved.get().getAddress()).isEqualTo("5, Benskin Road, Watford");
	    }
	   
	    @Test	    
	    public void shouldUpdateCustomer() {	    	
	    	Customer customer = createCustomer();
			Customer savedCustomer = entityManager.persist(customer);	    	
			Optional<Customer> customerSaved = this.customerRepository.findById(savedCustomer.getAccountNumber());	        
	        String newLastName = "Joe";
	        customer.setSurname(newLastName);
	        this.customerRepository.save(customer);
	        customerSaved = this.customerRepository.findById(savedCustomer.getAccountNumber());
	        assertThat(customerSaved.get().getSurname()).isEqualTo(newLastName);
	    }
}
