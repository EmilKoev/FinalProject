package technomarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import technomarket.exeptions.BadRequestException;
import technomarket.exeptions.NotFoundException;
import technomarket.model.dao.ProductDao;
import technomarket.model.dto.requestDTO.*;
import technomarket.model.dto.responseDTO.ResponseProductDTO;
import technomarket.model.pojo.*;
import technomarket.model.repository.ProductRepository;
import technomarket.model.repository.UserRepository;

import java.util.ArrayList;
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
    @Autowired
    private ProductDao productDao;


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

    public List<ResponseProductDTO> searchByName(SearchStringDTO searchStringDTO) {
       List<ResponseProductDTO> products = new ArrayList<>();
        for (Product p : productRepository.findAllByNameLike("%" + searchStringDTO.getSearch() + "%")) {
            products.add(new ResponseProductDTO(p));
        }
        return products;
    }

    public List<ResponseProductDTO> searchByAttributes(FilterDTO filterDTO) {
        List<Integer> ids = productDao.findAllProductsByFilter(filterDTO);
        List<Product> firstFilter = productRepository.findAllByIdIn(ids);
        List<ResponseProductDTO> filteredProducts = new ArrayList<>();

            for (Product p : firstFilter) {
                int attributesFound = 0;
                for (AttributeDTO attribute : filterDTO.getAttributes()) {
                    boolean findAttribute = false;
                    for (ProductAttribute a : p.getAttributes()) {
                        if (attribute.getName().equalsIgnoreCase(a.getName())){
                            if (attribute.getValue().equalsIgnoreCase(a.getValue())){
                                findAttribute = true;
                                attributesFound++;
                                break;
                            }
                        }
                    }

                }
                if (attributesFound == filterDTO.getAttributes().size()){
                    filteredProducts.add(new ResponseProductDTO(p));
                }
            }
        return filteredProducts;
    }
}

