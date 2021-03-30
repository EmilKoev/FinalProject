package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import technomarket.exeptions.AuthenticationException;
import technomarket.model.dto.requestDTO.RequestSubCategoryDTO;
import technomarket.model.dto.responseDTO.ResponseSubCategoryDTO;
import technomarket.model.pojo.SubCategory;
import technomarket.model.pojo.User;
import technomarket.service.SubCategoryService;

import javax.servlet.http.HttpSession;

@RestController
public class SubCategoryController extends Controller{

    @Autowired
    private SubCategoryService service;

    @PutMapping("/sub_categories")
    public ResponseSubCategoryDTO createSubCategory(@RequestBody RequestSubCategoryDTO subCategoryDTO, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        if (!user.isAdmin()){
            throw  new AuthenticationException("Only admins can do this!");
        }
        return service.addSubCategory(subCategoryDTO);
    }

    @GetMapping("/sub_categories/{id}")
    public ResponseSubCategoryDTO getSubCategory(@PathVariable int id){
        SubCategory subCategory = service.getSubCategory(id);
        return new ResponseSubCategoryDTO(subCategory);
    }

    @PostMapping("/sub_categories/{id}")
    public ResponseSubCategoryDTO editSubCategory(@PathVariable int id, @RequestBody RequestSubCategoryDTO subCategory, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        if (!user.isAdmin()){
            throw  new AuthenticationException("Only admins can do this!");
        }
        return service.edit(id, subCategory);
    }

    @DeleteMapping("/sub_categories/{id}")
    public String deleteSubCategory(@PathVariable int id, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        if (!user.isAdmin()){
            throw  new AuthenticationException("Only admins can do this!");
        }
        service.delete(id);
        return "Delete successful";
    }
}
