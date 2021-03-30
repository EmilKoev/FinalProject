package technomarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import technomarket.exeptions.BadRequestException;
import technomarket.exeptions.NotFoundException;
import technomarket.model.dto.requestDTO.*;
import technomarket.model.dto.responseDTO.ResponseProductDTO;
import technomarket.model.pojo.Discount;
import technomarket.model.pojo.Product;
import technomarket.model.pojo.SubCategory;
import technomarket.model.pojo.User;
import technomarket.model.repository.ProductRepository;
import technomarket.model.repository.UserRepository;

import java.util.List;
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
    @Autowired
    private UserRepository userRepository;


    public Product getById(int id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()){
            throw  new NotFoundException("Product not found!");
        }else {
            return product.get();
        }
    }

    @Transactional
    public ResponseProductDTO addProduct(ProductDTO productDTO) {
        SubCategory subCategory = subCategoryService.getSubCategory(productDTO.getSubCategoryId());
        Discount discount = discountService.getDiscount(productDTO.getDiscountId());
        Product product = new Product(productDTO, subCategory, discount);
        productRepository.save(product);
        for (AttributeDTO attributeDTO : productDTO.getAttributeList()) {
            attributeService.addAttribute(attributeDTO, product.getId());
        }
        return new ResponseProductDTO(product);
    }

    void save(Product product) {
        productRepository.save(product);
    }

    public void delete(int id) {
        Product product = getById(id);
        SubCategory subCategory = product.getSubCategory();
        Discount discount = product.getDiscount();
        subCategory.getProducts().remove(product);
        discount.getProductList().remove(product);
        productRepository.delete(product);
    }

    public ResponseProductDTO edit(int id, EditProductDTO editProductDTO) {
        SubCategory subCategory = subCategoryService.getSubCategory(editProductDTO.getSubCategoryId());
        Discount discount = discountService.getDiscount(editProductDTO.getDiscountId());
        Product product = getById(id);
        product.setName(editProductDTO.getName());
        product.setBrand(editProductDTO.getBrand());
        product.setSubCategory(subCategory);
        product.setPrice(editProductDTO.getPrice());
        product.setInfo(editProductDTO.getInfo());
        product.setDiscount(discount);
        productRepository.save(product);
        return new ResponseProductDTO(product);
    }

    public ResponseProductDTO react(ReactDTO reactDTO, int productId, User user) {
        Product product = getById(productId);
        switch (reactDTO.getReact()){
            case -1:
                if (!product.getDislikers().contains(user)){
                    product.getLikers().remove(user);
                    product.getDislikers().add(user);
                    user.getLikedProducts().remove(product);
                    user.getDislikedProducts().add(product);
                }
                break;
            case 1:
                if (!product.getLikers().contains(user)) {
                    product.getLikers().add(user);
                    product.getDislikers().remove(user);
                    user.getLikedProducts().add(product);
                    user.getDislikedProducts().remove(product);
                }
                break;
            case 0:
                product.getLikers().remove(user);
                product.getDislikers().remove(user);
                user.getLikedProducts().remove(product);
                user.getDislikedProducts().remove(product);
                break;
            default:
                throw new BadRequestException("Wrong credential!");
        }
        productRepository.save(product);
        userRepository.save(user);
        return new ResponseProductDTO(product);
    }

    public List<Product> searchByName(SearchStringDTO searchStringDTO) {
        return productRepository.findAllByNameLike("%" + searchStringDTO.getSearch() + "%");
    }

    public List<Product> searchByAttributes(AttributesDTO attributesDTO) {

        //todo...
        return null;
    }
}

