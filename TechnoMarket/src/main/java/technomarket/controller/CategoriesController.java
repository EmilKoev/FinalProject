package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import technomarket.exeptions.AuthenticationException;
import technomarket.model.dto.requestDTO.RequestCategoryDTO;
import technomarket.model.dto.responseDTO.ResponseCategoryDTO;
import technomarket.model.pojo.Category;
import technomarket.model.pojo.User;
import technomarket.service.CategoryService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class CategoriesController extends Controller{

    @Autowired
    private CategoryService service;

    @PutMapping("/categories")
    public ResponseCategoryDTO createCategory(@RequestBody Category category, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        if (!user.isAdmin()){
            throw  new AuthenticationException("Only admins can do this!");
        }
        return service.addCategory(category);
    }

    @GetMapping("/categories/{id}")
    public ResponseCategoryDTO getCategory(@PathVariable int id){
        Category category = service.getCategory(id);
        return new ResponseCategoryDTO(category);
    }

    @PostMapping("/categories/{id}")
    public ResponseCategoryDTO editCategory(@PathVariable int id, @RequestBody RequestCategoryDTO categoryDTO, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        if (!user.isAdmin()){
            throw  new AuthenticationException("Only admins can do this!");
        }
        return service.edit(id, categoryDTO);
    }

    @DeleteMapping("/categories/{id}")
    public String deleteCategory(@PathVariable int id, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        if (!user.isAdmin()){
            throw  new AuthenticationException("Only admins can do this!");
        }
        service.delete(id);
        return "Delete successful";
    }


    @GetMapping("/categories")
    public List<ResponseCategoryDTO> getAllCategories(){
        return service.getAllCategories();
    }

}
