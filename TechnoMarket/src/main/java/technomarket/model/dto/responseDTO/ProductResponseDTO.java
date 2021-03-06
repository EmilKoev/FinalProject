package technomarket.model.dto.responseDTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import technomarket.model.pojo.*;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Component
@Getter
@Setter
@NoArgsConstructor
public class ProductResponseDTO {

    private int id;
    private String name;
    private String brand;
    private SubCategory subCategory;
    private double price;
    private String info;
    private Discount discount;
    private int comments;
    private List<ProductImage> images;
    private List<ProductAttribute> attributes;
    private int likes;
    private int dislikes;

    public ProductResponseDTO(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.brand = product.getBrand();
        this.subCategory = product.getSubCategory();
        this.price = product.getPrice();
        this.info = product.getInfo();
        this.discount = product.getDiscount();
        this.images = product.getImages();
        this.attributes = product.getAttributes();
        this.likes = product.getLikers().size();
        this.dislikes = product.getDislikers().size();
        this.comments = product.getComments().size();
    }

}
