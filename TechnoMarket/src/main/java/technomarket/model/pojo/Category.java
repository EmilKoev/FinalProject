package technomarket.model.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import technomarket.model.dto.requestDTO.RequestCategoryDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "category")
    @JsonBackReference
    private List<SubCategory> subCategories;

    public Category(RequestCategoryDTO categoryDTO){
        this.name = categoryDTO.getName();
        this.subCategories = new ArrayList<>();
    }

}
