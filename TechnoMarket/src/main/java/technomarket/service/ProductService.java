package technomarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import technomarket.exeptions.NotFoundException;
import technomarket.model.dto.ProductDTO;
import technomarket.model.dto.AttributeDTO;
import technomarket.model.dto.EditProductDTO;
import technomarket.model.pojo.Product;
import technomarket.model.repository.ProductRepository;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AttributeService attributeService;
    @Autowired
    private DiscountService discountService;
    @Autowired
    private SubCategoryService subCategoryService;


    public Product getById(int id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()){
            throw  new NotFoundException("Product not found!");
        }else {
            return product.get();
        }
    }

    @Transactional
    public Product addProduct(ProductDTO productDTO) {
        subCategoryService.getSubCategory(productDTO.getSubCategoryId());
        discountService.getDiscount(productDTO.getDiscountId());
        Product product = new Product(productDTO);
        productRepository.save(product);
        for (AttributeDTO attributeDTO : productDTO.getAttributeList()) {
            attributeService.addAttribute(attributeDTO, product.getId());
        }
        return product;
    }

    void save(Product product) {
        productRepository.save(product);
    }

    public void delete(int id) {
        Product product = getById(id);
        productRepository.delete(product);
    }

    public Product edit(int id, EditProductDTO editProductDTO) {
        Product product = getById(id);
        product.setBrand(editProductDTO.getBrand());
        product.setSubCategoryId(editProductDTO.getSubCategoryId());
        product.setPrice(editProductDTO.getPrice());
        product.setInfo(editProductDTO.getInfo());
        product.setDiscountId(editProductDTO.getDiscountId());
        return productRepository.save(product);
    }
}

