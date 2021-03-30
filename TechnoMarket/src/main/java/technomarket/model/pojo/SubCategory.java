package technomarket.model.pojo;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import technomarket.model.dto.categoryDTO.RequestSubCategoryDTO;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "sub_categories")
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;
    private String name;
    @OneToMany(mappedBy = "subCategory")
    @JsonBackReference
    private List<Product> products;

    public SubCategory(RequestSubCategoryDTO subCategoryDTO, Category category){
        this.name = subCategoryDTO.getName();
        this.category = category;
    }

}
