package technomarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technomarket.model.pojo.ProductAttribute;
import technomarket.model.repository.AttributeRepository;

@Service
public class AttributeService {

    @Autowired
    private AttributeRepository repository;

    public ProductAttribute addAttribute(ProductAttribute attribute) {
        return repository.save(attribute);
    }
}
