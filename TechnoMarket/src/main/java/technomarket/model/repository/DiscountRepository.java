package technomarket.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technomarket.model.pojo.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {

}
