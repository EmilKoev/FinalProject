package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import technomarket.exeptions.AuthenticationException;
import technomarket.model.dto.requestDTO.*;
import technomarket.model.dto.requestDTO.productAndAttributeDTO.EditProductDTO;
import technomarket.model.dto.requestDTO.productAndAttributeDTO.ProductDTO;
import technomarket.model.dto.responseDTO.MessageDTO;
import technomarket.model.dto.responseDTO.ResponseProductDTO;
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
    public ResponseProductDTO getById(@PathVariable int id){
        Product product = productService.getById(id);
        return new ResponseProductDTO(product);
    }

    @PutMapping("/add/products")
    public ResponseProductDTO addProduct(@Valid @RequestBody ProductDTO productDTO, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        adminProtection(user);
        Product product = productService.addProduct(productDTO);
        return new ResponseProductDTO(product);
    }

    @DeleteMapping("/products/{id}")
    public MessageDTO deleteProduct(@PathVariable int id, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        adminProtection(user);
        productService.delete(id);
        return new MessageDTO("Delete product successful");
    }

    @PostMapping("/products/{id}")
    public ResponseProductDTO editProduct(@PathVariable int id,@Valid @RequestBody EditProductDTO editProductDTO, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        adminProtection(user);
        Product product = productService.edit(id, editProductDTO);
        return new ResponseProductDTO(product);
    }

    @PostMapping("/products/react/{productId}")
    public ResponseProductDTO reactProduct(@RequestBody ReactDTO reactDTO,
                                           @PathVariable int productId,
                                           HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        Product product = productService.react(reactDTO, productId, user);
        return new ResponseProductDTO(product);
    }

    @PostMapping("/products")
    public List<ResponseProductDTO> searchByName(@Valid @RequestBody SearchStringDTO searchStringDTO){
        return productService.searchByName(searchStringDTO);
    }

    @PostMapping("/products/search")
    public List<ResponseProductDTO> searchByAttributes(@RequestBody FilterDTO filterDTO){
        return productService.searchByAttributes(filterDTO);
    }
}
