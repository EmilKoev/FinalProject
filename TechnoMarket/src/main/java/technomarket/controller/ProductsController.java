package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import technomarket.exeptions.AuthenticationException;
import technomarket.model.dto.ProductDTO;
import technomarket.model.dto.EditProductDTO;
import technomarket.model.pojo.Product;
import technomarket.model.pojo.User;
import technomarket.service.ProductService;

import javax.servlet.http.HttpSession;

@RestController
public class ProductsController extends Controller{

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{id}")
    public Product getById(@PathVariable int id){
        return productService.getById(id);
    }

    @PutMapping("/add/products")
    public Product addProduct(@RequestBody ProductDTO productDTO, HttpSession session){
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
    public Product editProduct(@PathVariable int id, @RequestBody EditProductDTO editProductDTO, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        if (!user.isAdmin()){
            throw  new AuthenticationException("Only admins can do this!");
        }
        return productService.edit(id, editProductDTO);
    }

}
