package technomarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technomarket.exeptions.NotFoundException;
import technomarket.model.dto.requestDTO.RequestCategoryDTO;
import technomarket.model.dto.responseDTO.ResponseCategoryDTO;
import technomarket.model.pojo.Category;
import technomarket.model.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public ResponseCategoryDTO addCategory(RequestCategoryDTO categoryDTO) {
        Category category = new Category(categoryDTO);
       repository.save(category);
       return new ResponseCategoryDTO(category);
    }

    public Category getCategory(int id) {
        Optional<Category> category = repository.findById(id);
        if (category.isPresent()) {
            return category.get();
        }else {
            throw new NotFoundException("Category not found!");
        }
    }

    void save(Category category) {
        repository.save(category);
    }

    public ResponseCategoryDTO edit(int id, RequestCategoryDTO categoryDTO) {
        Category category = getCategory(id);
        category.setName(categoryDTO.getName());
        repository.save(category);
        return new ResponseCategoryDTO(category);
    }

    public void delete(int id){
        Category category = getCategory(id);
        repository.delete(category);
    }

    public List<ResponseCategoryDTO> getAllCategories() {
        List<Category> categories = repository.findAll();
        List<ResponseCategoryDTO> responseCategoryDTOList = new ArrayList<>();
        for (Category c : categories) {
            responseCategoryDTOList.add(new ResponseCategoryDTO(c));
        }
        return responseCategoryDTOList;
    }
}
