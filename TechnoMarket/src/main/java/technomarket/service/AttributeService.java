package technomarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import technomarket.exeptions.BadRequestException;
import technomarket.exeptions.NotFoundException;
import technomarket.model.dto.requestDTO.productAndAttributeDTO.AttributeRequestDTO;
import technomarket.model.dto.requestDTO.productAndAttributeDTO.EditAttributeRequestDTO;
import technomarket.model.pojo.AttributeId;
import technomarket.model.pojo.Product;
import technomarket.model.pojo.ProductAttribute;
import technomarket.model.repository.AttributeRepository;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class AttributeService {

    @Autowired
    private AttributeRepository repository;

    @Autowired
    protected ProductService productService;

    public ProductAttribute addAttribute(AttributeRequestDTO attributeDTO, int productId) {
        try {
            getAttribute(attributeDTO.getName(), productId);
        }catch (NotFoundException exception){
            Product product = productService.getById(productId);
            ProductAttribute newAttribute = new ProductAttribute(attributeDTO, product);
            repository.save(newAttribute);
            return newAttribute;
        }
        throw new BadRequestException("Product already has this attribute");
    }

    public ProductAttribute getAttribute(String name, int productId){
        Product product = productService.getById(productId);
        AttributeId attributeId = new AttributeId(name, product.getId());
        Optional<ProductAttribute> attribute = repository.findById(attributeId);
        if (attribute.isPresent()){
            return attribute.get();
        }else {
            throw new NotFoundException("Attribute not found!");
        }
    }

    public void delete(String name, int productId) {
        ProductAttribute attribute = getAttribute(name, productId);
        repository.delete(attribute);
    }

    @Transactional
    public ProductAttribute edit(EditAttributeRequestDTO editAttributeDTO, int productId) {
        ProductAttribute attribute = getAttribute(editAttributeDTO.getName(), productId);
        repository.delete(attribute);
        AttributeRequestDTO attributeDTO = new AttributeRequestDTO(editAttributeDTO.getNewName(), editAttributeDTO.getNewValue());
        return addAttribute(attributeDTO, productId);
    }
}