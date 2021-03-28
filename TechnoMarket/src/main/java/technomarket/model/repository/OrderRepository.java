package technomarket.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technomarket.model.pojo.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    Order getByUserId(int userId);
}
