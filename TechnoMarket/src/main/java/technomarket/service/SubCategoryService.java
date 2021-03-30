package technomarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technomarket.exeptions.NotFoundException;
import technomarket.model.dto.requestDTO.RequestSubCategoryDTO;
import technomarket.model.dto.responseDTO.ResponseSubCategoryDTO;
import technomarket.model.pojo.Category;
import technomarket.model.pojo.SubCategory;
import technomarket.model.repository.SubCategoryRepository;

import java.util.Optional;

@Service
public class SubCategoryService {
    @Autowired
    private SubCategoryRepository repository;

    @Autowired
    private CategoryService categoryService;

    public ResponseSubCategoryDTO addSubCategory(RequestSubCategoryDTO subCategoryDTO) {
        Category category = categoryService.getCategory(subCategoryDTO.getCategoryId());
        SubCategory subCategory = new SubCategory(subCategoryDTO, category);
        category.getSubCategories().add(subCategory);
        repository.save(subCategory);
        categoryService.save(category);
        return new ResponseSubCategoryDTO(subCategory);
    }

    public SubCategory getSubCategory(int id) {
        Optional<SubCategory> subCategory = repository.findById(id);
        if (subCategory.isPresent()) {
            return subCategory.get();
        }else {
            throw new NotFoundException("Sub Category not found!");
        }
    }

    public ResponseSubCategoryDTO edit(int id, RequestSubCategoryDTO requestSubCategory) {
        SubCategory subCategory = getSubCategory(id);
        Category category = subCategory.getCategory();
        Category newCategory = categoryService.getCategory(requestSubCategory.getCategoryId());
        category.getSubCategories().remove(subCategory);
        categoryService.save(category);
        subCategory.setName(requestSubCategory.getName());
        subCategory.setCategory(newCategory);
        repository.save(subCategory);
        newCategory.getSubCategories().add(subCategory);
        categoryService.save(newCategory);
        return new ResponseSubCategoryDTO(subCategory);
    }

    public void delete(int id){
        SubCategory subCategory = getSubCategory(id);
        Category category = subCategory.getCategory();
        category.getSubCategories().remove(subCategory);
        categoryService.save(category);
        repository.delete(subCategory);
    }
}
