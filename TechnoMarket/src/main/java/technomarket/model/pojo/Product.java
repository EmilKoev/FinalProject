package technomarket.model.pojo;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import technomarket.model.dto.AddProductDTO;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brand;
    private int subCategoryId;
    private double price;
    private String info;
    private int discountId;
    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<ProductImage> images;
    @OneToMany
    @JsonManagedReference
    private List<ProductAttribute> attributes;
    @JsonBackReference
    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

    public Product(AddProductDTO productDTO){
        this.brand = productDTO.getBrand();
        this.subCategoryId = productDTO.getSubCategoryId();
        this.price = productDTO.getPrice();
        this.info = productDTO.getInfo();
        this.discountId = productDTO.getDiscountId();

    }

}
