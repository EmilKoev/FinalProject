package technomarket.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technomarket.model.pojo.ProductAttribute;

@Repository
public interface AttributeRepository extends JpaRepository<ProductAttribute, String> {
}
