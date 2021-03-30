package technomarket.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technomarket.model.pojo.Product;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Integer> {

    List<Product> findAllByNameLike(String like);
}
