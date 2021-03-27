package technomarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technomarket.exeptions.NotFoundException;
import technomarket.model.dto.CreateRequestSubCategoryDTO;
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

    public SubCategory addSubCategory(CreateRequestSubCategoryDTO subCategoryDTO) {
        Category category = categoryService.getCategory(subCategoryDTO.getCategoryId());
        SubCategory subCategory = new SubCategory(subCategoryDTO, category);
        return repository.save(subCategory);
    }

    public SubCategory getSubCategory(int id) {
        Optional<SubCategory> subCategory = repository.findById(id);
        if (subCategory.isPresent()) {
            return subCategory.get();
        }else {
            throw new NotFoundException("Sub Category not found!");
        }
    }
}
