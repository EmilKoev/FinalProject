package technomarket.service;

import org.springframework.stereotype.Service;
import technomarket.exeptions.NotFoundException;
import technomarket.model.pojo.ProductAttribute;
import technomarket.model.repository.AttributeRepository;

@Service
public class AttributeService {

    private AttributeRepository repository;
    private ProductService productService;

    public ProductAttribute addAttribute(ProductAttribute attribute) {
        if (productService.getById(attribute.getProductId()) != null){
            throw new NotFoundException("Product not found!");
        }
        return repository.save(attribute);
    }
}
