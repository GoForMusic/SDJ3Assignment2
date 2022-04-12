package via.sdj3.sdj3assignment2.repository;

import org.springframework.data.repository.CrudRepository;
import via.sdj3.sdj3assignment2.model.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}
