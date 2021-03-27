package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import technomarket.exeptions.AuthenticationException;
import technomarket.model.dto.AddProductDTO;
import technomarket.model.pojo.Product;
import technomarket.model.pojo.User;
import technomarket.service.ProductService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
public class ProductsController extends Controller{

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{id}")
    public Product getById(@PathVariable int id){
        return productService.getById(id);
    }

    @PutMapping("/add/product")
    public Product addProduct(@RequestBody AddProductDTO productDTO, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        if (!user.isAdmin()){
            throw  new AuthenticationException("Only admins can do this!");
        }
        return productService.addProduct(productDTO);
    }

}
