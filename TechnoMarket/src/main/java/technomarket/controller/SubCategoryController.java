package technomarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import technomarket.model.dto.requestDTO.SubCategoryRequestDTO;
import technomarket.model.dto.responseDTO.MessageResponseDTO;
import technomarket.model.dto.responseDTO.SubCategoryResponseDTO;
import technomarket.model.pojo.SubCategory;
import technomarket.model.pojo.User;
import technomarket.service.SubCategoryService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class SubCategoryController extends Controller{

    @Autowired
    private SubCategoryService service;

    @PutMapping("/sub_categories")
    public SubCategoryResponseDTO createSubCategory(@Valid @RequestBody SubCategoryRequestDTO subCategoryDTO, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        adminProtection(user);
        SubCategory subCategory = service.addSubCategory(subCategoryDTO);
        return new SubCategoryResponseDTO(subCategory);
    }

    @GetMapping("/sub_categories/{id}")
    public SubCategoryResponseDTO getSubCategory(@PathVariable int id){
        SubCategory subCategory = service.getSubCategory(id);
        return new SubCategoryResponseDTO(subCategory);
    }

    @PostMapping("/sub_categories/{id}")
    public SubCategoryResponseDTO editSubCategory(@PathVariable int id, @Valid @RequestBody SubCategoryRequestDTO requestSubCategoryDTO, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        adminProtection(user);
        SubCategory subCategory = service.edit(id, requestSubCategoryDTO);
        return new SubCategoryResponseDTO(subCategory);
    }

    @DeleteMapping("/sub_categories/{id}")
    public MessageResponseDTO deleteSubCategory(@PathVariable int id, HttpSession session){
        User user = sessionManager.getLoggedUser(session);
        adminProtection(user);
        service.delete(id);
        return new MessageResponseDTO("Delete sub category successful");
    }
}
