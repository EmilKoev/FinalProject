package technomarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import technomarket.exeptions.BadRequestException;
import technomarket.exeptions.NotFoundException;
import technomarket.model.dto.AddProductDTO;
import technomarket.model.dto.AttributeDTO;
import technomarket.model.dto.RegisterRequestUserDTO;
import technomarket.model.dto.UserWithoutPassDTO;
import technomarket.model.pojo.Product;
import technomarket.model.pojo.ProductAttribute;
import technomarket.model.pojo.User;
import technomarket.model.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    private AttributeService attributeService;


    public Product getById(int id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()){
            throw  new NotFoundException("Product not found!");
        }else {
            return product.get();
        }
    }

    public Product addProduct(AddProductDTO productDTO) {
        Product product = new Product(productDTO);
        List<AttributeDTO> attributes = productDTO.getAttributeList();
        for (AttributeDTO attributeDTO : attributes) {
            ProductAttribute attribute = new ProductAttribute(attributeDTO, product.getId());
            attributeService.addAttribute(attribute);
        }
        return productRepository.save(product);
    }
}

