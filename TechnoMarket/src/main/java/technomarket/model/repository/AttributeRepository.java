package technomarket.model.repository;

import org.springframework.data.repository.CrudRepository;
import technomarket.model.pojo.AttributeId;
import technomarket.model.pojo.ProductAttribute;

public interface AttributeRepository extends CrudRepository<ProductAttribute, AttributeId> {

}
