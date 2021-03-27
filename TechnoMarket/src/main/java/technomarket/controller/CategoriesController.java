package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import technomarket.exeptions.AuthenticationException;
import technomarket.model.pojo.Category;
import technomarket.model.pojo.User;
import technomarket.service.CategoryService;

import javax.servlet.http.HttpSession;

@RestController
public class CategoriesController extends Controller{

    @Autowired
    private CategoryService service;

    @PutMapping("/category")
    public Category createCategory(@RequestBody Category category, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        if (!user.isAdmin()){
            throw  new AuthenticationException("Only admins can do this!");
        }
        return service.addCategory(category);
    }

    @GetMapping("/category/{id}")
    public Category getCategory(@PathVariable int id){
        return service.getCategory(id);
    }

}
