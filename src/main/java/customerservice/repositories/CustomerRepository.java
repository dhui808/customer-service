package customerservice.repositories;

import org.springframework.data.repository.CrudRepository;

import customerservice.entities.CustomerEntity;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
}
