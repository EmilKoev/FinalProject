package technomarket.model.dto.responseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import technomarket.model.pojo.Category;
import technomarket.model.pojo.SubCategory;

import java.util.List;

@Component
@Setter
@Getter
@NoArgsConstructor
public class CategoryResponseDTO {

    private int id;
    private String name;
    List<SubCategory> subCategories;

    public CategoryResponseDTO(Category category){
        this.id = category.getId();
        this.name = category.getName();
        this.subCategories = category.getSubCategories();
    }

}
