package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import technomarket.model.dto.requestDTO.*;
import technomarket.model.dto.requestDTO.productAndAttributeDTO.EditProductRequestDTO;
import technomarket.model.dto.requestDTO.productAndAttributeDTO.ProductRequestDTO;
import technomarket.model.dto.responseDTO.MessageResponseDTO;
import technomarket.model.dto.responseDTO.ProductResponseDTO;
import technomarket.model.pojo.Product;
import technomarket.model.pojo.User;
import technomarket.service.ProductService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductsController extends Controller{

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{id}")
    public ProductResponseDTO getById(@PathVariable int id){
        Product product = productService.getById(id);
        return new ProductResponseDTO(product);
    }

    @PutMapping("/add/products")
    public ProductResponseDTO addProduct(@Valid @RequestBody ProductRequestDTO productDTO, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        adminProtection(user);
        Product product = productService.addProduct(productDTO);
        return new ProductResponseDTO(product);
    }

    @DeleteMapping("/products/{id}")
    public MessageResponseDTO deleteProduct(@PathVariable int id, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        adminProtection(user);
        productService.delete(id);
        return new MessageResponseDTO("Delete product successful");
    }

    @PostMapping("/products/{id}")
    public ProductResponseDTO editProduct(@PathVariable int id, @Valid @RequestBody EditProductRequestDTO editProductDTO, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        adminProtection(user);
        Product product = productService.edit(id, editProductDTO);
        return new ProductResponseDTO(product);
    }

    @PostMapping("/products/react/{productId}")
    public ProductResponseDTO reactProduct(@RequestBody ReactRequestDTO reactDTO,
                                           @PathVariable int productId,
                                           HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        Product product = productService.react(reactDTO, productId, user);
        return new ProductResponseDTO(product);
    }

    @PostMapping("/products")
    public List<ProductResponseDTO> searchByName(@Valid @RequestBody SearchByStringRequestDTO searchStringDTO){
        return productService.searchByName(searchStringDTO);
    }

    @PostMapping("/products/search")
    public List<ProductResponseDTO> searchByAttributes(@RequestBody FilterRequestDTO filterDTO){
        return productService.searchByAttributes(filterDTO);
    }
}
