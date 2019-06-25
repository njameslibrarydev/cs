package com.santech.customermanagement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.santech.customermanagement.model.Customer;

/**
 * Repository class for <code>Customer</code> domain objects All method names are compliant with Spring Data naming
 * conventions so this interface can easily be extended for Spring Data See here: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
 *
 * @author Nikhil James 
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
	
	/**
     * Retrieve {@link Customer}s from the data store by surname, returning all Customers
     * whose surname <i>equals</i> given name.
     * @param surname Value to search for
     * @return a List of matching {@link Customer}s (or an empty List if none
     * found)
     */
	List<Customer> findBySurname(String surname);
	
}
