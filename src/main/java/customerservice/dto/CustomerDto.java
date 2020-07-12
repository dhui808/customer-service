package customerservice.dto;

import java.util.List;

import customerservice.model.Customer;
import productservice.model.Product;

public class CustomerDto extends Customer {

    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
