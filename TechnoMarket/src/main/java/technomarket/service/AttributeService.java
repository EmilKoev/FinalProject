package technomarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technomarket.exeptions.NotFoundException;
import technomarket.model.dto.AttributeDTO;
import technomarket.model.pojo.Product;
import technomarket.model.pojo.ProductAttribute;
import technomarket.model.repository.AttributeRepository;

import java.util.Optional;

@Service
public class AttributeService {

    @Autowired
    private AttributeRepository repository;

    @Autowired
    protected ProductService productService;

    public ProductAttribute addAttribute(AttributeDTO attributeDTO, int id) {
        Product product = productService.getById(id);
        ProductAttribute attribute = new ProductAttribute(attributeDTO, product);
        repository.save(attribute);
        product.getAttributes().add(attribute);
        productService.save(product);
        return attribute;
    }

    public ProductAttribute getAttribute(String name){
        Optional<ProductAttribute> attribute = repository.findById(name);
        if (attribute.isPresent()){
            return attribute.get();
        }else {
            throw new NotFoundException("Attribute not found!");
        }
    }

    public void delete(String name, int id) {
        //TODO get with 2 pk
        ProductAttribute attribute = getAttribute(name);
        Product product = productService.getById(id);
        product.getAttributes().remove(attribute);
        productService.save(product);
        repository.delete(attribute);
    }
}
