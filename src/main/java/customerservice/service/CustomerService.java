package customerservice.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import customerservice.entities.CustomerEntity;
import customerservice.model.Customer;
import customerservice.repositories.CustomerRepository;

@Service
public class CustomerService {
	
	private final Logger logger = LoggerFactory.getLogger(CustomerService.class);
	
	@Autowired
    private CustomerRepository customerRepository;
	
	public List<Customer> listCustomers() {
		
		Iterable<CustomerEntity> customerEntityList = customerRepository.findAll();
		List<Customer> customers = new ArrayList<Customer>();
		
		for (CustomerEntity entity: customerEntityList) {
			Customer customer = new Customer();
			customer.setId(entity.getId().toString());
			customer.setName(entity.getName());
			customer.setAge(entity.getAge());
			
			customers.add(customer);
		}
		
		return customers;
	}

	public Long addCustomer(Customer customer) {
		
		CustomerEntity entity = new CustomerEntity();
		
		entity.setName(customer.getName());
		entity.setAge(customer.getAge());
		
		entity = customerRepository.save(entity);
		
		logger.debug("Registered customer " + customer.getName());
		
		return entity.getId();
	}
}
