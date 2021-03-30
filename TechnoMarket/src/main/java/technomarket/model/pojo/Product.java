package technomarket.model.pojo;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import technomarket.model.dto.requestDTO.ProductDTO;

import javax.persistence.*;
import java.util.ArrayList;
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
    private String name;
    private String brand;
    @ManyToOne()
    @JoinColumn(name = "sub_category_id")
    @JsonBackReference
    private SubCategory subCategory;
    private double price;
    private String info;
    @ManyToOne()
    @JoinColumn(name = "discount_id")
    @JsonBackReference
    private Discount discount;
    @OneToMany(mappedBy = "productId")
    @JsonManagedReference
    private List<Comment> comments;
    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<ProductImage> images;
    @OneToMany(mappedBy = "productId")
    @JsonManagedReference
    private List<ProductAttribute> attributes;
    @JsonBackReference
    @ManyToMany(mappedBy = "products")
    private List<Order> orders;
    @ManyToMany
    @JoinTable(
            name ="liked_products",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns ={@JoinColumn(name = "user_id")}
    )
    List<User> likers;
    @ManyToMany
    @JoinTable(
            name ="disliked_products",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns ={@JoinColumn(name = "user_id")}
    )
    List<User> dislikers;

    public Product(ProductDTO productDTO, SubCategory subCategory, Discount discount){
        this.name = productDTO.getName();
        this.brand = productDTO.getBrand();
        this.subCategory = subCategory;
        this.price = productDTO.getPrice();
        this.info = productDTO.getInfo();
        this.discount = discount;
        this.attributes = new ArrayList<>();
        this.likers = new ArrayList<>();
        this.dislikers = new ArrayList<>();
        this.comments = new ArrayList<>();
    }
}
