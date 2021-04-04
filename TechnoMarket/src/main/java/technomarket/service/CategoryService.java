package technomarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technomarket.exeptions.NotFoundException;
import technomarket.model.dto.requestDTO.CategoryRequestDTO;
import technomarket.model.pojo.Category;
import technomarket.model.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category addCategory(CategoryRequestDTO categoryDTO) {
        Category category = new Category(categoryDTO);
        return repository.save(category);
    }

    public Category getCategory(int id) {
        Optional<Category> category = repository.findById(id);
        if (category.isPresent()) {
            return category.get();
        }else {
            throw new NotFoundException("Category not found!");
        }
    }

    public Category edit(int id, CategoryRequestDTO categoryDTO) {
        Category category = getCategory(id);
        category.setName(categoryDTO.getName());
        repository.save(category);
        return category;
    }

    public void delete(int id){
        Category category = getCategory(id);
        repository.delete(category);
    }

    public List<Category> getAllCategories() {
        return repository.findAll();
    }
}
