package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import technomarket.model.dto.requestDTO.CategoryRequestDTO;
import technomarket.model.dto.responseDTO.MessageResponseDTO;
import technomarket.model.dto.responseDTO.CategoryResponseDTO;
import technomarket.model.pojo.Category;
import technomarket.model.pojo.User;
import technomarket.service.CategoryService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoriesController extends Controller{

    @Autowired
    private CategoryService service;

    @PutMapping("/categories")
    public CategoryResponseDTO createCategory(@Valid @RequestBody CategoryRequestDTO categoryDTO, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        adminProtection(user);
        Category category = service.addCategory(categoryDTO);
        return new CategoryResponseDTO(category);
    }

    @GetMapping("/categories/{id}")
    public CategoryResponseDTO getCategory(@PathVariable int id){
        Category category = service.getCategory(id);
        return new CategoryResponseDTO(category);
    }

    @PostMapping("/categories/{id}")
    public CategoryResponseDTO editCategory(@PathVariable int id, @Valid @RequestBody CategoryRequestDTO categoryDTO, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        adminProtection(user);
        Category category = service.edit(id, categoryDTO);
        return new CategoryResponseDTO(category);
    }

    @DeleteMapping("/categories/{id}")
    public MessageResponseDTO deleteCategory(@PathVariable int id, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        adminProtection(user);
        service.delete(id);
        return new MessageResponseDTO("Delete category successful!");
    }


    @GetMapping("/categories")
    public List<CategoryResponseDTO> getAllCategories(){
        List<Category> categories = service.getAllCategories();
        List<CategoryResponseDTO> responseCategoryDTOList = new ArrayList<>();
        for (Category c : categories) {
            responseCategoryDTOList.add(new CategoryResponseDTO(c));
        }
        return responseCategoryDTOList;
    }

}
