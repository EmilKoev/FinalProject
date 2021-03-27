package technomarket.model.pojo;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import technomarket.model.dto.CreateRequestSubCategoryDTO;

import javax.persistence.*;
import java.util.concurrent.atomic.AtomicInteger;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "sub_categories")
public class SubCategory {

    @Id
    private int id;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;
    private String name;
    private static AtomicInteger count = new AtomicInteger(1);

    public SubCategory(CreateRequestSubCategoryDTO subCategoryDTO, Category category){
        this.name = subCategoryDTO.getName();
        this.category = category;
        this.id = count.getAndIncrement();
    }

}
