package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import technomarket.exeptions.AuthenticationException;
import technomarket.model.dto.requestDTO.RequestCategoryDTO;
import technomarket.model.dto.responseDTO.MessageDTO;
import technomarket.model.dto.responseDTO.ResponseCategoryDTO;
import technomarket.model.pojo.Category;
import technomarket.model.pojo.User;
import technomarket.service.CategoryService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoriesController extends Controller{

    @Autowired
    private CategoryService service;

    @PutMapping("/categories")
    public ResponseCategoryDTO createCategory(@RequestBody RequestCategoryDTO categoryDTO, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        if (!user.isAdmin()){
            throw  new AuthenticationException("Only admins can do this!");
        }
        Category category = service.addCategory(categoryDTO);
        return new ResponseCategoryDTO(category);
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
        Category category = service.edit(id, categoryDTO);
        return new ResponseCategoryDTO(category);
    }

    @DeleteMapping("/categories/{id}")
    public MessageDTO deleteCategory(@PathVariable int id, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        if (!user.isAdmin()){
            throw  new AuthenticationException("Only admins can do this!");
        }
        service.delete(id);
        return new MessageDTO("Delete category successful!");
    }


    @GetMapping("/categories")
    public List<ResponseCategoryDTO> getAllCategories(){
        List<Category> categories = service.getAllCategories();
        List<ResponseCategoryDTO> responseCategoryDTOList = new ArrayList<>();
        for (Category c : categories) {
            responseCategoryDTOList.add(new ResponseCategoryDTO(c));
        }
        return responseCategoryDTOList;
    }

}
