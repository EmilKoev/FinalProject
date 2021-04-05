package technomarket.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import technomarket.model.pojo.ProductImage;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {

}
