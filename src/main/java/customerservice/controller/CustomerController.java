package customerservice.controller;

import customerservice.dto.CustomerDto;
import customerservice.feign.client.ProductClient;
import customerservice.model.AddCustomerResponse;
import customerservice.model.Customer;
import customerservice.service.CustomerService;
import productservice.client.ProductControllerApi;
import productservice.model.Product;
import productservice.ApiClient;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {

	private ProductControllerApi productClient = new ApiClient().buildClient(ProductControllerApi.class);;

	@Autowired
	private CustomerService customerService;

	@GetMapping("/customers")
	public List<CustomerDto> getCustomers() {
		List<Customer> customers = customerService.listCustomers();
		
		List<CustomerDto> dtos = new ArrayList<CustomerDto>(customers.size());
		
		for (Customer customer : customers) {
			List<Product> products = productClient.listProductsByCustomerIdUsingGET(customer.getId());
			CustomerDto dto = new CustomerDto();
			BeanUtils.copyProperties(customer, dto);
			dto.setProducts(products);
			dtos.add(dto);
		}

		return dtos;
	}

	@GetMapping("/customer/{id}")
	public CustomerDto getCustomerById(@PathVariable String id) {
		Customer customer = customerService.listCustomers().stream().filter(cust -> cust.getId().equalsIgnoreCase(id))
				.findFirst().get();
		List<Product> products = productClient.listProductsByCustomerIdUsingGET(id);
		CustomerDto dto = new CustomerDto();
		BeanUtils.copyProperties(customer, dto);
		dto.setProducts(products);
		// Product pr1 = productClient.getProductById("PRD1");
		// Product pr2 = productClient.create(products.get(0));
		// List<Product> pr3 = productClient.listProducts();
		return dto;
	}
	
	@RequestMapping(method = RequestMethod.POST, value="/customer/add", produces = "application/json")
	public AddCustomerResponse addCustomer(@RequestBody Customer customer) {
		
		Long customerId = customerService.addCustomer(customer);
		AddCustomerResponse customerResponse = new AddCustomerResponse();
		customerResponse.setStatus("success");
		customerResponse.setCustomerId(customerId);
		System.out.println("addCustomer called - customerId = " + customerId);
		
		return customerResponse;
	}
}
