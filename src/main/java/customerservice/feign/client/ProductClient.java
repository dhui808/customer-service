package customerservice.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import productservice.model.AddProductResponse;
import productservice.model.Product;

import java.util.List;


@FeignClient(name = "product-service" ,url = "http://localhost:8080/productservice")
public interface ProductClient {

    @GetMapping("/products")
    List<Product> listProducts();

    @GetMapping("/products/{id}")
    Product getProductById(@PathVariable String id);

    @GetMapping("/products/customers/{custId}")
    List<Product> listProductsByCustomerId(@PathVariable String custId);
    
    @RequestMapping(method = RequestMethod.POST, value="/product/add", produces = "application/json")
	AddProductResponse addProduct(@RequestBody Product product);
}
