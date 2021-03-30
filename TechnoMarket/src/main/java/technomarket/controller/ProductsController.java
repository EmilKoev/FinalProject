package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import technomarket.exeptions.AuthenticationException;
import technomarket.model.dto.requestDTO.*;
import technomarket.model.dto.responseDTO.ResponseProductDTO;
import technomarket.model.pojo.Product;
import technomarket.model.pojo.User;
import technomarket.service.ProductService;

import javax.servlet.http.HttpSession;
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
    public ResponseProductDTO addProduct(@RequestBody ProductDTO productDTO, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        if (!user.isAdmin()){
            throw  new AuthenticationException("Only admins can do this!");
        }
        return productService.addProduct(productDTO);
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable int id, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        if (!user.isAdmin()){
            throw  new AuthenticationException("Only admins can do this!");
        }
        productService.delete(id);
        return "Delete successful";
    }

    @PostMapping("/products/{id}")
    public ResponseProductDTO editProduct(@PathVariable int id, @RequestBody EditProductDTO editProductDTO, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        if (!user.isAdmin()){
            throw  new AuthenticationException("Only admins can do this!");
        }
        return productService.edit(id, editProductDTO);
    }

    @PostMapping("/products/react/{productId}")
    public ResponseProductDTO reactProduct(@RequestBody ReactDTO reactDTO,
                                           @PathVariable int productId,
                                           HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        return productService.react(reactDTO, productId, user);
    }

    @PostMapping("/products")
    public List<Product> searchByName(@RequestBody SearchStringDTO searchStringDTO){
        return productService.searchByName(searchStringDTO);
    }

    @PostMapping("/products/search")
    public List<Product> searchByAttributes(@RequestBody AttributesDTO attributesDTO){
        return productService.searchByAttributes(attributesDTO);
    }
}
