package technomarket.model.pojo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "products")
public class Product {

    private int id;
    private String brand;
    private int subCategoryId;
    private double price;
    private String info;
    private int discountId;
    

}
