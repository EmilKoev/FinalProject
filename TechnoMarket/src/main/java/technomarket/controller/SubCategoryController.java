package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import technomarket.exeptions.AuthenticationException;
import technomarket.model.dto.CreateRequestSubCategoryDTO;
import technomarket.model.pojo.SubCategory;
import technomarket.model.pojo.User;
import technomarket.service.SubCategoryService;

import javax.servlet.http.HttpSession;

@RestController
public class SubCategoryController extends Controller{

    @Autowired
    private SubCategoryService service;

    @PutMapping("/sub_category")
    public SubCategory createSubCategory(@RequestBody CreateRequestSubCategoryDTO category, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        if (!user.isAdmin()){
            throw  new AuthenticationException("Only admins can do this!");
        }
        return service.addSubCategory(category);
    }

    @GetMapping("/sub_category/{id}")
    public SubCategory getSubCategory(@PathVariable int id){
        return service.getSubCategory(id);
    }
}
