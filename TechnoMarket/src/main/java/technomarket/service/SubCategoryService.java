package technomarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technomarket.exeptions.NotFoundException;
import technomarket.model.dto.requestDTO.RequestSubCategoryDTO;
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

    public SubCategory addSubCategory(RequestSubCategoryDTO subCategoryDTO) {
        Category category = categoryService.getCategory(subCategoryDTO.getCategoryId());
        SubCategory subCategory = new SubCategory(subCategoryDTO, category);
        repository.save(subCategory);
        return subCategory;
    }

    public SubCategory getSubCategory(int id) {
        Optional<SubCategory> subCategory = repository.findById(id);
        if (subCategory.isPresent()) {
            return subCategory.get();
        }else {
            throw new NotFoundException("Sub Category not found!");
        }
    }

    public SubCategory edit(int id, RequestSubCategoryDTO requestSubCategory) {
        SubCategory subCategory = getSubCategory(id);
        Category newCategory = categoryService.getCategory(requestSubCategory.getCategoryId());
        subCategory.setName(requestSubCategory.getName());
        subCategory.setCategory(newCategory);
        repository.save(subCategory);
        return subCategory;
    }

    public void delete(int id){
        SubCategory subCategory = getSubCategory(id);
        repository.delete(subCategory);
    }
}
