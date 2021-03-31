package technomarket.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import technomarket.model.pojo.Product;

import java.util.List;

@Repository
public interface ProductRepository  extends PagingAndSortingRepository<Product, Integer> {

    List<Product> findAllByNameLike(String like);
    List<Product> findAllByIdIn(List<Integer> ids);
}
