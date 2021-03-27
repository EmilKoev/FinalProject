package technomarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technomarket.exeptions.NotFoundException;
import technomarket.model.pojo.Product;
import technomarket.model.pojo.ProductImage;
import technomarket.model.repository.ProductImageRepository;

import java.util.Optional;

@Service
public class ProductImageService {

    @Autowired
    private ProductImageRepository repository;

    public ProductImage getById(int id) {
        Optional<ProductImage> product = repository.findById(id);
        if (!product.isPresent()){
            throw  new NotFoundException("Product image not found");
        }else {
            return product.get();
        }
    }

    public void save(ProductImage image){
        repository.save(image);
    }

}
