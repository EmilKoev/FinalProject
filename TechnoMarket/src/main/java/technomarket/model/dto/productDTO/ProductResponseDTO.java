package technomarket.model.dto.productDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import technomarket.model.pojo.Product;
import technomarket.model.pojo.ProductAttribute;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Component
public class ProductResponseDTO {

    private int id;
    private String brand;
    private int subCategoryId;
    private double price;
    private int discountId;
    private int comments;
    private List<ProductAttribute> attributeList;


    public ProductResponseDTO(Product product){
        id = product.getId();
        brand = product.getBrand();
        subCategoryId = product.getSubCategory().getId();
        price = product.getPrice();
        discountId = product.getDiscount().getId();
        comments = product.getComments().size();
        attributeList = product.getAttributes();
    }
}
