package technomarket.model.dto.responseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import technomarket.model.pojo.Category;
import technomarket.model.pojo.SubCategory;

@Component
@Getter
@Setter
@NoArgsConstructor
public class ResponseSubCategoryDTO {

    private int id;
    private String name;
    private Category category;

    public ResponseSubCategoryDTO(SubCategory subCategory){
        this.id = subCategory.getId();
        this.name = subCategory.getName();
        this.category = subCategory.getCategory();
    }

}
